package Layouts.Map;
import Layouts.Controller;
import Layouts.ViewInterface;
import Positions.Positions;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
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
import java.util.HashMap;

import Character.Character;

public class Map implements ViewInterface{
    private Scene scene;
    private Stage stage;

    private Pane pane;

    private SVGPath addCharacterButton;
    private SVGPath nextMapButton;
    private SVGPath charactersButton;
    private SVGPath saveButton;

    private Accordion accordion;

    private ImageView imageView;

    public Map() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Map.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);

        pane = (Pane) scene.lookup("#pane");

        addCharacterButton = (SVGPath) scene.lookup("#addCharacterButton");
        nextMapButton = (SVGPath) scene.lookup("#nextMapButton");
        charactersButton = (SVGPath) scene.lookup("#charactersButton");
        saveButton = (SVGPath) scene.lookup("#saveButton");

        accordion = (Accordion) scene.lookup("#accordion");

        imageView = (ImageView) scene.lookup("#imageView");

        Image image = new Image("/map1.jpg");
        imageView.setImage(image);

        GridPane gridPane = (GridPane) scene.lookup("#gridPane");


        for (int i = 0; i < 20; i++) {
            ColumnConstraints col = new ColumnConstraints(5, 100, Region.USE_COMPUTED_SIZE);
            gridPane.getColumnConstraints().add(col);
        }
        for (int i = 0; i < 20; i++) {
            RowConstraints row = new RowConstraints(5, 100, Region.USE_COMPUTED_SIZE);
            gridPane.getRowConstraints().add(row);
        }

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
            case "charactersButton":
                charactersButton.setOnMouseClicked(listener);
                break;
            case "saveButton":
                saveButton.setOnMouseClicked(listener);
                break;
            case "getCharacter":
                for (Node titledPane: accordion.getChildrenUnmodifiable())
                    titledPane.setOnMouseClicked(listener);
                break;
        }
    }

    @Override
    public void setButtonListener(String comboBox, ChangeListener changeListener) {
    }
}


