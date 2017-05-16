package Game;
import Commands.Commands;
import Scenes.Scene;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Verbs.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Layout extends Application implements EventHandler<KeyEvent> {
    public static ArrayList<String> allCommands = new ArrayList<>();
    private Text scenetitle;
    private TextField commandTextField;
    private javafx.scene.Scene scene;
    private ScrollPane scrollPane;
    private Character Player1;
    private Scene Map1;
    private Commands parser = new Commands();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new MapCreation();
        // New Map and New player
        Player1 = new Character();
        Map1 = new Scene();
        // Main Grid Panel
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 10));

        // GridPane that contains rusultsText and scrollBar
        GridPane textGrid = new GridPane();
        textGrid.setPadding(new Insets(30, 0, 0, 0));
        grid.add(textGrid,0,12,45,10);

        // Results Text
        scenetitle = new Text("Welcome to our game\n");
        scenetitle.setId("scenetitle");
        scenetitle.setWrappingWidth(650);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        textGrid.add(scenetitle, 0, 2, 25, 5);

        // ScrollPane for text result
        scrollPane = new ScrollPane(scenetitle);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPadding(new Insets(0,0,0,10));
        scrollPane.setMinSize(686,350);
        textGrid.add(scrollPane,0,1,25,10);

        // Command parser
        commandTextField = new TextField();
        commandTextField.setId("commandTextField");
        commandTextField.setOnKeyPressed(this);
        commandTextField.setMinSize(686,45);
        grid.add(commandTextField, 0, 37,1,1);

        scene = new javafx.scene.Scene(grid, 700, 450);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Layout.class.getResource("Layout.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.show();

        // Welcome message
        scenetitle.setText(scenetitle.getText() + Map1.getDescription());
        scenetitle.setText(scenetitle.getText() + Map1.getDoorNumber() + "\n");
        scenetitle.setText(scenetitle.getText() + Map1.getRoomItems());
        scenetitle.setText(scenetitle.getText() + Map1.printMonster());
    }



    @Override
    public void handle(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
            try {
                String sentence = commandTextField.getText();
                // Adds the user's sentence in a string array
                allCommands.add(sentence);
                scenetitle.setText(scenetitle.getText() + "-------------\n > ");
                scenetitle.setText(scenetitle.getText() + sentence + "\n");
                scenetitle.setText(scenetitle.getText() + parser.commandParser(Map1, Player1, sentence) + "\n");
            } catch (Exception e) {
                scenetitle.setText(scenetitle.getText() + "Not an available command\n");
            }
            scrollPane.setVvalue(1.0);
            commandTextField.clear();
        }
    }
}