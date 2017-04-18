package Game;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Formatter;


public class UserInput{
      
    private Scanner myVar = new Scanner(System.in);
    private String UserCommand = "";
    private Character Player1;
    private Room Map1;
    private String allInput = "";
    private boolean load = false;
    
    UserInput(){
        Player1 = new Character();
        Map1 = new Room();
        System.out.println("Καλωσήρθες,\nΒρίσκεσαι σε ένα σκοτεινό δωμάτιο, το κεφάλι σου κουδουνίζει και η μνήμη σου είναι θολή.\n" + Map1.getDoorNumber());
        Map1.getRoomItems();
        Map1.printMonster();
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
            if(UserCommand.contains("EQUIP"))
                Player1.equip(UserCommand);
            if(UserCommand.contains("DROP"))
                Player1.dropItem(UserCommand);
            if(UserCommand.contains("CONSUME"))
                Player1.consumeItem(UserCommand);
            switch (UserCommand){
                case "LOOK":
                    Map1.getRoomItems();
                    Map1.printMonster();
                    break;
                case "VIEW INVENTORY":
                    Player1.viewInventory();
                    break; 
                case "VIEW INV":
                    Player1.viewInventory();
                    break;     
                case "ATTACK":
                    if(!Map1.checkIfNullMonster()){
                        Player1.attack(Map1.getMonster());
                        Map1.removeMonster();
                    }
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
      }
}
    
    
    public void getHelp(){
         System.out.println("GO [ORIENTATION]\nPICK [ITEM]\nLOOK\nDROP [ITEM]\nEQUIP [ITEM]\nVIEW INVENTORY\nLOAD\nSAVE\nEXIT" );
    }
    
    
    
    
    
    
    
    
    
}