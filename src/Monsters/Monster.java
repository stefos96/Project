package Monsters;



// gia tora vgazo to abstract
public class Monster {
    
    private int hp;
    private int dmg;
    private int xp;
    private String name;

    public Monster(int dmg, int hp, int xp, String name){
        this.dmg = dmg;
        this.hp = hp;
        this.xp = xp;
        this.name = name;
    }

    public int getHp(){
        return this.hp;
    }

    public int getDmg(){
        return this.dmg;
    }

    public int getXp(){
        return this.xp;
    }

    public String getName(){ return this.name; }

}

