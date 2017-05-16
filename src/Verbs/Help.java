package Verbs;

import Game.Character;
import Scenes.Scene;

public class Help implements Verbs{
    @Override
    public String checkVerb(Scene map, Character player, String noun) {
        return ("GO [ORIENTATION]") + "\n"
        + "PICK [ITEM]" + "\n"
        + "LOOK" + "\n"
        + "DROP [ITEM]" + "\n"
        + "EQUIP [ITEM]" + "\n"
        + "VIEW INVENTORY" + "\n"
        + "SAVE" + "\n"
        + "LOAD" + "\n"
        + "EXIT";
    }
}
