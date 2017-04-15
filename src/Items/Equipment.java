package Items;


public class Equipment extends Item{
    private int extraDmg;
    private int extraArmor;
    private int requiredLevel;
    
    
    
    public Equipment(){
        this.storable = false;
    }
    
    
    public Equipment(int dmg, int armor){
        this.storable = false;
        this.extraArmor = armor;
        this.extraDmg = dmg;
    }
    
    public int getExtraDmg(){
        return extraDmg;
    }
    
    public int getExtraArmor(){
        return extraArmor;
    }
    
}
