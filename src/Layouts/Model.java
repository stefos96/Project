package Layouts;
import Layouts.Map.Map;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import Character.Character;

/**
 * Model of the MVC patern where all important data stay like map and character info
 */
public class Model implements Serializable{

    private int columns;
    private int rows;

    private ArrayList<ArrayList<Rectangle>> rectangleArray = new ArrayList<>();
    private ArrayList<ArrayList<String>> monsterArray = new ArrayList<>();
    private ArrayList<ArrayList<String>> descriptionArray = new ArrayList<>();

    private HashMap<String, Character> characterHashMap = new HashMap<>();

    /**
     * Set the array size for the map creation and for the monster insertion
     */
    void updateArraySize(int rows, int columns){
        this.columns = columns;
        this.rows = rows;
    }

    int getColumns(){
        return columns;
    }

    int getRows(){
        return rows;
    }

    void setRectangleArray(ArrayList<ArrayList<Rectangle>> rectangleArray){
        this.rectangleArray = rectangleArray;
    }

    void setMonsterArray(ArrayList<ArrayList<String>> monsterArray){
        this.monsterArray = monsterArray;
    }

    void setDescriptionArray(ArrayList<ArrayList<String>> descriptionArray){
        this.descriptionArray = descriptionArray;
    }

    void addCharacter(String characterName, Character character) {
        characterHashMap.put(characterName, character);
    }

    HashMap<String, Character> getCharacterHashMap() {
        return characterHashMap;
    }


    // x-2 <= charX <= x+2
    public Character getCorrectCharacter(double x, double y, double radius) {
        for (String key: characterHashMap.keySet()) {
            Character character = characterHashMap.get(key);

            double charX = character.getXPosition();
            double charY = character.getYPosition();


            if (charX == x || (x - radius <= charX && charX <= x + radius)) {
                if (charY == y || (y - radius <= charY && charY <= y + radius))
                    return character;
            }
        }
        return null;
    }
}
