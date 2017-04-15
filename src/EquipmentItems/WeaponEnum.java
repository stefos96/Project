package EquipmentItems;


public enum WeaponEnum {
    
    // WEAPONS
    BRONZE_DAGGER(5,0),
    IRON_DAGGER(12,0),
    DRAGONSTONE_DAGGER(30,0),
    BRONZE_SWORD(10,0),
    IRON_SWORD(18,0),
    STEEL_SWORD(25,0),
    DEMON_SWORD(35,0),
    LONG_SWORD(22,0),
    EXCALIBUR_SWORD(100,0),
    EMERALD_SWORD(40,0),
    DRAGON_SWORD(45,0),
    
    // SHIELDS
    STEEL_SHIELD(0,15),
    GOLDEN_SHIELD(0,30),
    
    // HELMETS
    LEATHER_HELMET(0,5),
    BRONZE_HELMET(0,10),
    IRON_HELMET(0,18),
    STEEL_HELMET(0,25),
    GOLDEN_HELMET(0,32),
    
    // GLOVES
    LEATHER_GLOVES(0,2),
    BRONZE_GLOVES(0,5),
    IRON_GLOVES(0,8),
    STEEL_GLOVES(0,10),
    GOLDEN_GLOVES(0,15),
    
    // BOOTS
    LEATHER_BOOTS(0,3),
    BRONZE_BOOTS(0,6),
    IRON_BOOTS(0,8),
    STEEL_BOOTS(0,11),
    GOLDEN_BOOTS(0,17),
    
    // ARMOR
    LEATHER_ARMOR(0,10),
    BRONZE_ARMOR(0,17),
    IRON_ARMOR(0,23),
    STEEL_ARMOR(0,30),
    GOLDEN_ARMOR(0,40);
    
    
    
    
    public int damage;
    public int armor;
    
    WeaponEnum(int dmg, int armor){
        this.damage = dmg;
        this.armor = armor;
    }    
}