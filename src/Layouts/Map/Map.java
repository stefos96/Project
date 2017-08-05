package Layouts.Map;
import Layouts.ViewInterface;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.*;
import Character.Character;
import javafx.util.Duration;

public class Map implements ViewInterface{
    private Scene scene;
    private Stage stage;

    private Pane pane;

    private SVGPath addCharacterButton;
    private SVGPath nextMapButton;
    private SVGPath saveButton;
    private SVGPath infoButton;

    private Accordion accordion;

    private ImageView imageView;

    private Group dice;

    private int rotationSpeed = 500;
    private int cycleCount = 50;
    private int rate = 5;


    public Map() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Map.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);

        pane = (Pane) scene.lookup("#pane");

        addCharacterButton = (SVGPath) scene.lookup("#addCharacterButton");
        nextMapButton = (SVGPath) scene.lookup("#nextMapButton");
        saveButton = (SVGPath) scene.lookup("#saveButton");
        infoButton = (SVGPath) scene.lookup("#infoButton");

        accordion = (Accordion) scene.lookup("#accordion");

        imageView = (ImageView) scene.lookup("#imageView");

        Image image = new Image("/map1.jpg");
        imageView.setImage(image);

        GridPane gridPane = (GridPane) scene.lookup("#gridPane");

        dice = (Group) scene.lookup("#dice");

        for (int i = 0; i < 20; i++) {
            ColumnConstraints col = new ColumnConstraints(5, 100, Region.USE_COMPUTED_SIZE);
            gridPane.getColumnConstraints().add(col);
        }
        for (int i = 0; i < 20; i++) {
            RowConstraints row = new RowConstraints(5, 100, Region.USE_COMPUTED_SIZE);
            gridPane.getRowConstraints().add(row);
        }

    }

    public void rotateDice() {
        //Creating a rotate transition
        RotateTransition rotateTransition = new RotateTransition();

        //Setting the node for the transition
        rotateTransition.setNode(dice);

        rotateTransition.setInterpolator(Interpolator.LINEAR);

        //Setting the duration for the transition
        rotateTransition.setDuration(Duration.millis(rotationSpeed));

        //Setting the angle of the rotation
        rotateTransition.setByAngle(360);

        //Setting the cycle count for the transition
        rotateTransition.setCycleCount(cycleCount);

        //Setting auto reverse value to false
        rotateTransition.setAutoReverse(false);

        rotateTransition.setOnFinished(e -> rotateDice());

        rotateTransition.setRate(rate);

        if (rate == 5)
            rate = 3;
        else if (cycleCount == 1) {
            rotateTransition.setAutoReverse(true);
            rate = 0;
        }
        else if (rate == 3) {
            rate = 1;
            cycleCount = 5;
            rotationSpeed = 800;
        }
        else if (rate == 1) {
            rotationSpeed = 1500;
            cycleCount = 1;
        }



        rotateTransition.play();
    }

    public void setImage(String imageString) {
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(imageString));
            imageView.setImage(new Image(is));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Add a character by creating a titlePane and a flowPane
     * @param character the character's name
     */
    public void addCharacterToAccordion(Character character, EventHandler<? super MouseEvent> listener) {
        String name = character.getCharacterName();
        String className = character.getCharacterClass().getName();



        TitledPane titledPane = new TitledPane();
        titledPane.setText(name);

        FlowPane flowPane = new FlowPane();

        // Image path
        String imagePath = character.getPortrait();

        try {
            InputStream is = new BufferedInputStream(new FileInputStream(imagePath));
            Image image = (new Image(is));

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);

            flowPane.getChildren().add(imageView);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        Label nameLabel = new Label(name);
        Label seperator = new Label(" - ");
        Label classLabel = new Label(className);
        nameLabel.setFont(Font.font(23));
        classLabel.setFont(Font.font(23));

        flowPane.getChildren().add(nameLabel);
        flowPane.getChildren().add(seperator);
        flowPane.getChildren().add(classLabel);

        titledPane.setContent(flowPane);

        accordion.getPanes().add(titledPane);

        try {
            InputStream is = new BufferedInputStream(new FileInputStream(imagePath));
            Image image = (new Image(is));

            ImageView miniIcon = new ImageView(image);
            miniIcon.setFitWidth(100);
            miniIcon.setFitHeight(100);

            if (character.getXPosition() != 0) {
                miniIcon.setX(character.getXPosition());
                miniIcon.setY(character.getYPosition());
            }

            miniIcon.setOnMouseDragged(listener);

            pane.getChildren().add(miniIcon);

            character.setPositions(miniIcon.getX(), miniIcon.getY());
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show() {
        stage.show();
    }

    @Override
    public void hide() {
        stage.hide();
    }

    @Override
    public void setButtonListener(String button, EventHandler<? super MouseEvent> listener) {
        switch (button) {
            case "addCharacterButton":
                addCharacterButton.setOnMouseClicked(listener);
                break;
            case "nextMapButton":
                nextMapButton.setOnMouseClicked(listener);
                break;
            case "saveButton":
                saveButton.setOnMouseClicked(listener);
                break;
            case "getCharacter":
                for (Node titledPane: accordion.getChildrenUnmodifiable())
                    titledPane.setOnMouseClicked(listener);
                break;
            case "infoButton":
                infoButton.setOnMouseClicked(listener);
                break;
        }
    }

    @Override
    public void setButtonListener(String comboBox, ChangeListener changeListener) {
    }
}


