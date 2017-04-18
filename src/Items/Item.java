package Items;


public class Item {
    
    protected boolean storable = true;
    protected boolean usable = true;
    protected boolean breakable = false;
    protected Integer indexToUnlock;

    
    public Item(){        
    }
    
    public Item(String name){
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



    public boolean isStorable(){
        return storable;
    }
    
}
