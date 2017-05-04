package Game;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Formatter;
import Verbs.*;
import javax.swing.*;


public class UserInput extends JFrame {


    private String firstWord = "";
    private String secondWord = "";
    private Scanner myVar = new Scanner(System.in);
    private String UserCommand = "";
    private Character Player1;
    private Room Map1;
    private String allInput = "";
    private boolean load = false;
    private HashMap<String, Verbs> verbCommand = new HashMap<>();
    
    UserInput(){
        this.setSize(400,400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        // Declarations
        JPanel mainPanel = new JPanel();
        this.add(mainPanel);
        JTextArea commandText = new JTextArea();
        JTextArea resultText = new JTextArea();
        mainPanel.add(commandText);
        mainPanel.add(resultText);


        // Result Label Properties
        resultText.setEditable(false);
        resultText.setRows(5);
        resultText.setLineWrap(true);
        resultText.setWrapStyleWord(true);



        Player1 = new Character();
        Map1 = new Room();
        enterNewCommand();
        resultText.setText("Welcome to our Game");
        resultText.append(Map1.getDoorNumber());
        resultText.append(Map1.getRoomItems());
        resultText.append(Map1.printMonster());
    }

    public void enterNewCommand(){
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
        verbCommand.put("EXIT", new Exit());
    }

    public void getCommand(){
        while (true) {
            try {
                UserCommand = myVar.nextLine().toUpperCase();
                splitWords();
                System.out.println(verbCommand.get(firstWord).checkVerb(Map1, Player1, secondWord));
            } catch (Exception e) {
                System.out.println(("Not an available command"));
            }
        }
    }

    public void Input(){

        while(!UserCommand.equals("EXIT")){

            if(!load)
                UserCommand = myVar.nextLine().toUpperCase();
            if ((!(UserCommand.equals("LOAD"))) && (!(UserCommand.equals("SAVE"))))
                allInput += UserCommand + "\n";
            if(UserCommand.contains("GO")){
                if (Map1.enterRoomVer2(UserCommand)){
                    System.out.println(Map1.getDoorNumber());
                    Map1.getRoomItems();
                    Map1.printMonster();
                }
            }
            if(UserCommand.contains("UNLOCK"))
                Player1.unlockDoor(UserCommand);
            if(UserCommand.contains("PICK"))
                Player1.storeItem(UserCommand);
            if(UserCommand.contains("UNEQUIP")) {
                Player1.unequipItem(UserCommand);
                UserCommand = "";
            }
            if(UserCommand.contains("EQUIP"))
                Player1.equip(UserCommand);
            if(UserCommand.contains("DROP"))
                Player1.dropItem(UserCommand);
            if(UserCommand.contains("CONSUME"))
                Player1.consumeItem(UserCommand);
            if(UserCommand.equals("ATTACK " + Map1.getMonster().getName().toUpperCase()))
                Player1.attack(Map1.getMonster());
            switch (UserCommand){
                case "LOOK":
                    System.out.println(Map1.getDoorNumber());
                    Map1.getRoomItems();
                    Map1.printMonster();
                    break;
                case "VIEW INVENTORY":
                    Player1.viewInventory();
                    break;
                case "VIEW INV":
                    Player1.viewInventory();
                    break;
                case "STATS":
                    Player1.printStats();
                    break;
                case "PREVIEW SET":
                    Player1.printEquipment();
                    break;
                case "LOAD":
                    System.out.println("Give name of your saved game.");
                    try{
                        BufferedReader br = new BufferedReader(new FileReader("./src/SavedGames/"+ myVar.nextLine().toUpperCase() + ".txt"));
                        String line;
                        load = true;
                        while ((line = br.readLine()) != null){
                            UserCommand = line;
                            this.Input();
                        }
                        UserCommand = "";
                        load = false;
                    }
                    catch (Exception e){
                        System.out.println("File not found.");
                    }
                    break;
                case "SAVE":
                    System.out.println("Save game as:");
                    String fileName = myVar.nextLine().toUpperCase();
                    try{
                        File x = new File("./src/SavedGames/" + fileName + ".txt");
                        if (x.exists()){
                            System.out.println("There's already a file with that name\nDo you want to overwrite?");
                            if (!myVar.nextLine().toUpperCase().equals("YES")) {
                                System.out.println("Failed to save game.");
                                break;
                            }
                        }
                        fileName = fileName.trim();
                        Formatter f = new Formatter("./src/SavedGames/" + fileName + ".txt");
                        System.out.println("Game saved successfully as " + fileName + ".txt");
                        f.format("%s",allInput);
                        f.close();
                    }
                    catch(Exception e){
                        System.out.println("Failed to save game.");
                    }
                    break;
                case "HELP":
                    getHelp();
                    break;
                default:
                    break;
            }
            if(load)
                UserCommand = "EXIT";
            splitWords();
      }
}


    public void getHelp(){
         System.out.println("GO [ORIENTATION]");
         System.out.println("PICK [ITEM]");
         System.out.println("LOOK");
         System.out.println("DROP [ITEM]");
         System.out.println("EQUIP [ITEM]");
         System.out.println("VIEW INVENTORY");
         System.out.println("SAVE");
         System.out.println("LOAD");
         System.out.println("EXIT");
    }

    public void splitWords(){
        UserCommand = UserCommand.trim();

        if (UserCommand.contains(" ")) {
            int i = UserCommand.indexOf(" ");
            firstWord = UserCommand.substring(0,i);
            secondWord = UserCommand.substring(i+1, UserCommand.length());
        }
        else
            firstWord = UserCommand;
    }




}