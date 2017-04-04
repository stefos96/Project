package Game;


public class FinalGame {
    
    
    
    public static void main(String[] args){        
        FileReader a = new FileReader();
        a.mapReader();
        a.passwordReader();
        a.itemReader();
        UserInput test = new UserInput();
        test.Input();
       
    
    }
}
