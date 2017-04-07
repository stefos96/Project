package Battle;
import Game.Character;
import Monsters.Monster;


public class Battle{


    /*
     * @returns xp pou tha parei o paiktis an to nikise, an exase tha epistrepsei 0.
     * i maxi tha ginetai me gures (first character attacks then monster and so on)
     * o paiktis exei ena xroniko diastima gia na kanei epithesi allios ksanavaraei to teras
     */
    public int enterBattle(Character character, Monster monster){
        int charLife = character.getCurrentLife();
        int monsterLife = monster.getHp();
        int charDmg = character.getDmg();                
        int monsterDmg = monster.getDmg();
        // long currentTime = currentTimeMillis() * 1000; //se seconds

        while ((charLife > 0) && (monsterLife > 0)){
            monsterLife -= attack(charDmg);
            charLife -= monsterDmg;
        } 
        if (monsterLife <= 0){
            character.refreshHealth(charLife);
            return monster.getXp();            
        }
        return 0;
    }

  
  
     public int attack(int dmg){
        int extraDmg = (int) (Math.random() * 3); // random timi apo 0 - 3
        if (((int) (Math.random() * 4)) == 1) // 0.25% pithanotita gia 5 dmg 
            extraDmg = 5;
        dmg += extraDmg;
        return dmg;
    }


    

}