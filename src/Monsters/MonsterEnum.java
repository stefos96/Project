package Monsters;

public enum MonsterEnum{
    SPIDER(5,10,12),
    ZOMBIE(3,10,8),
    TROLL(15,50,20),
    SNAKE(1,5,3),
    VAMPIRE(10,30,16);




    public int damage;
    public int health;
    public int xp;

    MonsterEnum(int dmg, int health, int xp){
        this.damage = dmg;
        this.health = health;
        this.xp = xp;
    }
}
