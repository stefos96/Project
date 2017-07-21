package Layouts.Menu;
import Layouts.ViewInterface;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.util.Duration;

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
        Image icon = new Image("/Extras/dnd_icon.png");
        stage.getIcons().add(icon);

        Group dice;
        dice = (Group) scene.lookup("#dice");
        dice.setScaleX(0.3);
        dice.setScaleY(0.3);
        dice.setLayoutX(-86);
        dice.setLayoutY(126);

        //Creating a rotate transition
        RotateTransition rotateTransition = new RotateTransition();

        //Setting the duration for the transition
        rotateTransition.setDuration(Duration.millis(800));

        //Setting the node for the transition
        rotateTransition.setNode(dice);

        //Setting the angle of the rotation
        rotateTransition.setByAngle(360);

        //Setting the cycle count for the transition
        rotateTransition.setCycleCount(100);

        //Setting auto reverse value to false
        rotateTransition.setAutoReverse(false);

        //Playing the animation
        rotateTransition.play();


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
}