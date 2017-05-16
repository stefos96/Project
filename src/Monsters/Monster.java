package Monsters;

public class Monster {
    
    private int damage;
    private int health;
    private int xp;
    private String name;

    public Monster(int dmg, int health, int xp, String name){
        this.damage = dmg;
        this.health = health;
        this.xp = xp;
        this.name = name;
    }

    public void painInflicted(int damageTaken){
        this.health -= damageTaken;
    }

    public int getHealth(){
        return this.health;
    }

    public int getDamage(){
        return this.damage;
    }

    public int getXp(){
        return this.xp;
    }

    public String getName(){ return this.name; }

}

