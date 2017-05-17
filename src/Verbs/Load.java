package Verbs;
import Commands.Commands;
import Game.Character;
import Layouts.MainGame.MainGame;
import Game.MapCreation;
import Scenes.Scene;
import java.io.BufferedReader;
import java.io.FileReader;

public class Load implements Verbs{

    @Override
    public String checkVerb(Scene map, Character player, String noun) {
        Commands parser = new Commands();

        new MapCreation();
        player.clearAll();
//        MainGame.allCommands.remove(MainGame.allCommands.size() - 1);
        noun = noun.toLowerCase();
        try{
            BufferedReader br = new BufferedReader(new FileReader("./src/SavedGames/"+ noun + ".txt"));
            String line;
            while ((line = br.readLine()) != null){
                try {
                    MainGame.allCommands.add(line);
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
