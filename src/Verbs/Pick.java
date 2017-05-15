package Verbs;
import Game.Character;
import Game.Room;

public class Pick implements Verbs{

	@Override
	public String checkVerb(Room map, Character player, String noun) {
        return player.storeItem(noun);
	}
}