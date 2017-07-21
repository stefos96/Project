package Coins;


public enum Coins {
    COPPER(1),
    SILVER(10),
    GOLD(100),
    PLATINUM(1000);

    int value;

    /*
     * Value in copper coins
     */
    Coins(int value){
        this.value = value;
    }

    double getGoldValue(){
        return value / 100;
    }
}
