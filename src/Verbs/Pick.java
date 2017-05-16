package Verbs;
import Game.Character;
import Scenes.Scene;

public class Pick implements Verbs{

	@Override
	public String checkVerb(Scene map, Character player, String noun) {
        return player.storeItem(noun);
	}
}