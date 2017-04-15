package Game;
import EquipmentItems.WeaponEnum;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Formatter;


public class UserInput{
      
    private Scanner myVar = new Scanner(System.in);
    private String UserCommand = "";
    private Character Player1;
    private Room Map1;
    private String allInput;
    
    UserInput(){
        Player1 = new Character();
        Map1 = new Room();
        System.out.println("Καλωσήρθες,\nΒρίσκεσαι σε ένα σκοτεινό δωμάτιο, το κεφάλι σου κουδουνίζει και η μνήμη σου είναι θολή.\n" + Map1.getDoorNumber());
        Map1.getRoomItems();
        Map1.printMonster();
    }
       
    public void Input(){
                
        while(!UserCommand.equals("EXIT")){
            UserCommand = myVar.nextLine().toUpperCase();
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
                    System.out.println("wtf");
                    break;   
                case "SAVE":
                    System.out.println("Save game as:");
                    UserCommand = myVar.nextLine().toUpperCase();
                    try{
                        Formatter f = new Formatter("./src/SavedGames/" + UserCommand + ".txt");
                        System.out.println("Game saved successfully as " + UserCommand + ".txt");
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
            
            
      }
}
    
    
    public void getHelp(){
         System.out.println("GO [ORIENTATION]\nPICK [ITEM]\nLOOK\nVIEW INVENTORY\nLOAD\nSAVE\nEXIT" );
    }
    
    
    
    
    
    
    
    
    
}