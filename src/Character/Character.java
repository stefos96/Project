
package Character;
import java.util.HashMap;
import static MapCreation.MapCreation.sceneList;
import Scenes.Scene;


public class Character {
    private String name;
    public CharacterRace race;
    public CharacterClass class1; // A character can have many classes but for now it's limited to 3
    public CharacterClass class2;
    public CharacterClass class3;

    private GenderEnum gender;
    private int height;
    private int weight;


    public Character(String name, CharacterRace pickedRace, CharacterClass pickedClass){
        this.race = pickedRace;
        strength += race.getStr();
        dexterity += race.getDex();
        constitution += race.getCon();
        intelligence += race.getInt();
        wisdom += race.getWis();
        charisma += race.getCha();
        this.class1 = pickedClass;
        this.name = name;
        abilityScores();
    }

    // HP
//    health = class + con

    // overall level
//    private int effectiveCharacterLevel = classLevel + levelAdjustment + racialHD;

    // lawfull good or lawfull neutral
    private int ethics;
    private int justice;

    /*
     * Base attack bonus
     * low, medium, high
     * initial 0,0,1 respectively
     * 1/3 1/2 1
     */


    /*
    private int health = 10;
    private int currentHealth = 10;
    private int armor = 1;
    private int dmg = 5;
    private int level = 1;
    private int xp = 0;
    private int xpToNextLvl = 30;
    private int gold = 0;


    private HashMap<String, Equipment> equipment = new HashMap<>();
    private HashMap<String, Item> inventory = new HashMap<>();

*/
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private int hitPoints;
    private int armorClass;




   /*
    *  Prints equipment

    public String printEquipment() {
        String equiped = "";
        try {
            for (String key : equipment.keySet()) {
                if (!key.equals("")) {
                    if (equipment.get(key).getExtraArmor() == 0)
                        equiped += key + "(+" + equipment.get(key).getExtraDmg() + " damage)\n";
                    else
                        equiped += key + "(+" + equipment.get(key).getExtraArmor() + " armor)\n";
                }
            }
            if (!equiped.equals("")) {
                equiped = equiped.replace("_"," ");
                return ("You have equiped: " + equiped);
            }
            else{
                return "You have no equipment";
            }
        }
        catch (Exception e) {
            return "";
        }
    }
    */

    /*
     * Removes equipment from player

    public String unequipItem(String itemToUnequip){
        if (!itemToUnequip.equals(""))
            itemToUnequip = findItemName(itemToUnequip, equipment.toString());
        if (!itemToUnequip.equals("")) {
            this.armor -= equipment.get(itemToUnequip).getExtraArmor();
            this.dmg -= equipment.get(itemToUnequip).getExtraDmg();
            inventory.put(itemToUnequip, equipment.get(itemToUnequip));
            equipment.remove(itemToUnequip);
            return ("You unequiped " + itemToUnequip.replace("_"," ") + " and stored it in your inventory.");
        }
        return null;
    }
    */

    /*
     * Wears equipment that may be in inventory or in the current scene

    public String equip(String itemName) {
        itemName = itemName.toLowerCase();
        itemName = itemName.replace("equip ", "");
        String fullName = findItemName(itemName, sceneList.get(Scene.activeRoom).itemMap.toString());

        if (fullName.equals("")) {
            fullName = findItemName(itemName, inventory.toString());
            if(!fullName.equals(""))
                return equipOperation(itemName, fullName, inventory);
            else
                return (itemName + " not found");
        }
        else
            return equipOperation(itemName, fullName, sceneList.get(Scene.activeRoom).itemMap);
    }
    */


    /*
     * gia na min kanei 2 fores elegxous kai idies diadikasies gia to inventory kai gia ta items pou
     * uparxoun sto domatio ekana autin tin methodo

    private String equipOperation(String itemName, String fullName, HashMap<String, Item> eMap){
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

*/

    /*
     * @param shortened String of Equipment and returns complete name of Equipment
     * @returns String "***_armor"

    private String findItemName(String itemName, String mapString) {
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

*/

    /*
     * Drinks potion and restores health

    public String consumeItem(String potionName){
        potionName = potionName.toLowerCase();
        potionName = potionName.replaceAll("consume ", "");
        if(inventory.containsKey(potionName)){
            if(inventory.get(potionName).getClass().getSimpleName().equals("Consumable")){
                int restoredHealth = ((Consumable) inventory.get(potionName)).getRestoreHealth();
                this.currentHealth += restoredHealth;
                if(currentHealth >health)
                    currentHealth = health;
                inventory.remove(potionName);
                return ("You restored " + restoredHealth + " health");
            }
        }
        return "You really shouldn't try to consume anything";
    }
    */

    /*
     * Stores Items to Inventory

    public String storeItem(String itemName){
        itemName = itemName.toLowerCase();
        if(sceneList.get(Scene.activeRoom).itemMap.containsKey(itemName)){
            inventory.put(itemName, sceneList.get(Scene.activeRoom).itemMap.get(itemName));
            sceneList.get(Scene.activeRoom).itemMap.remove(itemName);
            return (itemName + " stored in inventory");
        }
        else
            return (itemName + " not found");
    }
    */

