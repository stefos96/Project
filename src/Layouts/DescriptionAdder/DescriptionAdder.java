package Layouts.DescriptionAdder;
import Layouts.ViewInterface;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.io.IOException;

public class DescriptionAdder implements ViewInterface{

    private Scene scene;
    private Stage stage;

    private SVGPath backButton;
    private SVGPath nextButton;
    private SVGPath descriptionButton;

    private GridPane gridPane;

    public DescriptionAdder() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DescriptionAdder.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);

        nextButton = (SVGPath) scene.lookup("#nextButton");
        backButton = (SVGPath) scene.lookup("#backButton");
        descriptionButton = (SVGPath) scene.lookup("#descriptionButton");

        gridPane = (GridPane) scene.lookup("#gridPane");
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
            case "backButton":
                backButton.setOnMouseClicked(listener);
            case "nextButton":
                nextButton.setOnMouseClicked(listener);
            case "descriptionButton":
                descriptionButton.setOnMouseClicked(listener);
        }
    }
}
