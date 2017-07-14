package Layouts.CreateCharacter;
import Layouts.ViewInterface;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import java.io.IOException;

public class CreateCharacter implements ViewInterface {
    private Scene scene;
    private Stage stage;

    private SVGPath createCharacterButton;
    private SVGPath cancelButton;
    private TextField playerNameTextField;
    private TextField characterNameTextField;
    private ComboBox raceComboBox;
    private ComboBox classComboBox;
    private ComboBox genderComboBox;
    private TextField heightTextField;
    private TextField weightTextField;

    public CreateCharacter() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CreateCharacter.fxml"));

        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);

        playerNameTextField = (TextField) scene.lookup("#playerNameTextField");
        characterNameTextField = (TextField) scene.lookup("#characterNameTextField");
        createCharacterButton = (SVGPath) scene.lookup("#createCharacterButton");
        cancelButton = (SVGPath) scene.lookup("#cancelButton");
        raceComboBox = (ComboBox) scene.lookup("#raceComboBox");
        classComboBox = (ComboBox) scene.lookup("#classComboBox");
        genderComboBox = (ComboBox) scene.lookup("#genderComboBox");
        heightTextField = (TextField) scene.lookup("#heightTextField");
        weightTextField = (TextField) scene.lookup("#weightTextField");
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
        }
    }
}
