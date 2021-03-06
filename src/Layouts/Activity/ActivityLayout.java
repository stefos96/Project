package Layouts.Activity;
import Layouts.ViewInterface;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class ActivityLayout implements ViewInterface{
    private Scene scene;
    private Stage stage;
    private ScrollPane scrollPane;

    public ActivityLayout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Activity.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);

        scrollPane = (ScrollPane) scene.lookup("#scrollPane");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
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

    }

    @Override
    public void setButtonListener(String comboBox, ChangeListener changeListener) {

    }
}
