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

    //    health = class + con
    private int health;

    // lawfull good or lawfull neutral
    private int ethics;
    private int justice;

    private String prefClass;

    /**
     * Calculates health
     */
    public void calcHealth(){
        health += class1.getHitDie() + constitution;
    }

    // overall level

    private int effectiveCharacterLevel;

    public void calcEffectiveLevel(){
//        effectiveCharacterLevel = class1.getClassLevel() + levelAdjustment + racialHD;
    }


    /*
     * Base attack bonus
     * low, medium, high
     * initial 0,0,1 respectively
     * 1/3 1/2 1
     */


    private int currentHealth;
    private int armor;
    private int dmg;
    private int level;
    private int xp;
    private int xpToNextLvl;
    private int gold = 0;


//    private HashMap<String, Equipment> equipment = new HashMap<>();
//    private HashMap<String, Item> inventory = new HashMap<>();

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private int hitPoints;
    private int armorClass;


    private void abilityScores(){
        strength = fourThrowsD6();
        dexterity = fourThrowsD6();
        constitution = fourThrowsD6();
        intelligence = fourThrowsD6();
        wisdom = fourThrowsD6();
        charisma = fourThrowsD6();
    }

    private int fourThrowsD6(){
        int max = 0;
        for (int i = 0; i < 4; i++){
            int diceThrow = dice(6);
            if (diceThrow > max)
                max = diceThrow;
        }
        System.out.println(max);
        return max;
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
