package Verbs;
import Game.Character;
import Game.Room;

public class Exit implements Verbs{

    @Override
    public String checkVerb(Room map, Character player, String noun) {
        System.exit(0);
        return null;
    }
}
