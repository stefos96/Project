package Game;
import java.util.HashMap;

import static Game.MapCreation.roomList;

import Items.Consumable;
import Items.Equipment;
import Items.Item;
import Monsters.Monster;


public class Character {

    private int health = 10;
    private int currentLife = 10;
    private int armor = 1;
    private int dmg = 5;
    private int level = 1;
    private int xp = 0;
    private int xpToNextLvl = 30;

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private int hitPoints;
    private int armorClass;

    private HashMap<String, Equipment> equipment = new HashMap<>();
    private HashMap<String, Item> inventory = new HashMap<>();

    /*
    *  Prints equipment
    */
    public String printEquipment() {
        String equiped = "";
        try {
            for (String key : equipment.keySet()) {
                if (!key.equals(""))
                    equiped += key + "(armor:" + equipment.get(key).getExtraArmor() +
                                     ",dmg:" + equipment.get(key).getExtraDmg() + ") ";
            }
            if (!equiped.equals("")) {
                equiped = equiped.replace("_"," ");
                return ("You have equiped: " + equiped + ".");
            }
            else{
                return null;
            }
        }
        catch (Exception e) {
            return null;
        }
    }

    /*
     * Removes equipment from player
     */
    public String unequipItem(String itemToUnequip){
        if (!itemToUnequip.equals(""))
            itemToUnequip = findItemName(itemToUnequip, equipment.toString());

//        Scanner myVar = new Scanner(System.in);
        if (!itemToUnequip.equals("")) {
            this.armor -= equipment.get(itemToUnequip).getExtraArmor();
            this.dmg -= equipment.get(itemToUnequip).getExtraDmg();
            inventory.put(itemToUnequip, equipment.get(itemToUnequip));
            equipment.remove(itemToUnequip);
            return ("You unequiped " + itemToUnequip.replace("_"," ") + " and stored it in your inventory.");
        }
        return null;
    }

    /*
     * Foraei opla kai panoplies pou uparxoun sto domatio i sto inventory
     */
    public String equip(String itemName) {
        itemName = itemName.toLowerCase();
        itemName = itemName.replace("equip ", "");
        String fullName = findItemName(itemName, roomList.get(Room.activeRoom).itemMap.toString());

        if (fullName.equals("")) {
            fullName = findItemName(itemName, inventory.toString());
            if(!fullName.equals(""))
                return equipOperation(itemName, fullName, inventory);
            else
                return (itemName + " not found");
        }
        else
            return equipOperation(itemName, fullName, roomList.get(Room.activeRoom).itemMap);
    }


    /*
     * gia na min kanei 2 fores elegxous kai idies diadikasies gia to inventory kai gia ta items pou
     * uparxoun sto domatio ekana autin tin methodo
     */
    public String equipOperation(String itemName, String fullName, HashMap<String, Item> eMap){
        try {
            // an to item einai tupou equipment
            if (eMap.get(fullName).getClass().getSimpleName().equals("Equipment")) {
                // an den foras idi eksoplismo idiou tupoy p.x. armor forese ton
                if (!equipment.keySet().toString().contains(itemName)) {
                    equipment.put(fullName, (Equipment) eMap.get(fullName));
                    this.armor += equipment.get(fullName).getExtraArmor();
                    this.dmg += equipment.get(fullName).getExtraDmg();
                    eMap.remove(fullName);
                    return (fullName + " equiped");
                }
                // an foras idi eksoplismo
                else {
                    unequipItem(itemName);
                    equipOperation(itemName, fullName, eMap);
                    return ("You already wear " + itemName + "\n" + "You want to replace it?");
                }
            }
            else
                return ("Item can't be equiped");
        }
        catch (Exception e){
            return null;
        }
    }


    /*
     * @param shortened String of Equipment and returns complete name of Equipment
     * vriskei to String "***_armor"
     */
    public String findItemName(String itemName, String mapString) {
        itemName = itemName.toLowerCase();
        mapString = mapString.toLowerCase();
        try {
            int a = mapString.indexOf(itemName);
            while (true) {
                a--;
                if (mapString.charAt(a) == ' ' || mapString.charAt(a) == '{')
                    break;
            }
            a++;
            mapString = mapString.substring(a, mapString.indexOf(itemName));
            mapString = mapString.concat(itemName);
            return mapString;
        }
        catch(Exception e){
            return "";
        }
    }


