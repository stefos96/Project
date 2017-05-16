package Verbs;

import Game.Character;
import Scenes.Scene;

public class Unlock implements Verbs{

	@Override
	public String checkVerb(Scene map, Character player, String noun) {
		return "";
//		return player.unlockDoor(noun);
	}
}