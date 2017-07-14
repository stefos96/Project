 package Layouts;
import Layouts.Activity.ActivityLayout;
import Layouts.Character.CharacterLayout;
import Layouts.CreateCharacter.CreateCharacter;
import Layouts.DescriptionAdder.DescriptionAdder;
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
import java.util.ArrayList;

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
    private ViewInterface descriptionAdder;

    private Model model;

    public Controller(Model model) throws Exception {
        this.model = model;

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
        descriptionAdder = new DescriptionAdder();

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

        mapCreation.setButtonListener("gridPanePressed", new MapCreationGridPressed());
        mapCreation.setButtonListener("gridPaneReleased", new MapCreationGridReleased());


        mapCreation.setButtonListener("fieldLabel", new MapCreationFieldListener());
        mapCreation.setButtonListener("cliffLabel", new MapCreationCliffListener());
        mapCreation.setButtonListener("treeLabel", new MapCreationTreeListener());
        mapCreation.setButtonListener("hillLabel", new MapCreationHillListener());
        mapCreation.setButtonListener("waterLabel", new MapCreationWaterListener());
        mapCreation.setButtonListener("voidLabel", new MapCreationVoidListener());


        // MonsterInsertion
        monsterInsertion.setButtonListener("backButton", new MonsterInsertionBackListener());
        monsterInsertion.setButtonListener("nextButton", new MonsterInsertionNextListener());
        monsterInsertion.setButtonListener("gridPane", new MonsterInsertionGridPane());
        monsterInsertion.setButtonListener("listView", new MonsterInsertionListView());


        // DescriptionAdder
        descriptionAdder.setButtonListener("backButton", new DescriptionAdderBackListener());
        descriptionAdder.setButtonListener("nextButton", new DescriptionAdderNextListener());
        descriptionAdder.setButtonListener("descriptionButton", new DescriptionAdderListener());
        descriptionAdder.setButtonListener("gridPane", new DescriptionAdderGridPane());
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
            int columns = ((MapCreationLayout) mapCreation).getColumns();
            int rows = ((MapCreationLayout) mapCreation).getRows();

            model.updateArraySize(columns, rows);
            model.setRectangleArray(((MapCreationLayout) mapCreation).getRectangleArray());

            ((MonsterInsertion) monsterInsertion).setColumns(columns);
            ((MonsterInsertion) monsterInsertion).setRows(rows);

            mapCreation.hide();
            monsterInsertion.show();
        }
    }



    // Map Creation Selecting the grid
    private class MapCreationGridPressed implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            ((MapCreationLayout) mapCreation).selectInGrid(event.getSceneX(), event.getSceneY());
        }
    }

     private class MapCreationGridReleased implements EventHandler<MouseEvent> {
         @Override
         public void handle(MouseEvent event) {
             ((MapCreationLayout) mapCreation).releaseInGrid(event.getSceneX(), event.getSceneY());
         }
     }



    // Map Creation Tools
    private class MapCreationFieldListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            ((MapCreationLayout) mapCreation).addTerrain("FIELD");
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
            ((MapCreationLayout) mapCreation).addTerrain("WATER");
        }
    }

    private class MapCreationVoidListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            ((MapCreationLayout) mapCreation).addTerrain("VOID");
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
            ArrayList<ArrayList<String>> temp = ((MonsterInsertion) monsterInsertion).getMonsterArray();
            model.setMonsterArray(temp);

            ((DescriptionAdder) descriptionAdder).setColumns(model.getColumns());
            ((DescriptionAdder) descriptionAdder).setRows(model.getRows());

            monsterInsertion.hide();
            descriptionAdder.show();
        }
    }

     private class MonsterInsertionGridPane implements EventHandler<MouseEvent> {
         @Override
         public void handle(MouseEvent event) {
             ((MonsterInsertion) monsterInsertion).selectInGrid(event.getSceneX(), event.getSceneY());
         }
     }

     private class MonsterInsertionListView implements EventHandler<MouseEvent> {
         @Override
         public void handle(MouseEvent event) {
             ((MonsterInsertion) monsterInsertion).selectListView();
         }
     }

     // DescriptionAdder
     private class DescriptionAdderBackListener implements EventHandler<MouseEvent> {
         @Override
         public void handle(MouseEvent event) {
             descriptionAdder.hide();
             monsterInsertion.show();
         }
     }

     private class DescriptionAdderNextListener implements EventHandler<MouseEvent> {
         @Override
         public void handle(MouseEvent event) {
         }
     }

     private class DescriptionAdderListener implements EventHandler<MouseEvent> {
         @Override
         public void handle(MouseEvent event) {
             ((DescriptionAdder) descriptionAdder).addDescription();
         }
     }

     private class DescriptionAdderGridPane implements EventHandler<MouseEvent> {
         @Override
         public void handle(MouseEvent event) {
             ((DescriptionAdder) descriptionAdder).selectInGrid(event.getSceneX(), event.getSceneY());
         }
     }
}
