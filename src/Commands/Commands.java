package Commands;
import Game.Character;
import Scenes.Scene;
import Verbs.*;
import java.util.HashMap;

public class Commands {

    private HashMap<String, Verbs> verbCommand = new HashMap<>();
    private String firstWord;
    private String secondWord;

    public Commands(){
            verbCommand.put("ATTACK", new Attack());
            verbCommand.put("CONSUME", new Consume());
            verbCommand.put("DROP", new Drop());
            verbCommand.put("EQUIP", new Equip());
            verbCommand.put("GO", new Go());
            verbCommand.put("LOOK", new Look());
            verbCommand.put("PICK", new Pick());
            verbCommand.put("UNEQUIP", new Unequip());
            verbCommand.put("UNLOCK", new Unlock());
            verbCommand.put("VIEW", new View());
            verbCommand.put("HELP", new Help());
            verbCommand.put("SAVE", new Save());
            verbCommand.put("LOAD", new Load());
            verbCommand.put("EXIT", new Exit());
    }

    public String commandParser(Scene map, Character player, String sentence){
        sentence = sentence.toUpperCase();
        splitWords(sentence);
        return verbCommand.get(firstWord).checkVerb(map, player, secondWord);
    }


    private void splitWords(String sentence) {
        sentence = sentence.trim();
        if (sentence.contains(" ")) {
            int i = sentence.indexOf(" ");
            firstWord = sentence.substring(0, i);
            secondWord = sentence.substring(i + 1, sentence.length());
        } else {
            firstWord = sentence;
        }
    }

}
