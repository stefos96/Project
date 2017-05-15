package Verbs;

import Game.Character;
import Game.Room;

public class Consume implements Verbs{

	@Override
	public String checkVerb(Room map, Character player, String noun) {
		return player.consumeItem(noun);
	}
}