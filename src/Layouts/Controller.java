 package Layouts;
import Layouts.Activity.ActivityLayout;
import Layouts.Character.CharacterLayout;
import Layouts.CreateCharacter.CreateCharacter;
import Layouts.Help.HelpLayout;
import Layouts.Inventory.InventoryLayout;
import Layouts.Lobby.Lobby;
import Layouts.MainGame.MainGame;
import Layouts.MapCreation.MapCreationLayout;
import Layouts.Menu.MainMenu;
import Layouts.MonsterInsertion.MonsterInsertion;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.awt.*;

 /**
 * Controller of the MVC pattern that connects the Views with the Model
 */
public class Controller {
    private ViewInterface menu;
    private ViewInterface mainGame;
    private ViewInterface session;
    private ViewInterface inventory;
    private ViewInterface createCharacter;
    private ViewInterface character;
    private ViewInterface help;
    private ViewInterface mapCreation;
    private ViewInterface monsterInsertion;
    private ViewInterface activity;

    public Controller(Model model) throws Exception {
        menu = new MainMenu();
        mainGame = new MainGame();
        session = new Lobby();
        inventory = new InventoryLayout();
        createCharacter = new CreateCharacter();
        character = new CharacterLayout();
        help = new HelpLayout();
        mapCreation = new MapCreationLayout();
        monsterInsertion = new MonsterInsertion();
        activity = new ActivityLayout();

        menu.show();

        // Menu
        menu.setButtonListener("enterGameButton", new EnterGameListener());
        menu.setButtonListener("createCharacterButton", new CreateCharacterListener());
        menu.setButtonListener("sessionButton", new SessionListener());
        menu.setButtonListener("exitButton", new ExitListener());
        menu.setButtonListener("helpButton", new HelpListener());

        // Main game
        mainGame.setButtonListener("inventoryButton", new InventoryListener());
        mainGame.setButtonListener("characterButton", new CharacterListener());

        // Create character
        createCharacter.setButtonListener("cancelButton", new CreateCharacterCancelListener());

        // Character
        character.setButtonListener("backButton", new CharacterBackListener());

        // Inventory
        inventory.setButtonListener("backButton", new InventoryBackListener());

        // Session
        session.setButtonListener("backButton", new SessionBackListener());
        session.setButtonListener("joinButton", new JoinListener());
        session.setButtonListener("dmButton", new DMButtonListener());

        // MapCreation
        mapCreation.setButtonListener("addRowButton", new AddRowListener());
        mapCreation.setButtonListener("addColumnButton", new AddColumnListener());
        mapCreation.setButtonListener("removeColumnButton", new RemoveColumnListener());
        mapCreation.setButtonListener("removeRowButton", new RemoveRowListener());

        mapCreation.setButtonListener("cancelButton", new MapCreationCancelListener());
        mapCreation.setButtonListener("nextButton", new MapCreationNextListener());

        mapCreation.setButtonListener("gridPane", new MapCreationGridListener());

        mapCreation.setButtonListener("fieldLabel", new MapCreationFieldListener());
        mapCreation.setButtonListener("cliffLabel", new MapCreationCliffListener());
        mapCreation.setButtonListener("treeLabel", new MapCreationTreeListener());
        mapCreation.setButtonListener("hillLabel", new MapCreationHillListener());
        mapCreation.setButtonListener("waterLabel", new MapCreationWaterListener());
        mapCreation.setButtonListener("voidLabel", new MapCreationVoidListener());


        // MonsterInsertion
        monsterInsertion.setButtonListener("backButton", new MonsterInsertionBackListener());
        monsterInsertion.setButtonListener("joinButton", new MonsterInsertionNextListener());
    }

    // Menu
    private class EnterGameListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            menu.hide();
            mainGame.show();
        }
    }

    private class CreateCharacterListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            menu.hide();
            createCharacter.show();
        }
    }

    private class SessionListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            menu.hide();
            session.show();
        }
    }

    private class HelpListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            help.show();
        }
    }

    private class ExitListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            System.exit(0);
        }
    }

    // MainGame
    private class InventoryListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            mainGame.hide();
            inventory.show();
        }
    }

    private class CharacterListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            mainGame.hide();
            character.show();
        }
    }

    // CreateCharacter
    private class CreateCharacterCancelListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            createCharacter.hide();
            menu.show();
        }
    }

    // Character
    private class CharacterBackListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            character.hide();
            mainGame.show();
        }
    }

    // Inventory
    private class InventoryBackListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            inventory.hide();
            mainGame.show();
        }
    }

    // Session
    private class SessionBackListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            session.hide();
            menu.show();
        }
    }

    private class JoinListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            // TODO implement
        }
    }

    private class DMButtonListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            session.hide();
            mapCreation.show();
        }
    }

    // MapCreation
    private class AddColumnListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            ((MapCreationLayout) mapCreation).addColumn();
        }
    }

    private class AddRowListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            ((MapCreationLayout) mapCreation).addRow();
        }
    }

    private class RemoveRowListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            ((MapCreationLayout) mapCreation).removeRow();
        }
    }

    private class RemoveColumnListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            ((MapCreationLayout) mapCreation).removeColumn();
        }
    }

    private class MapCreationCancelListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            mapCreation.hide();
            session.show();
        }
    }

    private class MapCreationNextListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            mapCreation.hide();
            monsterInsertion.show();
        }
    }

    private class MapCreationGridListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            ((MapCreationLayout) mapCreation).selectInGrid(event.getSceneX(), event.getSceneY());
        }
    }

    // Map Creation Tools
    private class MapCreationFieldListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            ((MapCreationLayout) mapCreation).addField();
        }
    }

    private class MapCreationCliffListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
        }
    }

    private class MapCreationTreeListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
        }
    }

    private class MapCreationHillListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
        }
    }

    private class MapCreationWaterListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            ((MapCreationLayout) mapCreation).addWater();
        }
    }

    private class MapCreationVoidListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            ((MapCreationLayout) mapCreation).addVoid();
        }
    }

    // MonsterCreation
    private class MonsterInsertionBackListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            monsterInsertion.hide();
            mapCreation.show();
        }
    }

    private class MonsterInsertionNextListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            // TODO implement
        }
    }
}