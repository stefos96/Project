package Verbs;

import Game.Character;
import Game.Room;

public class Help implements Verbs{
    @Override
    public String checkVerb(Room map, Character player, String noun) {
        String a = ("GO [ORIENTATION]") + "\n"
        + "PICK [ITEM]" + "\n"
        +"LOOK" + "\n"
        + "DROP [ITEM]" + "\n"
        + "EQUIP [ITEM]" + "\n"
        + "VIEW INVENTORY" + "\n"
        + "SAVE" + "\n"
        + "LOAD" + "\n"
        + "EXIT";
        return a;
    }
}
