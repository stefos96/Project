package Verbs;

import Game.Character;
import Game.Room;

public class Look implements Verbs{

	@Override
	public String checkVerb(Room map, Character player, String noun) {
		return map.getDoorNumber() + "\n"
                + map.getRoomItems() + "\n"
                + map.printMonster();
	}
}