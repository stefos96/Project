package Layouts.ItemLayout;
import Item.Item;
import Layouts.ViewInterface;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class ItemLayout implements ViewInterface{
    private Scene scene;
    private Stage stage;

    private TextField nameTextField;
    private TextField categoryTextField;
    private TextField auraTextField;
    private TextField casterLevelTextField;
    private TextField priceTextField;
    private TextField weightTextField;

    private Label fullTextLabel;

    public ItemLayout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Map.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);

        nameTextField = (TextField) scene.lookup("#nameTextField");
        categoryTextField = (TextField) scene.lookup("#categoryTextField");
        auraTextField = (TextField) scene.lookup("#auraTextField");
        casterLevelTextField = (TextField) scene.lookup("#casterLevelTextField");
        priceTextField = (TextField) scene.lookup("#priceTextField");
        weightTextField = (TextField) scene.lookup("#weightTextField");

        fullTextLabel = (Label) scene.lookup("#fullTextLabel");

    }

    /**
     * Sets all the fields based on the item that you selected
     * @param item the item to display
     */
    public void setFields(Item item) {
        nameTextField.setText(item.getName());
        categoryTextField.setText(item.getCategory());
        auraTextField.setText(item.getAura());
        casterLevelTextField.setText(String.valueOf(item.getCasterLevel()));
        priceTextField.setText(String.valueOf(item.getPrice()));
        weightTextField.setText(String.valueOf(item.getWeight()));

        fullTextLabel.setText(item.getFullText());
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
