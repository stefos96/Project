package Monsters;

public enum MonsterEnum{
    HUGE_SPIDER(5,10,12),
    ZOMBIE(3,10,8),
    TROLL(15,50,20),
    SNAKE(1,5,3),
    VAMPIRE(10,30,16),
    FLUFFY_BUNNY(1,1,4);




    public int damage;
    public int health;
    public int xp;

    MonsterEnum(int dmg, int health, int xp){
        this.damage = dmg;
        this.health = health;
        this.xp = xp;
    }
}
