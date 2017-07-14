package Layouts.MapCreation;
import Layouts.ViewInterface;
import com.sun.org.apache.regexp.internal.RE;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import org.w3c.dom.css.Rect;

import javax.naming.event.ObjectChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MapCreationLayout implements ViewInterface{
    private Scene scene;
    private Stage stage;

    private GridPane gridPane;

    private Circle addRowButton;
    private Circle addColumnButton;
    private Circle removeColumnButton;
    private Circle removeRowButton;
    private TextField sizeTextField;

    private SVGPath cancelButton;
    private SVGPath nextButton;

    private TitledPane titledPane;

    private VBox vBox;

    private Label fieldLabel;
    private Label cliffLabel;
    private Label treeLabel;
    private Label hillLabel;
    private Label waterLabel;
    private Label voidLabel;

    private int currentRow = 1;
    private int currentCol = 1;

    private int releasedColumn;
    private int releasedRow;

    private ArrayList<ArrayList<Rectangle>> rectangleArray = new ArrayList<>();

    public MapCreationLayout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MapCreation.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);

        gridPane = (GridPane) scene.lookup("#gridPane");

        sizeTextField = (TextField) scene.lookup("#sizeTextField");
        viewGridSize();

        addRowButton = (Circle) scene.lookup("#addRowButton");
        addColumnButton = (Circle) scene.lookup("#addColumnButton");
        removeColumnButton = (Circle) scene.lookup("#removeColumnButton");
        removeRowButton = (Circle) scene.lookup("#removeRowButton");

        cancelButton = (SVGPath) scene.lookup("#cancelButton");
        nextButton = (SVGPath) scene.lookup("#nextButton");

        titledPane = (TitledPane) scene.lookup("#titledPane");

        vBox = (VBox) titledPane.getContent();

        fieldLabel = (Label) vBox.getChildren().get(0);
        cliffLabel = (Label) vBox.getChildren().get(1);
        treeLabel = (Label) vBox.getChildren().get(2);
        hillLabel = (Label) vBox.getChildren().get(3);
        waterLabel = (Label) vBox.getChildren().get(4);
        voidLabel = (Label) vBox.getChildren().get(5);

        for (int i = 0; i < 25; i++){
            rectangleArray.add(new ArrayList<>());
            for (int j = 0; j < 25; j++){
                Rectangle temp = null;
                rectangleArray.get(i).add(temp);
            }
        }
    }

    @Override
    public void show() {
        stage.show();
    }

    @Override
    public void hide() {
        stage.hide();
    }

    @Override
    public void setButtonListener(String button, EventHandler<? super MouseEvent> listener) {
        switch (button){
            case "addRowButton":
                addRowButton.setOnMouseClicked(listener);
            case "addColumnButton":
                addColumnButton.setOnMouseClicked(listener);
            case "removeColumnButton":
                removeColumnButton.setOnMouseClicked(listener);
            case "removeRowButton":
                removeRowButton.setOnMouseClicked(listener);

            case "cancelButton":
                cancelButton.setOnMouseClicked(listener);
            case "nextButton":
                nextButton.setOnMouseClicked(listener);

            case "gridPanePressed":
                gridPane.setOnMousePressed(listener);
            case "gridPaneReleased":
                gridPane.setOnMouseReleased(listener);

            case "fieldLabel":
                fieldLabel.setOnMouseClicked(listener);
            case "cliffLabel":
                cliffLabel.setOnMouseClicked(listener);
            case "treeLabel":
                treeLabel.setOnMouseClicked(listener);
            case "hillLabel":
                hillLabel.setOnMouseClicked(listener);
            case "waterLabel":
                waterLabel.setOnMouseClicked(listener);
            case "voidLabel":
                voidLabel.setOnMouseClicked(listener);
        }
    }


    public void addColumn(){
        if (gridPane.getColumnConstraints().size() < 15) {
            ColumnConstraints col = new ColumnConstraints(5, 100, Region.USE_COMPUTED_SIZE);
            gridPane.getColumnConstraints().add(col);
            viewGridSize();
            refreshBoxes();
        }
    }

    public void addRow(){
        if (gridPane.getRowConstraints().size() < 15) {
            RowConstraints row = new RowConstraints(5, 100, Region.USE_COMPUTED_SIZE);
            gridPane.getRowConstraints().add(row);
            viewGridSize();
            refreshBoxes();
        }
    }

    public void removeColumn(){
        if (gridPane.getColumnConstraints().size() > 5) {
            gridPane.getColumnConstraints().remove(gridPane.getColumnConstraints().size() - 1);
            viewGridSize();
            refreshBoxes();
        }
    }

    public void removeRow(){
        if (gridPane.getRowConstraints().size() > 5) {
            gridPane.getRowConstraints().remove(gridPane.getRowConstraints().size() - 1);
            viewGridSize();
            refreshBoxes();
        }
    }


    /**
     * In every removal or addition of cols/rows box sizes must be refreshed
     * Add all boxes to be refreshed
     */
    private void refreshBoxes() {
        double width = gridPane.getWidth() / gridPane.getColumnConstraints().size() - gridPane.getHgap();
        double height = gridPane.getHeight() / gridPane.getRowConstraints().size() - gridPane.getVgap();

        for (int i = 0; i < 25; i++) {
            for (Rectangle cur : rectangleArray.get(i)) {
                try {
                    cur.setWidth(width);
                    cur.setHeight(height);
                }
                catch (Exception e){}
            }
        }
        removeCurrentSelection();
    }


    /**
     * Shows the col and row in the sizeTextField
     */
    private void viewGridSize(){
        int r = gridPane.getRowConstraints().size();
        int c = gridPane.getColumnConstraints().size();
        sizeTextField.setText(r + "," + c);
    }




    /**
     * Finds where clicks occur in a GridPane
     */
    public void selectInGrid(double xAxis, double yAxis){
        removeCurrentSelection();

        Bounds bounds = gridPane.getBoundsInParent();

        // From where the gridPane starts
        double startingHeight = bounds.getMinY();
        double startingWidth = bounds.getMinX();

        // gridPane array size
        int rows = gridPane.getRowConstraints().size();
        int cols = gridPane.getColumnConstraints().size();

        // gridPane width and height
        double width = gridPane.getWidth();
        double height = gridPane.getHeight();

        // Column width and Row height
        double colWidth = width / cols;
        double rowHeight = height / rows;

        // While loop to find in which column the click occured
        double xPos = 0;
        currentCol = 0;

        while ((xAxis - startingWidth) > xPos){
            xPos = currentCol * colWidth;
            currentCol++;
        }
        currentCol = currentCol - 2;

        // While loop to find in which row the click occured
        double yPos = 0;
        currentRow = 0;

        while ((yAxis - startingHeight) > yPos){
            yPos = currentRow * rowHeight;
            currentRow++;
        }
        currentRow = currentRow -  2;
    }



    /**
     * Release mouse event in grid
     */
    public void releaseInGrid(double xAxis, double yAxis){
        Bounds bounds = gridPane.getBoundsInParent();

        // From where the gridPane starts
        double startingHeight = bounds.getMinY();
        double startingWidth = bounds.getMinX();

        // gridPane array size
        int rows = gridPane.getRowConstraints().size();
        int cols = gridPane.getColumnConstraints().size();

        // gridPane width and height
        double width = gridPane.getWidth();
        double height = gridPane.getHeight();

        // Column width and Row height
        double colWidth = width / cols;
        double rowHeight = height / rows;

        // While loop to find in which column the click occured
        double xPos = 0;
        releasedColumn = 0;

        while ((xAxis - startingWidth) > xPos){
            xPos = releasedColumn * colWidth;
            releasedColumn++;
        }
        releasedColumn = releasedColumn - 2;

        // While loop to find in which row the click occured
        double yPos = 0;
        releasedRow = 0;

        while ((yAxis - startingHeight) > yPos){
            yPos = releasedRow * rowHeight;
            releasedRow++;
        }
        releasedRow = releasedRow -  2;

        if (releasedColumn >= currentCol && releasedRow >= currentRow){
            for (int i = currentCol; i <= releasedColumn; i++){
                for (int j = currentRow; j <= releasedRow; j++){
                    paintCurrentSelection(i, j);
                }
            }
        }
        else if (releasedColumn <= currentCol && releasedRow <= currentRow){
            for (int i = currentCol; i >= releasedColumn; i--){
                for (int j = currentRow; j >= releasedRow; j--){
                    paintCurrentSelection(i, j);
                }
            }
        }
        else if (releasedColumn > currentCol && releasedRow < currentRow){
            for (int i = currentCol; i <= releasedColumn; i++){
                for (int j = currentRow; j >= releasedRow; j--){
                    paintCurrentSelection(i, j);
                }
            }
        }
        else {
            for (int i = currentCol; i >= releasedColumn; i--){
                for (int j = currentRow; j <= releasedRow; j++){
                    paintCurrentSelection(i, j);
                }
            }
        }
    }

    /**
     * Creates a selection with rectangles on mouse released
     * @param i is the column to add the rectangle
     * @param j is the row to add the rectangle
     */
    private void paintCurrentSelection(int i, int j){
        double rectangleWidth = gridPane.getWidth() / gridPane.getColumnConstraints().size() - gridPane.getHgap();
        double rectangleHeight = gridPane.getHeight() / gridPane.getRowConstraints().size() - gridPane.getVgap();

        try {
            Rectangle currentRectangle = new Rectangle(rectangleWidth, rectangleHeight, Paint.valueOf("#58ecee"));
            gridPane.add(currentRectangle, i, j);
        }
        catch (Exception e){}
    }





    /**
     * Adds a new rectangle terrain in the map, also adds it in the rectangleArrayList
     * @param terrain a terrain name like water or field etc
     */
    public void addTerrain(String terrain){
        Paint color;

        switch (terrain){
            case "WATER":
                color = Paint.valueOf("BLUE");
                break;
            case "FIELD":
                color = Paint.valueOf("#00FC83");
                break;
            case "VOID":
                color = Paint.valueOf("BLACK");
                break;
            default:
                color = Paint.valueOf("BLACK");
                break;
        }

        if (releasedColumn >= currentCol && releasedRow >= currentRow){
            for (int i = currentCol; i <= releasedColumn; i++){
                for (int j = currentRow; j <= releasedRow; j++) {
                    terrainAddLoop(i, j, color);
                }
            }
        }
        else if (releasedColumn <= currentCol && releasedRow <= currentRow){
            for (int i = currentCol; i >= releasedColumn; i--){
                for (int j = currentRow; j >= releasedRow; j--){
                    terrainAddLoop(i, j, color);
                }
            }
        }
        else if (releasedColumn > currentCol && releasedRow < currentRow){
            for (int i = currentCol; i <= releasedColumn; i++){
                for (int j = currentRow; j >= releasedRow; j--){
                    terrainAddLoop(i, j, color);
                }
            }
        }
        else {
            for (int i = currentCol; i >= releasedColumn; i--){
                for (int j = currentRow; j <= releasedRow; j++){
                    terrainAddLoop(i, j, color);
                }
            }
        }
        removeCurrentSelection();
    }

    /**
     * Removes the Rectangle in the current index and then adds the new Rectangle
     */
    private void terrainAddLoop(int i, int j, Paint color){
        double width = gridPane.getWidth() / gridPane.getColumnConstraints().size() - gridPane.getHgap();
        double height = gridPane.getHeight() / gridPane.getRowConstraints().size() - gridPane.getVgap();

        Rectangle rectangle = new Rectangle(width, height, color);
        Rectangle rectToRemove = rectangleArray.get(i).get(j);
        rectToRemove = null;
        rectangleArray.get(i).remove(j);
        rectangleArray.get(i).add(j, rectangle);
    }

    /**
     * Removes the rectangle selection and adds again the terrain rectangles
     */
    private void removeCurrentSelection(){
        Node node = gridPane.getChildren().get(0);
        gridPane.getChildren().clear();
        gridPane.getChildren().add(0,node);

        int sizeCol = gridPane.getColumnConstraints().size() - 1;
        int sizeRow = gridPane.getRowConstraints().size() - 1;

        for (int i = 0; i <= sizeCol; i++) {
            for (int j = 0; j <= sizeRow; j++) {
                Rectangle temp = rectangleArray.get(i).get(j);
                if (temp != null) {
                    gridPane.add(temp, i, j);
                }
            }
        }
    }

    public int getColumns(){
        return gridPane.getColumnConstraints().size();
    }

    public int getRows(){
        return gridPane.getRowConstraints().size();
    }

    public ArrayList<ArrayList<Rectangle>> getRectangleArray(){
        return rectangleArray;
    }
}