    /*
     * Drops items from inventory to the floor

    public String dropItem(String itemName){
        itemName = itemName.toLowerCase();
        itemName = itemName.replace("drop ", "");
        if(inventory.containsKey(itemName)){
            sceneList.get(Scene.activeRoom).itemMap.put(itemName, inventory.get(itemName));
            inventory.remove(itemName);
            return ("You droped " + itemName);
        }
        return null;
    }
    */

    /*
     * Prints Items in Inventory

    public String viewInventory(){
        String inventoryItems = "";
        if (inventory.isEmpty())
            return "Your inventory is empty";
        for(String key : this.inventory.keySet())
               inventoryItems += key + " ";

        return ("You have in your inventory: " + inventoryItems);
    }
    */

    /*
     * Strikes a monster and it strikes back

    public String attack(Monster monster){
        int initialMonsterHealth = monster.getHealth();

        monster.painInflicted(criticalHit());
        currentHealth -= armorReducedDamage(monster.getDamage());

        String monsterName = monster.getName().replace("_"," ");

        if (currentHealth <= 0) {
            return ("You died!");
        }
        if (monster.getHealth() <= 0) {
            Scene tempScene = new Scene();
            int xp = monster.getXp();
            addXp(xp);
            tempScene.removeMonster();
            // depending on current level gains gold
            gold += level * (int)(  Math.random() * 10 + 1);
            return "You killed the " + monsterName + " and gained " + xp + "xp and " + gold + "gold";
        }
        return ("You attacked the " + monsterName + " and dealt " + (initialMonsterHealth - monster.getHealth()) + " damage.");
    }

    private int armorReducedDamage(int monsterDamage){
        return monsterDamage * (2 - (100 / (100 - armor)));
    }

    private int criticalHit(){
        int extraDmg = (int) (Math.random() * 3); // random timi apo 0 - 3
        if (((int) (Math.random() * 4)) == 1) // 0.25% pithanotita gia 5 dmg
            extraDmg = (int) (dmg * 1.5);
        dmg += extraDmg;
        return dmg;
    }
    */

    /*
     * Player gains xp and levels up if he reaches his xp cap

    private void addXp(int experience){
        xp += experience;
        if (xp >= xpToNextLvl){
            int exp = xp - xpToNextLvl;
            levelUp(exp);
        }
    }
    */

    /*
     * Levels up the player, his stats are increased and his life restored

    private void levelUp(int exp){
        level++;
        dmg++;
        health += 5;
        xp = exp;
        currentHealth = health;
        xpToNextLvl += 15;
    }
    */

//    public String getGold(){
//        return "You look at your pouch " + gold + " gold";
//    }
//

    /*
     * Prints player's stats
     */
//    public String printStats(){
//        return "Current Health: " + this.currentHealth + "\n"
//                + "Damage: " + this.dmg + "\n"
//                + "Armor: " + this.armor + "\n"
//                + "Current Level: " + this.level + "\n"
//                + "Current experience: " + this.xp + "\n"
//                + "Experience to next Level: " + (this.xpToNextLvl - this.xp);
//    }

//    public void clearAll(){
//        inventory.clear();
//        equipment.clear();
//        health = 10;
//        currentHealth = 10;
//        armor = 1;
//        dmg = 5;
//        level = 1;
//        xp = 0;
//        xpToNextLvl = 30;
//    }

    private void abilityScores(){
        strength = fourThrowsD6();
        dexterity = fourThrowsD6();
        constitution = fourThrowsD6();
        intelligence = fourThrowsD6();
        wisdom = fourThrowsD6();
        charisma = fourThrowsD6();
    }

    private int fourThrowsD6(){
        int firstThrow = dice(6);
        int secondThrow = dice(6);
        int thirdThrow = dice(6);
        int fourthThrow = dice(6);
        if (firstThrow <= secondThrow && firstThrow <= thirdThrow && firstThrow <= fourthThrow)
            return secondThrow + thirdThrow + fourthThrow;
        else if (secondThrow <= thirdThrow && secondThrow <= fourthThrow)
            return firstThrow + thirdThrow + fourthThrow;
        else if (thirdThrow <= fourthThrow)
            return firstThrow + secondThrow + fourthThrow;
        return firstThrow + secondThrow + thirdThrow;
    }

    private int dice(int diceSides){
        return (int) ((Math.random() * diceSides + 1));
    }


    private int abilityModifier(int ability) {
        return (ability - 10) / 2;
    }

    public String getAbilityScores(){
        return "strength=" + strength + " " +
                "dexterity=" + dexterity + " " +
                "constitution=" + constitution + " " +
                "intelligence=" + intelligence + " " +
                "wisdom=" + wisdom + " " +
                "charisma=" + charisma + "\n";
    }

    public void setGender(GenderEnum gender){
        this.gender = gender;
    }

    public GenderEnum getGender(){
        return gender;
    }

    public String getAnythingFromRace(String word){
        return null;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
