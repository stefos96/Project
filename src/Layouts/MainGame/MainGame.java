package Layouts.MainGame;
import Layouts.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

public class MainGame implements ViewInterface{
    private Scene scene;
    private Stage stage;

    private TextArea resultTextArea;
    private TextField chatTextField;
    private SVGPath inventoryButton;
    private SVGPath characterButton;

    public MainGame() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainGame.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);

        chatTextField = (TextField) scene.lookup("#chatTextField");
        resultTextArea = (TextArea) scene.lookup("#resultTextArea");
        characterButton = (SVGPath) scene.lookup("#characterButton");
        inventoryButton = (SVGPath) scene.lookup("#inventoryButton");
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
    public void setButtonListener(String button, javafx.event.EventHandler<? super MouseEvent> listener) {
        switch (button){
            case "characterButton":
                characterButton.setOnMouseClicked(listener);
                break;
            case "inventoryButton":
                inventoryButton.setOnMouseClicked(listener);
                break;
        }
    }
}
