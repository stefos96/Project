package Game;
import java.util.Scanner;


public class UserInput{
      
    private Scanner myVar = new Scanner(System.in);
    private String UserCommand = "";
    private Character Player1;
    private Room Map1;
    
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
            switch (UserCommand){  
                case "LOOK":
                    Map1.getRoomItems();
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
                case "LOAD":
                    break;   
                case "SAVE":
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