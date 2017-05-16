package Verbs;
import Commands.Commands;
import Game.Character;
import Game.Layout;
import Game.MapCreation;
import Scenes.Scene;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Load implements Verbs{

    @Override
    public String checkVerb(Scene map, Character player, String noun) {
        Commands parser = new Commands();

        new MapCreation();
        player.clearAll();
        Layout.allCommands.remove(Layout.allCommands.size() - 1);
        noun = noun.toLowerCase();
        try{
            BufferedReader br = new BufferedReader(new FileReader("./src/SavedGames/"+ noun + ".txt"));
            String line;
            while ((line = br.readLine()) != null){
                try {
                    Layout.allCommands.add(line);
                    parser.commandParser(map, player,line);
                }
                catch (Exception e){}
            }
            return "File loaded successfully";
        }
        catch (Exception e){
            return "File not found";
        }
    }
}
