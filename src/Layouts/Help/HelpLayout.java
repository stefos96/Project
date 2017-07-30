package Layouts.Help;
import Layouts.ViewInterface;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import java.io.IOException;

public class HelpLayout implements ViewInterface {
    private Scene scene;
    private Stage stage;

    private SVGPath backButton;

    public HelpLayout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Help.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);

        ScrollPane scroller = (ScrollPane) scene.lookup("#scrollPane");
        scroller.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
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
