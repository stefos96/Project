package Verbs;
import Game.Character;
import Game.Room;

public interface Verbs{
	public String checkVerb(Room map, Character player, String noun);
}