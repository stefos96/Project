package Size;


public enum Size {
    FINE,
    DIMINUTIVE,
    TINY,
    SMALL,
    MEDIUM,
    LARGE,
    HUGE,
    GARGANTUAN,
    COLLOSAL;

    public String capFirstLetter() {
        String name = this.toString();
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}