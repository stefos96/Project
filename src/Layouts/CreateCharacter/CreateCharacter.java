package Layouts.CreateCharacter;
import Alignment.*;
import Layouts.ViewInterface;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.io.*;
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

    private SVGPath portraitButton;

    private TextField playerNameTextField;
    private TextField characterNameTextField;

    private ComboBox raceComboBox;
    private ComboBox classComboBox;

    private ComboBox alignmentComboBox;
    private ComboBox ethicsComboBox;

    private Rectangle genderRectangle;
    private Circle genderMaleCircle;
    private Circle genderFemaleCircle;
    private Circle genderFemaleBack;
    private Circle genderMaleBack;

    private Image image;
    private String imagePath;


    private CharacterSql characterSql = new CharacterSql();

    CharacterClass characterClass = new CharacterClass();
    CharacterRace characterRace = new CharacterRace();


    public CreateCharacter() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CreateCharacter.fxml"));

        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);

        createCharacterButton = (SVGPath) scene.lookup("#createCharacterButton");
        cancelButton = (SVGPath) scene.lookup("#cancelButton");

        portraitButton = (SVGPath) scene.lookup("#portraitButton");

        playerNameTextField = (TextField) scene.lookup("#playerNameTextField");
        characterNameTextField = (TextField) scene.lookup("#characterNameTextField");

        raceComboBox = (ComboBox) scene.lookup("#raceComboBox");
        classComboBox = (ComboBox) scene.lookup("#classComboBox");
        alignmentComboBox = (ComboBox) scene.lookup("#alignmentComboBox");
        ethicsComboBox = (ComboBox) scene.lookup("#ethicsComboBox");

        // Gender
        genderRectangle = (Rectangle) scene.lookup("#genderRectangle");
        genderMaleCircle = (Circle) scene.lookup("#genderMaleCircle");
        genderFemaleCircle = (Circle) scene.lookup("#genderFemaleCircle");
        genderMaleBack = (Circle) scene.lookup("#genderMaleBack");
        genderFemaleBack = (Circle) scene.lookup("#genderFemaleBack");


        alignmentComboBox.setItems(FXCollections.observableArrayList( Alignment.values()));
        ethicsComboBox.setItems(FXCollections.observableArrayList( Ethics.values()));

        Thread characterThread = new Thread(characterSql);
        characterThread.run();
    }

    @Override
    public void show() {
        stage.show();
        ready();
    }

    @Override
    public void hide() {
//        clearFields();
        stage.hide();
    }

    @Override
    public void setButtonListener(String button, EventHandler<? super MouseEvent> listener) {
        switch (button) {
            case "createCharacterButton":
                createCharacterButton.setOnMouseClicked(listener);
                break;
            case "cancelButton":
                cancelButton.setOnMouseClicked(listener);
                break;
            case "genderButton":
                genderRectangle.setOnMouseClicked(listener);
                genderFemaleCircle.setOnMouseClicked(listener);
                genderMaleCircle.setOnMouseClicked(listener);
                genderMaleBack.setOnMouseClicked(listener);
                genderFemaleBack.setOnMouseClicked(listener);
                break;
            case "portraitButton":
                portraitButton.setOnMouseClicked(listener);
                break;
        }
    }

    @Override
    public void setButtonListener(String comboBox, ChangeListener changeListener) {
        switch (comboBox) {
            case "classSelected":
                classComboBox.valueProperty().addListener(changeListener);
                break;
            case "raceSelected":
                raceComboBox.valueProperty().addListener(changeListener);
        }
    }

    /**
     * Starts whenever a new class has been selected in the classComboBox
     */
    public void classChanged() {
        String selectedClass = classComboBox.getSelectionModel().getSelectedItem().toString();
        characterClass.selectClass(selectedClass);
    }

    /**
     * Starts whenever a new race has been selected in the raceComboBox
     */
    public void raceChanged() {
        String selectedRace = raceComboBox.getSelectionModel().getSelectedItem().toString();
        characterRace.selectRace(selectedRace);
    }

    /**
     * Changes the gender
     */
    public void changeGender() {
        if (genderMaleCircle.isVisible()) {
            genderMaleCircle.setVisible(false);
            genderFemaleCircle.setVisible(true);
        }
        else {
            genderMaleCircle.setVisible(true);
            genderFemaleCircle.setVisible(false);
        }
    }


    /**
     * The characterSql class starts in the constructor of this class in a new Thread, where it fills
     * the raceArrayList and the classArrayList.
     *
     * When this layout shows it runs the ready() method and fills the comboBoxes
     */
    public void ready() {
        ArrayList<String> raceArrayList = characterSql.getRaces();
        ObservableList<String> raceObservable = FXCollections.observableArrayList(raceArrayList);
        raceComboBox.setItems(raceObservable);

        ArrayList<String> classArrayList = characterSql.getClasses();
        ObservableList<String> classObservable = FXCollections.observableArrayList(classArrayList);
        classComboBox.setItems(classObservable);
    }


    /**
     * Retrieves data from textFields and comboBoxes to make a new character.
     * Starts when the character has filled all fields and pressed the creation button.
     *
     * @return a Character object with all properties already set
     */
    public Character createCharacter() {
        String playerName = playerNameTextField.getText();
        String characterName = characterNameTextField.getText();

        String alignmentString = alignmentComboBox.getSelectionModel().getSelectedItem().toString();
        String ethicsString = ethicsComboBox.getSelectionModel().getSelectedItem().toString();
        Alignment alignment = Alignment.valueOf(alignmentString);
        Ethics ethics = Ethics.valueOf(ethicsString);

        GenderEnum gender;

        if (genderMaleCircle.isVisible())
            gender = GenderEnum.MALE;
        else
            gender = GenderEnum.FEMALE;

        return new Character(playerName, characterName, characterRace, characterClass, alignment, ethics, gender, imagePath);
    }

    public void setPortrait(String imagePath) {
        this.imagePath = imagePath;
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(imagePath));
            image = (new Image(is));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clearFields() {
        raceComboBox.getSelectionModel().clearSelection();
        classComboBox.getSelectionModel().clearSelection();
        characterNameTextField.clear();
        playerNameTextField.clear();
        ethicsComboBox.getSelectionModel().clearSelection();
        alignmentComboBox.getSelectionModel().clearSelection();
        imagePath = null;
    }


    /**
     * Checks if all fields are filled such as name, race and class
     *
     * @return true if all fields are filled
     */
    public boolean areFieldsFilled() {
        return !(raceComboBox.getSelectionModel().isEmpty() || classComboBox.getSelectionModel().isEmpty()
                || characterNameTextField.getText().isEmpty() || playerNameTextField.getText().isEmpty()
                || ethicsComboBox.getSelectionModel().isEmpty() || alignmentComboBox.getSelectionModel().isEmpty()
                || imagePath == null);
    }
}
