package Verbs;

import Game.Character;
import Scenes.Scene;

public class Go implements Verbs {

	@Override
	public String checkVerb(Scene map, Character player, String noun) {
		if (map.enterRoom(noun)){
            return map.getDescription()
					+ map.getDoorNumber() + "\n"
                    + map.getRoomItems()
                    + map.printMonster();
		}
		return "Not a valid direction";
	}
}