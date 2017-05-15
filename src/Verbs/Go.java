package Verbs;

import Game.Character;
import Game.Room;

public class Go implements Verbs {

	@Override
	public String checkVerb(Room map, Character player, String noun) {
		if (map.enterRoomVer2(noun)){
            return map.getDoorNumber() + "\n"
                    + map.getRoomItems() + "\n"
                    + map.printMonster();
		}
		return null;
	}
}