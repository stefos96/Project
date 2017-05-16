package Game;
import EquipmentItems.WeaponEnum;
import Items.Consumable;
import Items.ConsumablesEnum;
import Items.Equipment;
import Monsters.Monster;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import Monsters.MonsterEnum;
import Items.Item;
import Scenes.Scene;


public class MapCreation {
    private String mapText;
    public static ArrayList<Scene> sceneList = new ArrayList<>();
    private int rows = 0;
    private int cols = 0;
    private int i,j;

    public MapCreation(){
        mapReader();
        itemReader();
        monsterReader();
    }
    
   /*
    *Diavazei to map apo arxeio kai to vazei se ArrayList.
    *Algorithmos: arxika mpainei se aplo array kai meta se ArrayList opoy periexei
    *antikeimena klasis Room
    */
    public void mapReader(){
         int[][] roomArray = new int[100][100];
    try{
            File x = new File("./src/Map/map.txt");  // ubuntu change to backslashes
            Scanner sc = new Scanner(x);  
                        
            while(sc.hasNext()){
                mapText = sc.next().toUpperCase();
                if(mapText.equals("END")){
                    cols = 0;
                    rows++;          
                }
                if(mapText.contains("ROOM")){
                    roomArray[rows][cols] = 1;
                    if(mapText.equals("ROOMV"))
                        roomArray[rows][cols] = 2;
                    cols++;
                }
                if(mapText.equals("EMPTY")){
                    roomArray[rows][cols] = 0;
                    cols++;
                }
            }            
            rows++;
                for(i=0;i<rows;i++){
                    for (j=0;j<cols;j++){
                        if(roomArray[i][j] != 0 )
                            sceneList.add(new Scene());
                    }
                }
                
            int position = 0;
                for(i=0;i<rows;i++){
                    for (j=0;j<cols;j++){
                        if(roomArray[i][j] != 0)
                            position++;
                        if((roomArray[i][j] != 0) && (roomArray[i][j+1] != 0))
                            sceneList.get(position - 1).createHorizontalDoor(sceneList.get(position), position);
                        if((roomArray[i][j] == 2) && (roomArray[i+1][j] != 0)){
                            int b = j;
                            int count = 0;
                            for (b=b;b<cols;b++){
                                if(roomArray[i][b] != 0)
                                    count++;
                            }
                            for (b=0;b<j;b++){
                                if(roomArray[i+1][b] != 0)
                                    count++;
                            }   
                            sceneList.get(position - 1).createVerticalDoor(sceneList.get(position - 1 + count), position - 1, count);
                        }
                    }
                 }
                
             
            sc.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File error");
        }
}
    
    
    
    
   /*
    *Diavazei ta items apo arxeio kai to vazei se HashMap
    */    
    public void itemReader(){
        try{
            File x = new File("./src/Map/items.txt"); //ubuntu change to backslashes
            Scanner sc = new Scanner(x);
            int index = 0;
            while(sc.hasNext()){
                if (sc.hasNextInt()){
                    index = sc.nextInt();
                }
                mapText = sc.next().toLowerCase();
                if (mapText.contains("weap")){
                    mapText = mapText.replaceAll("weap","");
                    WeaponEnum weapon = WeaponEnum.valueOf(mapText.toUpperCase());
                    sceneList.get(index).setItem(new Equipment(weapon.damage, weapon.armor), mapText);
                }
                else if(mapText.contains("cons")){
                    mapText = mapText.replaceAll("cons","");
                    ConsumablesEnum cons = ConsumablesEnum.valueOf(mapText.toUpperCase());
                    sceneList.get(index).setItem(new Consumable(cons.restoreHealth), mapText);
                }
                else
                    sceneList.get(index).setItem(new Item(mapText), mapText);
            }
            sc.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File error");
        }
        catch(Exception e){
            System.out.println("Generic error");
        }
    }
    
    
    
    /*
    /*
     * Diavazei kodikous gia to kathe domatio apo arxeio
     public void passwordReader(){
        try{
            File x = new File("./src/Map/pass.txt"); //ubuntu change to backslashes
            Scanner sc = new Scanner(x);  
            int index = 0;
            mapText = "";
            while(sc.hasNext()){
                if (sc.hasNextInt()){
                    index = sc.nextInt();
                }
                  mapText = sc.next().toLowerCase();
                  if (mapText.equals("code")){
                      mapText = sc.next();
                      roomList.get(index).setDoorLock(mapText);
                  }
            }
            sc.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File error");
    }
    
}

*/


/*
 * Diavazei terata apo arxeio
 */
  public void monsterReader(){
        try{
            File x = new File("./src/Map/monsters.txt"); // ubuntu change to backslashes
            Scanner sc = new Scanner(x);  
            int index = 0;
            while(sc.hasNext()){
                if (sc.hasNextInt()){
                    index = sc.nextInt();
                }
                mapText = sc.next();
                MonsterEnum monster = MonsterEnum.valueOf(mapText.toUpperCase());
                sceneList.get(index).setMonster(new Monster(monster.health, monster.damage, monster.xp, monster.name().toLowerCase()));
            }
            sc.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File error");
        }
        catch(Exception e){
            System.out.println("Generic error");
        }
    }
     


}
