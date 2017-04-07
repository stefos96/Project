package Game;
import Battle.Battle;
import java.util.HashMap;
import static Game.FileReader.roomList;
import Monsters.Monster;


public class Character{
 
    private int life = 10;
    private int currentLife = 10;
    private int dmg = 5;
    private int level = 1;
    private int xp = 0;
    private int xpToNextLvl = 30;
    // private boolean infected = false;
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

    public void attack(Monster monster){
        Battle fight = new Battle();
        int result = fight.enterBattle(this, monster);
        if (currentLife <= 0)
            System.out.println("You died!");
        else{
            System.out.print("You killed the " + monster.getClass().getSimpleName());
            System.out.println(" and gained " + monster.getXp() + " xp");
            addXp(result);
        }
    }

    public void addXp(int experience){
        xp += experience;
        if (xp >= xpToNextLvl){
            int exp = xp - xpToNextLvl;
            levelUp(exp);
        }
    }

    public void refreshHealth(int health){
        this.currentLife = health;        
    }

    public void levelUp(int exp){
        level++;
        dmg++;
        life += 5;
        xp = exp;
        currentLife = life;
        xpToNextLvl += 15;
    }

    public int getCurrentLife(){
        return currentLife;
    }

    public int getDmg(){
        return dmg;
    }
    
    public void printStats(){
        System.out.println("Current Health: " + this.currentLife);
        System.out.println("Current Level: " + this.level);
        System.out.println("Current experience: " + this.xp);
        System.out.println("Experience to next Level: " + (this.xpToNextLvl - this.xp));
    }


}
