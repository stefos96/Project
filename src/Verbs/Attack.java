package Verbs;
import Game.Character;
import Scenes.Scene;

public class Attack implements Verbs{

	@Override
	public String checkVerb(Scene map, Character player, String noun) {
		if(!map.checkIfNullMonster()){
            return player.attack(map.getMonster());
		}
		return "There is nothing to attack";
	}
}