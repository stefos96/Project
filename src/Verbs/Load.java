package Verbs;
import Game.Character;
import Game.Layout;
import Game.MapCreation;
import Game.Room;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Load implements Verbs{
    private HashMap<String, Verbs> verbCommand = new HashMap<>();
    String firstWord;
    String secondWord;

    @Override
    public String checkVerb(Room map, Character player, String noun) {
        new MapCreation();
        player.clearAll();
        Layout.allCommands.remove(Layout.allCommands.size() - 1);
        noun = noun.toLowerCase();
        enterNewCommand();
        try{
            BufferedReader br = new BufferedReader(new FileReader("./src/SavedGames/"+ noun + ".txt"));
            String line;
            while ((line = br.readLine()) != null){
                splitWords(line);
                try {
                    Layout.allCommands.add(line);
                    verbCommand.get(firstWord).checkVerb(map, player, secondWord);
                }
                catch (Exception e){}
            }
            return "File loaded successfully";
        }
        catch (Exception e){
            return "File not found";
        }
    }

    public void enterNewCommand() {
        verbCommand.put("ATTACK", new Attack());
        verbCommand.put("CONSUME", new Consume());
        verbCommand.put("DROP", new Drop());
        verbCommand.put("EQUIP", new Equip());
        verbCommand.put("GO", new Go());
        verbCommand.put("LOOK", new Look());
        verbCommand.put("PICK", new Pick());
        verbCommand.put("PREVIEW", new Preview());
        verbCommand.put("UNEQUIP", new Unequip());
        verbCommand.put("UNLOCK", new Unlock());
        verbCommand.put("VIEW", new View());
        verbCommand.put("HELP", new Help());
        verbCommand.put("STATS", new Stats());
        verbCommand.put("SAVE", new Save());
        verbCommand.put("LOAD", new Load());
        verbCommand.put("EXIT", new Exit());
    }

    public void splitWords(String UserCommand) {
        UserCommand = UserCommand.trim();

        if (UserCommand.contains(" ")) {
            int i = UserCommand.indexOf(" ");
            firstWord = UserCommand.substring(0, i);
            secondWord = UserCommand.substring(i + 1, UserCommand.length());
        } else {
            firstWord = UserCommand;
        }
    }
}
