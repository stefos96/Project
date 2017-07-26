package Layouts.CreateCharacter;
import Alignment.*;
import Layouts.ViewInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import Character.GenderEnum;
import Character.CharacterRace;
import Character.CharacterClass;
import Character.Character;



public class CreateCharacter implements ViewInterface {
    private Scene scene;
    private Stage stage;

    private SVGPath createCharacterButton;
    private SVGPath cancelButton;

    private TextField playerNameTextField;
    private TextField characterNameTextField;

    private ComboBox raceComboBox;
    private ComboBox classComboBox;

    private ComboBox alignmentComboBox;
    private ComboBox ethicsComboBox;


    private TextField heightTextField;
    private TextField weightTextField;

    private Rectangle genderRectangle;
    private Circle genderMaleCircle;
    private Circle genderFemaleCircle;

    private CharacterSql characterSql = new CharacterSql();



    public CreateCharacter() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CreateCharacter.fxml"));

        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);

        createCharacterButton = (SVGPath) scene.lookup("#createCharacterButton");
        cancelButton = (SVGPath) scene.lookup("#cancelButton");

        playerNameTextField = (TextField) scene.lookup("#playerNameTextField");
        characterNameTextField = (TextField) scene.lookup("#characterNameTextField");

        raceComboBox = (ComboBox) scene.lookup("#raceComboBox");
        classComboBox = (ComboBox) scene.lookup("#classComboBox");
        alignmentComboBox = (ComboBox) scene.lookup("#alignmentComboBox");
        ethicsComboBox = (ComboBox) scene.lookup("#ethicsComboBox");


        heightTextField = (TextField) scene.lookup("#heightTextField");
        weightTextField = (TextField) scene.lookup("#weightTextField");

        // Gender
        genderRectangle = (Rectangle) scene.lookup("#genderRectangle");
        genderMaleCircle = (Circle) scene.lookup("#genderMaleCircle");
        genderFemaleCircle = (Circle) scene.lookup("#genderFemaleCircle");

        alignmentComboBox.setItems(FXCollections.observableArrayList( Alignment.values()));
        ethicsComboBox.setItems(FXCollections.observableArrayList( Ethics.values()));


        Thread characterThread = new Thread(characterSql);
        characterThread.run();
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
        switch (button){
            case "createCharacterButton":
                createCharacterButton.setOnMouseClicked(listener);
            case "cancelButton":
                cancelButton.setOnMouseClicked(listener);
            case "genderButton":
                genderRectangle.setOnMouseClicked(listener);
                genderFemaleCircle.setOnMouseClicked(listener);
                genderMaleCircle.setOnMouseClicked(listener);
        }
    }

    /**
     * Changes the gender
     */
    public void changeGender() {
        if (genderMaleCircle.getFill().equals(Paint.valueOf("#00ffb4"))) {
            genderMaleCircle.setFill(Paint.valueOf("#5c5b5c"));
            genderFemaleCircle.setFill(Paint.valueOf("#ff81fc"));
        }
        else {
            genderMaleCircle.setFill(Paint.valueOf("#00ffb4"));
            genderFemaleCircle.setFill(Paint.valueOf("#5d5e5e"));
        }
    }

    public void ready() {
        ArrayList<String> raceArrayList = characterSql.getRaces();
        ObservableList<String> raceObservable = FXCollections.observableArrayList(raceArrayList);
        raceComboBox.setItems(raceObservable);

        ArrayList<String> classArrayList = characterSql.getClasses();
        ObservableList<String> classObservable = FXCollections.observableArrayList(classArrayList);
        classComboBox.setItems(classObservable);


    }


    public void createCharacter(){
        String playerName = playerNameTextField.getText();
        String characterName = characterNameTextField.getText();
        String race = raceComboBox.getSelectionModel().getSelectedItem().toString();
        String playerClass = classComboBox.getSelectionModel().getSelectedItem().toString();
        GenderEnum gender;

        if (genderMaleCircle.getFill().equals("#00ffb4"))
            gender = GenderEnum.MALE;
        else
            gender = GenderEnum.FEMALE;

        int height = Integer.parseInt(heightTextField.getText());
        int weight = Integer.parseInt(weightTextField.getText());


    }
}
