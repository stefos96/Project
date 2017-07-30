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

    public double getGoldValue(){
        return value / 100;
    }

    /**
     * Converts a string like "90 gp" and returns it's value in copper pieces
     * There is a chance there's no need for this method and its possible it -
     * should be a local method somewhere
     * @param value the String value
     */
    public final int stringValueToCopper(String value) {
        switch (value) {
            case "cp":
                value = value.replaceAll("cp", "");
                return Integer.parseInt(value);
            case "sp":
                value = value.replaceAll("sp", "");
                return Integer.parseInt(value) * 10;
            case "gp":
                value = value.replaceAll("gp", "");
                return Integer.parseInt(value) * 100;
            case "pp":
                value = value.replaceAll("pp", "");
                return Integer.parseInt(value) * 1000;
            default:
                return 0;
        }
    }
}
