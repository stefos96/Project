package Verbs;

import Game.Character;
import Scenes.Scene;

public class Preview implements Verbs{

	@Override
	public String checkVerb(Scene map, Character player, String noun) {
        return player.printEquipment();
	}
}