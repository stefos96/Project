package Layouts.Menu;
import Layouts.ViewInterface;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import java.io.IOException;

public class MainMenu implements ViewInterface{
    private Scene scene;
    private Stage stage;

    private SVGPath enterGameButton;
    private SVGPath createCharacterButton;
    private SVGPath sessionButton;
    private SVGPath exitButton;
    private TextField nameTextField;
    private Circle helpButton;

    public MainMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        scene = new Scene(root,430,600);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);


        nameTextField = (TextField) scene.lookup("#nameTextField");
        enterGameButton = (SVGPath) scene.lookup("#enterGameButton");
        createCharacterButton = (SVGPath) scene.lookup("#createCharacterButton");
        sessionButton = (SVGPath) scene.lookup("#sessionButton");
        exitButton = (SVGPath) scene.lookup("#exitButton");
        helpButton = (Circle) scene.lookup("#helpButton");
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
            case "enterGameButton":
                enterGameButton.setOnMouseClicked(listener);
            case "createCharacterButton":
                createCharacterButton.setOnMouseClicked(listener);
            case "sessionButton":
                sessionButton.setOnMouseClicked(listener);
            case "exitButton":
                exitButton.setOnMouseClicked(listener);
            case "helpButton":
                helpButton.setOnMouseClicked(listener);
        }
    }

    @Override
    public void setButtonListener(String comboBox, ChangeListener changeListener) {

    }
}