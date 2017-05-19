package Races;


public enum RacesEnum{
    // TODO: better solution for ability scores
    HUMAN(0,0,0,0,0,0),
    ELF(0,2,-2,0,0,0),
    DWARF(0,0,2,0,0,-2),
    HALFLING(-2,2,0,0,0,0),
    GNOME(-2,0,2,0,0,0),
    HALF_ORC(2,0,0,-2,0,0),
    HIGH_ELF(0,0,0,0,0,0),
    WOOD_ELF(2,2,-2,-2,0,0),
    DROW_ELF(0,2,-2,2,0,2);

    public int str;
    public int dex;
    public int con;
    public int intel;
    public int wis;
    public int cha;

    RacesEnum(int str, int dex, int con, int intel, int wis, int cha){
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.intel = intel;
        this.wis = wis;
        this.cha = cha;
    }
}
