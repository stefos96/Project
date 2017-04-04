package Game;


public class Item {
    
    private boolean storable;
    private boolean usable;
    private boolean breakable;
    private Integer indexToUnlock;

    
    Item(String name){
        if (name.contains("key")){
            name = name.replace("key", "");
            indexToUnlock = Integer.parseInt(name);
        }
        switch (name){
            case "knife":
                storable = true;
                break;
            case "key":
                storable = true;
                break;
            default:   
                storable = true;
              break; 
        }
    }
    
    
    public Integer getIndexToUnlock(){
        return indexToUnlock;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
