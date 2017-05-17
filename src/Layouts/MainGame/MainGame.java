package Layouts.MainGame;
import Commands.Commands;
import Game.Character;
import Game.MapCreation;
import Layouts.TextPrinter;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class MainGame extends VBox implements EventHandler<MouseEvent> {
    private Scene scene;

    public static ArrayList<String> allCommands = new ArrayList<>();
    private TextArea resultTextArea;
    private TextField commandParser;
    private Character Player1;
    private Scenes.Scene Map1;
    private Commands parser = new Commands();

    public MainGame(String name, String race) throws IOException {
        new MapCreation();
        // New Map and New player
        Player1 = new Character();
        Map1 = new Scenes.Scene();

        Parent root = FXMLLoader.load(getClass().getResource("MainGame.fxml"));
        Stage stage;
        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        SVGPath bubbleButton1 = (SVGPath) scene.lookup("#bubble1");
        bubbleButton1.setOnMouseClicked(this);

        commandParser = (TextField) scene.lookup("#parser");
        commandParser.setOnKeyPressed(new EnterPressed());
        resultTextArea = (TextArea) scene.lookup("#resultTextArea");
        // Welcome message

        String show = "Hello " + name + "\n" + Map1.getDescription();
        show += Map1.getDoorNumber() + "\n";
        show += Map1.getRoomItems();
        show += Map1.printMonster();
        Runnable a = new TextPrinter(resultTextArea, show);
        new Thread(a).start();
    }

    // click in a bubble button
    @Override
    public void handle(MouseEvent event) {
        resultTextArea.appendText("nope\n");
        resultTextArea.selectPositionCaret(resultTextArea.getLength() - 1);
        resultTextArea.deselect();
    }

    // Enter key event
    class EnterPressed implements EventHandler<KeyEvent>{
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode().equals(KeyCode.ENTER)){
                try {
                    String sentence = commandParser.getText();
                    // Adds the user's sentence in a string array
                    allCommands.add(sentence);
                    resultTextArea.appendText("-------------\n > ");
                    resultTextArea.appendText(sentence + "\n");
                    resultTextArea.appendText(parser.commandParser(Map1, Player1, sentence) + "\n");
                } catch (Exception e) {
                    resultTextArea.appendText("Not an available command\n");
                }
                resultTextArea.selectPositionCaret(resultTextArea.getLength() - 1);
                resultTextArea.deselect();
                commandParser.clear();
            }
        }
    }
}
