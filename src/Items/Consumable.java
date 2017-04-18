package Items;


public class Consumable extends Item{
    private int restoreHealth;

    public Consumable(int restoreHealth){
        this.restoreHealth = restoreHealth;
    }
    public Consumable(){}

    public int getRestoreHealth(){
        return restoreHealth;
    }
}
