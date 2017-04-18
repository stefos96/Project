package Items;

public enum ConsumablesEnum {

    AMBROSIA(100),
    APPLE(5),
    BREAD(8),
    ELIXIR(40),
    GRAND_POTION(30),
    GRAND_TONIC(25),
    GREATER_POTION(25),
    GREATER_TONIC(20),
    LARGE_POTION(20),
    MEDIUM_POTION(15),
    MILK(6),
    ORC_POTION(15),
    ORC_TONIC(12),
    PHOENIX_ESSENCE(60),
    SMALL_HEALTH_POTION(15);



    public int restoreHealth;

    ConsumablesEnum(int restoreHealth){
        this.restoreHealth = restoreHealth;
    }
}
