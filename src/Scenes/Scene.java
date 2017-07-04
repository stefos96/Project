package Scenes;
import static MapCreation.MapCreation.sceneList;


public class Scene {
    private Integer leftRoom;
    private Integer rightRoom;
    private Integer upRoom;
    private Integer downRoom;
    private String description = "";
       
    public static int activeRoom = 0;
    


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
