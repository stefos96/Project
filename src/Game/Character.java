package Game;
import java.util.HashMap;
import static Game.FileReader.roomList;


public class Character {
 
    private int life = 10;
    private boolean infected = false;
    private HashMap<String, Item> inventory = new HashMap<>();
    
    
    /*
     * Apothikeuei Items sto Inventory
     */
    public void storeItem(String itemName){        
        itemName = itemName.toLowerCase();
        itemName = itemName.replace("pick ", "");
        if(roomList.get(Room.activeRoom).itemMap.containsKey(itemName)){
            inventory.put(itemName,roomList.get(Room.activeRoom).itemMap.get(itemName));            
            roomList.get(Room.activeRoom).itemMap.remove(itemName);
            System.out.println(itemName + " stored in inventory");
        }
        else
            System.out.println(itemName + " not found");
    }
    
    
    
    /*
     * Prints Items in Inventory
     */
    public void viewInventory(){
        String inventoryItems = "";
        for(String key : this.inventory.keySet())
               inventoryItems += key + " ";
        System.out.println("Έχεις στο σάκο σου: " + inventoryItems);
    }
    
           
    
        
    public void unlockDoor(String orientation){
        orientation = orientation.replace("UNLOCK ", "");
        Integer index = roomList.get(Room.activeRoom).getNextDoorIndex(orientation);
        if (index == null) index = 0;
        for(String key : this.inventory.keySet()){
            if((key.contains("key")) && (this.inventory.get(key).getIndexToUnlock().equals(index))){
                    roomList.get(index).removeLock(index);
                    System.out.println("You unlocked the room!");
            }
        }
    }
    
    
    
    
}
