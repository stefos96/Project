package Scenes;
import static Game.MapCreation.sceneList;
import Items.Item;
import Monsters.Monster;
import java.util.HashMap;


public class Scene {
    private Integer leftRoom;
    private Integer rightRoom;
    private Integer upRoom;
    private Integer downRoom;
    private String description = "";
       
    public static int activeRoom = 0;
    
    public HashMap<String, Item> itemMap = new HashMap<>();
    private Monster roomMonster;
    // public HashMap<String, Monster> monsterMap = new HashMap<>();
    
    
    public boolean checkIfNullMonster(){
        return sceneList.get(activeRoom).roomMonster == null;
    }
    
   /*
    * If there is a monster in the room it prints it's name
    */
    public String printMonster(){
            if(!checkIfNullMonster()) {
                String printedName = sceneList.get(activeRoom).roomMonster.getName();
                if (printedName.contains("_"))
                    printedName = printedName.replace("_"," ");
                return ("You see a " + printedName + "\n");
            }
            return "";
    }
    
   /*
    * @returns the Monster object
    */
    public Monster getMonster(){
            return sceneList.get(activeRoom).roomMonster;
    }

   /*
    * Kills the monster
    */
    public void removeMonster(){
        sceneList.get(activeRoom).roomMonster = null;
    }

   /*
    * Vazei ena teras sto domatio (kaleitai ap tin MapCreation)
    */
    public void setMonster(Monster monster){
            this.roomMonster = monster;
    }

   /*
    * @Returns the index of the next room depending on the orientation parameter
    */
    private Integer getNextDoorIndex(String orientation){
        switch(orientation){
            case "WEST": return sceneList.get(activeRoom).leftRoom;
            case "EAST": return sceneList.get(activeRoom).rightRoom;
            case "SOUTH": return sceneList.get(activeRoom).downRoom;
            case "NORTH": return sceneList.get(activeRoom).upRoom;
            default: return null;
        }
    }
        
   /*
    * Sets room items
    */
    public void setItem(Item item, String hashcode){
        this.itemMap.put(hashcode, item);        
    }    
    
   /*
    * Removes item from room
    */
    public void removeItem(String hashcode){
        sceneList.get(activeRoom).itemMap.remove(hashcode);
    }    
    
   /*
    * Prints room items
    */
    public String getRoomItems(){
        String roomItems = "";
        try{
            for(String key : sceneList.get(activeRoom).itemMap.keySet()){
               if(!key.equals(""))
                roomItems += key + " ";
            }
            if(!roomItems.equals(""))
                return ("You see: " + roomItems + "\n");
        }
        catch(Exception e){
        }
        return "";
    }
    
    
   /*
    * Returns: possible ways you can go
    */
    public String getDoorNumber(){
        String orientation = "";
        Integer count = 0;
        if(sceneList.get(activeRoom).leftRoom != null){
            orientation += "west";
            count++;
        }
        if(sceneList.get(activeRoom).rightRoom != null){
            if (count != 0)
                orientation += "+east";
            else
                orientation += "east";
            count++;
        }
        if(sceneList.get(activeRoom).upRoom != null){
            if (count != 0)
                orientation += "+north";
            else
                orientation += "north";
            count++;
        }
        if(sceneList.get(activeRoom).downRoom != null){
            if (count != 0)
                orientation += "+south";
            else
                orientation += "south";
            count++;
        }
        if (count == 2){
            orientation = orientation.replace("+", " and ");
            return "You can go " + orientation;
        }
        else if (count > 2){ 
            int t;
            for(t=0;t<count-1;t++){
                orientation = orientation.replace("+", ", ");
            }
            return "You can go " + orientation;
        }
        else
            return "There is only one way possible: " + orientation;
    }
    
    
    /*
     * Enters room
     * @param an orientation String like "NORTH"
     * @returns true if you successfully entered the room
     */
    public boolean enterRoom(String orientation){
        Integer index = getNextDoorIndex(orientation);
        if (index != null){
                    activeRoom = index;
                    return true;
        }
        return false;
   }
        
    

   /*
    * Connects 2 rooms horizontaly in the ArrayList
    */
    public void createHorizontalDoor(Scene a, int position){
        this.rightRoom = position;
        a.leftRoom = position - 1;
    }

    
   /*
    * Connects 2 rooms vertically in the ArrayList
    */
    public void createVerticalDoor(Scene a, int position, int count){
        this.downRoom = position + count;
        a.upRoom = position;
    }

    public void setDescription(String description){
        this.description = description;
    }
    
    public String getDescription(){
        String descr = sceneList.get(activeRoom).description;
        if (descr.equals(""))
            return "";
        return descr + "\n";
    }
    
    
}
