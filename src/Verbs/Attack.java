package Verbs;
import Game.Character;
import Game.Room;

public class Attack implements Verbs{

	@Override
	public String checkVerb(Room map, Character player, String noun) {
		if(!map.checkIfNullMonster()){
            return player.attack(map.getMonster());
		}
		return null;
	}
}