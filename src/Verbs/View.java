package Verbs;

import Game.Character;
import Scenes.Scene;

public class View implements Verbs{

	@Override
	public String checkVerb(Scene map, Character player, String noun) {
		switch (noun) {
			case "EQUIPMENT":
				return player.printEquipment();
			case "INVENTORY":
				return player.viewInventory();
			case "GOLD":
				return player.getGold();
			case "STATS":
				return player.printStats();
			case "E":
				return player.printEquipment();
			case "I":
				return player.viewInventory();
			case "G":
				return player.getGold();
			case "S":
				return player.printStats();
			default:
				return "Clarify what to view";
		}
	}
}