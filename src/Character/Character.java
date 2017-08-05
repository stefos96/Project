package Character;
import Alignment.Alignment;
import Alignment.Ethics;
import Dice.Dice;
import Equipment.Equipment;
import Item.Item;
import Skills.Skills;
import Spells.Spells;
import Positions.Positions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class Character implements Serializable{
    private String playerName;
    private String name;
    private CharacterRace race;

    private String portrait;

    private CharacterClass class1;
    private CharacterClass class2;
    private CharacterClass class3;

    // Lets you act before others
    // sum(dex + misc bonuses)
    private int initiativeModifier;
    // Any misc bonuses will change the initiativeBonus
    private int initiativeBonus;

    private String prefClass;

    // overall level
    private int effectiveCharacterLevel;

    private int xp;
    private int xpToNextLvl;
    private int gold = 0;


    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    // Depending on your hit die
    // You roll your classes dice plus constitution
    // some feats can grant you hp
    private int hitPoints;


    // BAB + str for melee OR dex for ranged + size modifier(if any) + any other modifier
    private int atackModifier;

    // fort save helps you avoid poison, disease, and being turned into a frog
    // ref save helps you avoid being caught in explosions or trapped under avalanches
    // will helps you avoid mind control and telepathy

    // constitution for fortitude saves
    // dexterity for reflex saves
    // wisdom for will saves

    // baseSave(class) + a save from above + equipment modifier + any other modifier
    private int saveModifier;


    // class skill points per lvl + intelligence per level
    // note at first level your skill points are *4
    // with every skill point you can purchase a rank in a skill
    // one rank in cross class skill costs 2 skill points
    // you can have many ranks in a skill equal to class level + 3
    private int skillPoints;


    // when you make a skill check
    // sum of that skill's ranks + relevant ability modifier + any misc modifier
    // some classes, races, equipment etc can grant bonuses or grant penalties
    private int skillModifier;


    // Determines how well you can avoid taking hits
    // sum of
    // +10
    // armor bonus by your armor or similar protective item
    // shield bonus by your shield, buckler, or similar item
    // dex, certain armors restrict the amount of your dexterity bonus you can apply to your AC.
    // size modifier, +0 for normal, bonuses for smaller, penalties for bigger
    // natural armor bonus, some races haves bonuses
    // deflection bonus, some spells and magic items have
    // any other modifier from any other source
    private int armorClass;

    private int armorBonus;
    private int shieldBonus;
    private int naturalArmorBonus;
    private int deflectionBonus;
    private int miscArmor;


    // TODO: check the type of equipment so you can't have 2 armors etc
    private HashMap<String, Equipment> equipmentHashMap = new HashMap<>();

    // All items
    private HashMap<String, Item> itemHashMap = new HashMap<>();

    // All learned skills
    private HashMap<String, Skills> skillHashMap = new HashMap<>();

    private HashMap<String, Spells> spellsHashMap = new HashMap<>();



    // Languages that the character can speak
    private ArrayList<String> languages = new ArrayList<>();

    // carrying capacity
    private int capacity;

    // lawfull good or lawfull neutral
    private Ethics ethics;
    private Alignment alignment;

    // Less important
    private GenderEnum gender;
    private int height;
    private int weight;
    private String size;

    // Position on the map
    private double xPosition;
    private double yPosition;


    public Character(String playerName, String name, CharacterRace pickedRace, CharacterClass pickedClass,
                     Alignment alignment, Ethics ethics, GenderEnum gender, String portrait){
        this.name = name;
        this.playerName = playerName;

        size = String.valueOf(pickedRace.getSize());

        this.alignment = alignment;
        this.ethics = ethics;

        this.race = pickedRace;
        this.class1 = pickedClass;

        abilityScores();

        strength += race.getRaceStr();
        dexterity += race.getRaceDex();
        constitution += race.getRaceCon();
        intelligence += race.getRaceInt();
        wisdom += race.getRaceWis();
        charisma += race.getRaceCha();

        this.gender = gender;

        this.portrait = portrait;
    }


    public void setHitPoints(CharacterClass selectedClass){
        hitPoints += selectedClass.getHitDie() + constitution;
    }


    public void calcEffectiveLevel(){
//        effectiveCharacterLevel = class1.getClassLevel() + levelAdjustment + racialHD;
    }


    private void abilityScores(){
        strength = fourThrowsD6();
        dexterity = fourThrowsD6();
        constitution = fourThrowsD6();
        intelligence = fourThrowsD6();
        wisdom = fourThrowsD6();
        charisma = fourThrowsD6();
    }

    private int fourThrowsD6(){
        Dice dice = Dice.D6;

        int min = 6;
        int sum = 0;
        for (int i = 0; i < 4; i++){
            int diceThrow = dice.roll();
            if (diceThrow < min)
                min = diceThrow;
            sum += diceThrow;
        }
        return sum - min;
    }

    public void setPositions(double x, double y) {
        this.xPosition = x;
        this.yPosition = y;
    }


    private int abilityModifier(int ability) {
        return (ability - 10) / 2;
    }

    public void setGender(GenderEnum gender){
        this.gender = gender;
    }

    public GenderEnum getGender(){
        return gender;
    }

    public String getCharacterName() {
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

    public void setInitiativeModifier() {
        this.initiativeModifier = dexterity + initiativeBonus;
    }

    public String getPortrait() {
        return portrait;
    }

    public CharacterClass getCharacterClass() {
        return class1;
    }

    public String getPlayerName() {
        return playerName;
    }

    public CharacterRace getRace() {
        return race;
    }

    public String getEthics() {
        return ethics.toString();
    }

    public String getAlignment() {
        return alignment.toString();
    }

    public String getSize() {
        return size;
    }

    public double getXPosition() {
        return xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }
}