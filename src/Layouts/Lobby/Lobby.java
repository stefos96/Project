package Layouts.Lobby;
import Layouts.ViewInterface;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Collection;

public class Lobby implements ViewInterface{
    private Scene scene;
    private Stage stage;

    private ListView listView;
    private SVGPath backButton;
    private SVGPath joinButton;
    private SVGPath dmButton;

    public Lobby() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);

        listView = (ListView) scene.lookup("#listView");

        backButton = (SVGPath) scene.lookup("#backButton");
        dmButton = (SVGPath) scene.lookup("#dmButton");
        joinButton = (SVGPath) scene.lookup("#joinButton");
    }

    public void setListView(ObservableList list){
        listView.setItems(list);
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
            case "dmButton":
                dmButton.setOnMouseClicked(listener);
            case "joinButton":
                joinButton.setOnMouseClicked(listener);
        }
    }

    @Override
    public void setButtonListener(String comboBox, ChangeListener changeListener) {

    }
}