    /*
     * Drinks potion and restores health
     */
    public String consumeItem(String potionName){
        potionName = potionName.toLowerCase();
        potionName = potionName.replaceAll("consume ", "");
        if(inventory.containsKey(potionName)){
            if(inventory.get(potionName).getClass().getSimpleName().equals("Consumable")){
                int restoredHealth = ((Consumable) inventory.get(potionName)).getRestoreHealth();
                this.currentLife += restoredHealth;
                if(currentLife>health)
                    currentLife = health;
                inventory.remove(potionName);
                return ("You restored " + restoredHealth + " health");
            }
        }
        return null;
    }

    /*
     * Apothikeuei Items sto Inventory
     */
    public String storeItem(String itemName){
        itemName = itemName.toLowerCase();
        if(roomList.get(Room.activeRoom).itemMap.containsKey(itemName)){
            inventory.put(itemName,roomList.get(Room.activeRoom).itemMap.get(itemName));
            roomList.get(Room.activeRoom).itemMap.remove(itemName);
            return (itemName + " stored in inventory");
        }
        else
            return (itemName + " not found");
    }

    /*
     * Rixnei items ap to inventory
     */
    public String dropItem(String itemName){
        itemName = itemName.toLowerCase();
        itemName = itemName.replace("drop ", "");
        if(inventory.containsKey(itemName)){
            roomList.get(Room.activeRoom).itemMap.put(itemName, inventory.get(itemName));
            inventory.remove(itemName);
            return ("You droped " + itemName);
        }
        return null;
    }

    /*
     * Prints Items in Inventory
     */
    public String viewInventory(){
        String inventoryItems = "";
        if (inventoryItems.isEmpty())
            return "Your inventory is empty";
        for(String key : this.inventory.keySet())
               inventoryItems += key + " ";

        return ("You have in your inventory: " + inventoryItems);
    }



    /*
     * Ksekleidonei tin porta an exeis to katallilo kleidi,
     * (H porta einai kleidomeni me kodiko)
     */
    public String unlockDoor(String orientation){
        orientation = orientation.replace("UNLOCK ", "");
        Integer index = roomList.get(Room.activeRoom).getNextDoorIndex(orientation);
        if (index == null) index = 0;
        for(String key : this.inventory.keySet()){
            if((key.contains("key")) && (this.inventory.get(key).getIndexToUnlock().equals(index))){
                    roomList.get(index).removeLock(index);
                    return ("You unlocked the room!");
            }
        }
        return null;
    }

    /*
     * Strikes a monster and it strikes back
     */
    public String attack(Monster monster){
        int a = monster.getHp();
        monster.setHp( - (int) criticalHit(this.getDmg()));
        refreshHealth( - (monster.getDmg() * (2 - (100 / (100 - this.getArmor())))) );

        if (getCurrentLife() <= 0) {
            return ("You died!");
        }
        if (monster.getHp() <= 0) {
            Room tempRoom = new Room();
            addXp(monster.getXp());
            String name = monster.getName();
            tempRoom.removeMonster();
            return ("You killed the " + name);
        }
        return ("You attacked the " + monster.getName() + " and dealt " + (a - monster.getHp()) + " damage.");
    }

    private double criticalHit(double dmg){
        int extraDmg = (int) (Math.random() * 3); // random timi apo 0 - 3
        if (((int) (Math.random() * 4)) == 1) // 0.25% pithanotita gia 5 dmg
            extraDmg = (int) (dmg * 1.5);
        dmg += extraDmg;
        return dmg;
    }

    /*
     * Prosthetei xp ston paikti
     */
    public void addXp(int experience){
        xp += experience;
        if (xp >= xpToNextLvl){
            int exp = xp - xpToNextLvl;
            levelUp(exp);
        }
    }

    /*
     * Pairnei tin ananeomeni zoi tou paikti ap tin maxi pou egine
     */
    public void refreshHealth(int health){
        this.currentLife += health;
    }

    /*
     * Pernaei sto epomeno epipedo kai allazei stats tou paikti
     */
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

    /*
     * Ektiponei ta stats tou paikti
     */
    public String printStats(){
        return "Current Health: " + this.currentLife + "\n"
                + "Damage: " + this.dmg + "\n"
                + "Armor: " + this.armor + "\n"
                + "Current Level: " + this.level + "\n"
                + "Current experience: " + this.xp + "\n"
                + "Experience to next Level: " + (this.xpToNextLvl - this.xp);
    }


}
