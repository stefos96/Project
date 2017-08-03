package Layouts.Character;
import Layouts.ViewInterface;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import java.io.IOException;
import Character.Character;

public class CharacterLayout implements ViewInterface{
    private Scene scene;
    private Stage stage;

    private ImageView imageView;

    private TextField characterName;
    private TextField player;
    private TextField classAndLevel;
    private TextField race;
    private TextField alignment;
    private TextField deity;
    private TextField size;
    private TextField age;
    private TextField gender;
    private TextField height;
    private TextField weight;
    private TextField eyes;
    private TextField hair;
    private TextField skin;
    private TextField speed;

    private Label strLabel;
    private Label dexLabel;
    private Label conLabel;
    private Label intLabel;
    private Label wisLabel;
    private Label chaLabel;

    private SVGPath strButton;
    private SVGPath dexButton;
    private SVGPath conButton;
    private SVGPath intButton;
    private SVGPath wisButton;
    private SVGPath chaButton;

    private SVGPath backButton;


    public CharacterLayout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CharacterLayout.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);

        imageView = (ImageView) scene.lookup("#imageView");

        // TextFields
        characterName = (TextField) scene.lookup("#characterName");
        player = (TextField) scene.lookup("#player");
        classAndLevel = (TextField) scene.lookup("#classAndLevel");
        race = (TextField) scene.lookup("#race");
        alignment = (TextField) scene.lookup("#alignment");
        deity = (TextField) scene.lookup("#deity");
        size = (TextField) scene.lookup("#size");
        age = (TextField) scene.lookup("#age");
        gender = (TextField) scene.lookup("#gender");
        height = (TextField) scene.lookup("#height");
        weight = (TextField) scene.lookup("#weight");
        eyes = (TextField) scene.lookup("#eyes");
        hair = (TextField) scene.lookup("#hair");
        skin = (TextField) scene.lookup("#skin");
        speed = (TextField) scene.lookup("#speed");

        // Buttons
        strButton = (SVGPath) scene.lookup("#strButton");
        dexButton = (SVGPath) scene.lookup("#dexButton");
        conButton = (SVGPath) scene.lookup("#conButton");
        intButton = (SVGPath) scene.lookup("#intButton");
        wisButton = (SVGPath) scene.lookup("#wisButton");
        chaButton = (SVGPath) scene.lookup("#chaButton");
        backButton = (SVGPath) scene.lookup("#backButton");

        // Labels
        strLabel = (Label) scene.lookup("#strLabel");
        dexLabel = (Label) scene.lookup("#dexLabel");
        conLabel = (Label) scene.lookup("#conLabel");
        intLabel = (Label) scene.lookup("#intLabel");
        wisLabel = (Label) scene.lookup("#wisLabel");
        chaLabel = (Label) scene.lookup("#chaLabel");
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

    public void setCharacter(Character character) {
//        Image portrait = character.getPortrait();
//        if (portrait != null) {
//            imageView = new ImageView(portrait);
//            imageView.setFitHeight(200);
//            imageView.setFitWidth(200);
//        }

        characterName.setText(character.getCharacterName());
        player.setText(character.getPlayerName());
        classAndLevel.setText(character.getCharacterClass().getName());
        race.setText(character.getRace().getName());

        alignment.setText(character.getAlignment() + " " + character.getEthics());

        size.setText(character.getSize());
        gender.setText(String.valueOf(character.getGender()));

        strLabel.setText(String.valueOf(character.getStrength()));
        dexLabel.setText(String.valueOf(character.getDexterity()));
        conLabel.setText(String.valueOf(character.getConstitution()));
        intLabel.setText(String.valueOf(character.getIntelligence()));
        wisLabel.setText(String.valueOf(character.getWisdom()));
        chaLabel.setText(String.valueOf(character.getCharisma()));
    }

}


