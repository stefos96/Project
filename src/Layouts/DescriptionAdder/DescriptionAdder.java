package Layouts.DescriptionAdder;
import Layouts.ViewInterface;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class DescriptionAdder implements ViewInterface{

    private Scene scene;
    private Stage stage;

    private SVGPath backButton;
    private SVGPath nextButton;
    private SVGPath descriptionButton;
    private GridPane gridPane;
    private TextArea descriptionTextArea;

    private ArrayList<ArrayList<String>> descriptionArray = new ArrayList<>();

    private int columns;
    private int rows;

    private int currentCol;
    private int currentRow;

    public DescriptionAdder() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DescriptionAdder.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);

        nextButton = (SVGPath) scene.lookup("#nextButton");
        backButton = (SVGPath) scene.lookup("#backButton");
        descriptionButton = (SVGPath) scene.lookup("#descriptionButton");

        gridPane = (GridPane) scene.lookup("#gridPane");

        descriptionTextArea = (TextArea) scene.lookup("#descriptionTextArea");

        for (int i = 0; i < 25; i++){
            descriptionArray.add(new ArrayList<>());
            for (int j = 0; j < 25; j++){
                String temp = null;
                descriptionArray.get(i).add(temp);
            }
        }
    }

    @Override
    public void show() {
        // Removes columns so it can add any necessary columns later, same thing goes for rows
        if (gridPane.getColumnConstraints().size() > 5) {
            for (int i = gridPane.getColumnConstraints().size(); i >= 5; i--)
                gridPane.getColumnConstraints().remove(gridPane.getColumnConstraints().size() - 1);
        }

        if (gridPane.getRowConstraints().size() > 5) {
            for (int i = gridPane.getRowConstraints().size(); i >= 5; i--)
                gridPane.getRowConstraints().remove(gridPane.getRowConstraints().size() - 1);
        }

        int currentColumnSize = gridPane.getColumnConstraints().size();
        int currentRowSize = gridPane.getRowConstraints().size();

        // Adds necessary columns and rows
        for (int i = currentColumnSize; i < columns; i++) {
            ColumnConstraints col = new ColumnConstraints(5, 100, Region.USE_COMPUTED_SIZE);
            gridPane.getColumnConstraints().add(col);
        }

        for (int i = currentRowSize; i < rows; i++) {
            RowConstraints row = new RowConstraints(5, 100, Region.USE_COMPUTED_SIZE);
            gridPane.getRowConstraints().add(row);
        }

        stage.show();

    }

    @Override
    public void hide() {
        stage.hide();
    }

    @Override
    public void setButtonListener(String button, EventHandler<? super MouseEvent> listener) {
        switch (button){
            case "backButton":
                backButton.setOnMouseClicked(listener);
            case "nextButton":
                nextButton.setOnMouseClicked(listener);
            case "descriptionButton":
                descriptionButton.setOnMouseClicked(listener);
            case "gridPane":
                gridPane.setOnMouseClicked(listener);
        }
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }




    /**
     * Simple selection when a click occurs in gridPane highlighting the box it's targetted
     */
    public void selectInGrid(double xAxis, double yAxis){
        arrayToGrid();

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

        double rectangleWidth = gridPane.getWidth() / gridPane.getColumnConstraints().size() - gridPane.getHgap();
        double rectangleHeight = gridPane.getHeight() / gridPane.getRowConstraints().size() - gridPane.getVgap();

        Rectangle currentRectangle = new Rectangle(rectangleWidth, rectangleHeight, Paint.valueOf("#58ecee"));
        currentRectangle.setOpacity(0.4);
        gridPane.add(currentRectangle, currentCol, currentRow);

        try {
            String description = descriptionArray.get(currentCol).get(currentRow);
            descriptionTextArea.setText(description);
        }
        catch (Exception e){}
    }


    /**
     * Clears the gridPane, reforms the Strings on the descriptionArray to labels and adds them to the gridPane
     */
    private void arrayToGrid() {
        Node node = gridPane.getChildren().get(0);
        gridPane.getChildren().clear();
        gridPane.getChildren().add(0,node);

        for (int i = 0; i <= columns; i++) {
            for (int j = 0; j <= rows; j++) {
                String description = descriptionArray.get(i).get(j);
                Label temp = new Label(description);
                if (description != null)
                    gridPane.add(temp, i, j);
            }
        }
    }

    public void addDescription(){
        String description = descriptionTextArea.getText();
        descriptionArray.get(currentCol).remove(currentRow);
        descriptionArray.get(currentCol).add(currentRow, description);
        descriptionTextArea.clear();
    }

    public ArrayList<ArrayList<String>> getDescriptionArray(){
        return descriptionArray;
    }
}
