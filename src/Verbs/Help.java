package Verbs;

import Game.Character;
import Scenes.Scene;

public class Help implements Verbs{
    @Override
    public String checkVerb(Scene map, Character player, String noun) {
        return ("GO [DIRECTION]") + "\n"
        + "PICK [ITEM]" + "\n"
        + "LOOK\n"
        + "DROP [ITEM]\n"
        + "EQUIP [ITEM]\n"
        + "UNEQUIP [ITEM]"
        + "ATTACK (MONSTER)\n"
        + "CONSUME [ITEM]\n"
        + "VIEW [INVENTORY]\n"
        + "VIEW [EQUIPMENT]\n"
        + "VIEW [GOLD]\n"
        + "VIEW [STATS]\n"
        + "SAVE [FILE]\n"
        + "LOAD [FILE]\n"
        + "EXIT";
    }
}
