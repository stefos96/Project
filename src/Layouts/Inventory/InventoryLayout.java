package Layouts.Inventory;
import Layouts.ViewInterface;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import java.io.IOException;

public class InventoryLayout implements ViewInterface{
    private Stage stage;
    private Scene scene;
    private SVGPath backButton;

    public InventoryLayout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InventoryLayout.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);

        backButton = (SVGPath) scene.lookup("#backButton");
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
        }
    }

    @Override
    public void setButtonListener(String comboBox, ChangeListener changeListener) {
    }
}