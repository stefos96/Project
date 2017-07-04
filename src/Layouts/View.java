package Layouts;
import Layouts.Character.CharacterLayout;
import Layouts.CreateCharacter.CreateCharacter;
import Layouts.Inventory.InventoryLayout;
import Layouts.Lobby.Lobby;
import Layouts.MainGame.MainGame;
import Layouts.Menu.MainMenu;
import java.util.HashMap;

public class View {
    private HashMap<String, ViewInterface> viewInterfaceHashMap = new HashMap<>();

    public View(){
        try {
            viewInterfaceHashMap.put("Menu", new MainMenu());
            viewInterfaceHashMap.put("MainGame", new MainGame());
            viewInterfaceHashMap.put("Lobby", new Lobby());
            viewInterfaceHashMap.put("Inventory", new InventoryLayout());
            viewInterfaceHashMap.put("CreateCharacter", new CreateCharacter());
            viewInterfaceHashMap.put("Character", new CharacterLayout());
        }
        catch (Exception e){}
    }

    public ViewInterface getView(String view){
        return viewInterfaceHashMap.get(view);
    }
}
