package Verbs;

import Game.Character;
import Scenes.Scene;

public class Look implements Verbs{

	@Override
	public String checkVerb(Scene map, Character player, String noun) {
		return map.getDoorNumber() + "\n"
                + map.getRoomItems() + "\n"
                + map.printMonster();
	}
}