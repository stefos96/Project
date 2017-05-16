package Verbs;

import Game.Character;
import Scenes.Scene;

public class Go implements Verbs {

	@Override
	public String checkVerb(Scene map, Character player, String noun) {
		if (map.enterRoom(noun)){
            return map.getDescription() + "\n"
					+ map.getDoorNumber() + "\n"
                    + map.getRoomItems() + "\n"
                    + map.printMonster();
		}
		return "Not a valid direction";
	}
}