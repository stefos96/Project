package Game;


public class FinalGame {
    
    
    public static void main(String[] args){        
        MapCreation a = new MapCreation();
        a.mapReader();
//        a.passwordReader();
        a.itemReader();
        a.monsterReader();
        UserInput test = new UserInput();
        test.Input();
       
    
    }
}
