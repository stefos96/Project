package Game;
import static Game.FileReader.roomList;
import Monsters.Monster;
import java.util.HashMap;
import java.util.Scanner;


public class Room {
    private boolean isLocked;
    private String doorLock;
    
    private Integer leftRoom;
    private Integer rightRoom;
    private Integer upRoom;
    private Integer downRoom;
       
    public static int activeRoom = 0;
    
    public HashMap<String, Item> itemMap = new HashMap<>();
    private Monster roomMonster;
    // public ArrayList<Monster> monsterList = new ArrayList<>();    
    // public HashMap<String, Monster> monsterMap = new HashMap<>();
    
    
    public boolean checkIfNullMonster(){
        if(roomList.get(activeRoom).roomMonster == null)
            return true;
        return false;
    }
    
   /*
    * Ektuponei AN uparxei teras sto domatio
    */
    public void printMonster(){
            if(!checkIfNullMonster())
                System.out.println("There's a "+ roomList.get(activeRoom).roomMonster.getClass().getSimpleName() +" in the room");
    }
    
   /*
    * epistrefei to teras stin UserInput
    */
    public Monster getMonster(){
            checkIfNullMonster();
            return roomList.get(activeRoom).roomMonster;
    }

   /*
    * Vazei ena teras sto domatio (kaleitai ap tin FileReader)
    */
    public void setMonster(Monster monster){
            this.roomMonster = monster;
    }

   /*
    * Epistrefei to index tou epomenou domatio sto ArrayList analoga me tin kateuthinsi pou tou dothike
    */
    public Integer getNextDoorIndex(String orientation){
        orientation = orientation.toLowerCase();
        switch(orientation){
            case "left": return roomList.get(activeRoom).leftRoom;                
            case "right": return roomList.get(activeRoom).rightRoom;   
            case "down": return roomList.get(activeRoom).downRoom;   
            case "up": return roomList.get(activeRoom).upRoom;   
            default: return roomList.get(activeRoom).upRoom;
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
        roomList.get(activeRoom).itemMap.remove(hashcode);
    }    
    
   /*
    * Prints room items
    */
    public void getRoomItems(){
        String roomItems = "";
        try{
            for(String key : roomList.get(activeRoom).itemMap.keySet()){
               if(!key.equals(""))
                roomItems += key + " ";
            }
            if(!roomItems.equals(""))
                System.out.println("Βλέπεις: " + roomItems + ".");
        }
        catch(Exception e){}
    }
    
    
      
    
    
   /*
    * Vazei kodiko gia to domatio index
    */ 
    public void setDoorLock(String password){
        this.doorLock = password;
        this.isLocked = true;
    }
    
    
    
    
    
   /*
    * Afairei ton kodiko gia to domatio index
    */ 
    public void removeLock(int index){
        roomList.get(index).isLocked = false;
    } 
    
    
    
    
    /*
    * Axreiasta megali methodos opou polu apla vlepei poies portes einai diathesimes sto activeRoom
    * Returns: Doors in the Room Message
    */
    public String getDoorNumber(){
        String orientation = "";
        Integer count = 0;
        if(roomList.get(activeRoom).leftRoom != null){
            if (count != 0)
                orientation += "+αριστερά";
            else
                orientation += "αριστερά";
            count++;
        }
        if(roomList.get(activeRoom).rightRoom != null){
            if (count != 0)
                orientation += "+δεξιά"; 
            else
                orientation += "δεξιά";
            count++;
        }
        if(roomList.get(activeRoom).upRoom != null){
            if (count != 0)
                orientation += "+πάνω";
            else
                orientation += "πάνω";
            count++;
        }
        if(roomList.get(activeRoom).downRoom != null){
            if (count != 0)
                orientation += "+κάτω";
            else
                orientation += "κάτω";
            count++;
        }
        if (count == 2){
            orientation = orientation.replace("+", " και ");
            return "Υπάρχουν " + count.toString() + " πόρτες στο δωμάτιο, " + orientation + ".";
        }
        else if (count > 2){ 
            int t;
            for(t=0;t<count-1;t++){
                orientation = orientation.replace("+", ", ");
            }
            return "Υπάρχουν " + count.toString() + " πόρτες στο δωμάτιο, " + orientation + ".";
        }
        else
            return "Υπάρχει μια πόρτα στο δωμάτιο από " + orientation + " σου.";
    }
    
    
    
    
    
    /*
    * Ksekleidonei to domatio an einai kleidomeno zitontas ap ton xristi na dosei kodiko
    */
    public void unlock(int roomToGO){
        String input;
        Scanner sc = new Scanner(System.in);
        System.out.println("The room is locked, give password, or STOP to stop typing.");      
        while(true){
            input = sc.nextLine().toLowerCase();
            if(input.equals("stop")){
                System.out.println("You stopped typing.");
                break;
            }
            if(input.equals(roomList.get(roomToGO).doorLock)){
                roomList.get(roomToGO).isLocked = false;
                System.out.println("Κωδικός δεκτός, μπήκες στο δωμάτιο."); 
                break;
            }
            else 
                System.out.println("Λάθος κωδικός."); 
        }
    }
    
    
    
    
    // VERSION 2.0
    
    
    public boolean enterRoomVer2(String orientation){
        orientation = orientation.replace("GO ", "").toLowerCase();
        Integer index = getNextDoorIndex(orientation);
        if (index != null){
            if (roomList.get(index).isLocked){
                roomList.get(index).unlock(index);
                if (!roomList.get(index).isLocked){
                    activeRoom = index;
                    return true;
                }
            }
            else{
                activeRoom = index;
                return true;
            }
        }
            return false;
   }
        
    
    
    /*
    * Sundeei orizontia 2 domatia
    * Ousiastika leei se poia thesi sto Array einai to kontino domatio orizontia
    */
    public void createHorizontalDoor(Room a, int position){
        this.rightRoom = position;
        a.leftRoom = position - 1;
    }
    
    
    
    /*
    * Sundeei katheta 2 domatia
    * Ousiastika leei se poia thesi sto Array einai to kontino domatio katheta
    */
    public void createVerticalDoor(Room a, int position, int count){
        this.downRoom = position + count;
        a.upRoom = position;
    }
    
    
    
    
}
