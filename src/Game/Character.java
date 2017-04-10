package Game;
import Battle.Battle;
import java.util.HashMap;
import static Game.FileReader.roomList;
import Items.Equipment;
import Items.Item;
import Monsters.Monster;


public class Character{
 
    private int health = 10;
    private int armor = 1;
    private int currentLife = 10;
    private int dmg = 5;
    private int level = 1;
    private int xp = 0;
    private int xpToNextLvl = 30;
    private HashMap<String, Equipment> equipment = new HashMap<>();
    private HashMap<String, Item> inventory = new HashMap<>();
    
    
    
   /*
    *  Prints equipment
    */
    public void printEquipment(){
        String equiped = "";
        try{
            for(String key : equipment.keySet()){
               if(!key.equals(""))
                equiped += key + " ";
            }
            if(!equiped.equals(""))
                System.out.println("Φοράς: " + equiped + ".");          
        }
        catch(Exception e){}
    }
    
    /*
     * Apothikeuei Items sto Inventory
     */
    public void equip(String itemName){
        itemName = itemName.toLowerCase();
        itemName = itemName.replace("equip ", "");
        if(roomList.get(Room.activeRoom).itemMap.containsKey(itemName)){
            equipment.put(itemName, (Equipment) roomList.get(Room.activeRoom).itemMap.get(itemName));
            this.armor += equipment.get(itemName).getExtraArmor();
            this.dmg += equipment.get(itemName).getExtraDmg();
            roomList.get(Room.activeRoom).itemMap.remove(itemName);
            System.out.println(itemName + " equiped");
        }
        else
            System.out.println(itemName + " not found");
    }
    
    
    
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
    
    public void dropItem(String itemName){
        itemName = itemName.toLowerCase();
        itemName = itemName.replace("drop ", "");
        if(inventory.containsKey(itemName)){
            roomList.get(Room.activeRoom).itemMap.put(itemName, inventory.get(itemName));
            inventory.remove(itemName);
            System.out.println("You droped " + itemName);
        }
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
        health += 5;
        xp = exp;
        currentLife = health;
        xpToNextLvl += 15;
    }

   /*
    * Get methods
    */
    public int getCurrentLife(){
        return currentLife;
    }

    public int getDmg(){
        return dmg;
    }
    
    public int getArmor(){
        return armor;
    }
    
    public void printStats(){
        System.out.println("Current Health: " + this.currentLife);
        System.out.println("Damage: " + this.dmg);
        System.out.println("Armor: " + this.armor);
        System.out.println("Current Level: " + this.level);
        System.out.println("Current experience: " + this.xp);
        System.out.println("Experience to next Level: " + (this.xpToNextLvl - this.xp));
    }


}
