package Layouts.MapCreation;
import Layouts.ViewInterface;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
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

    private Rectangle currentBox;

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

        currentBox = (Rectangle) scene.lookup("#currentBox");


        titledPane = (TitledPane) scene.lookup("#titledPane");

        vBox = (VBox) titledPane.getContent();

        fieldLabel = (Label) vBox.getChildren().get(0);
        cliffLabel = (Label) vBox.getChildren().get(1);
        treeLabel = (Label) vBox.getChildren().get(2);
        hillLabel = (Label) vBox.getChildren().get(3);
        waterLabel = (Label) vBox.getChildren().get(4);
        voidLabel = (Label) vBox.getChildren().get(5);
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
    public void setTextField(String textField, String text) {

    }

    @Override
    public String getTextField(String textField) {
        return null;
    }

    @Override
    public void setLabel(String label, String text) {

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

            case "gridPane":
                gridPane.setOnMouseClicked(listener);


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

    @Override
    public String getComboBoxSelection(String comboBox) {
        return null;
    }

    @Override
    public boolean areFieldsFilled() {
        return false;
    }

    public void addColumn(){
        ColumnConstraints col = new ColumnConstraints(5,100, Region.USE_COMPUTED_SIZE);
        gridPane.getColumnConstraints().add(col);
        viewGridSize();
        currentBox.setWidth(gridPane.getWidth() / gridPane.getColumnConstraints().size() - 5);
        currentBox.setHeight(gridPane.getHeight() / gridPane.getRowConstraints().size() - 5);

    }

    public void addRow(){
        RowConstraints row = new RowConstraints(5,100, Region.USE_COMPUTED_SIZE);
        gridPane.getRowConstraints().add(row);
        viewGridSize();
        currentBox.setWidth(gridPane.getWidth() / gridPane.getColumnConstraints().size() - 5);
        currentBox.setHeight(gridPane.getHeight() / gridPane.getRowConstraints().size() - 5);
    }

    public void removeColumn(){
        if (currentCol == (gridPane.getColumnConstraints().size() - 1)){
            currentCol--;
            try {
                gridPane.add(currentBox, currentCol, currentRow);
            }
            catch (Exception e){}
        }
        if (gridPane.getColumnConstraints().size() > 5) {
            gridPane.getColumnConstraints().remove(gridPane.getColumnConstraints().size() - 1);
            viewGridSize();
            currentBox.setWidth(gridPane.getWidth() / gridPane.getColumnConstraints().size() - 5);
            currentBox.setHeight(gridPane.getHeight() / gridPane.getRowConstraints().size() - 5);
        }
    }

    public void removeRow(){
        if (currentRow == (gridPane.getRowConstraints().size() - 1)){
            currentRow--;
            try {
                gridPane.add(currentBox, currentCol, currentRow);
            }
            catch (Exception e){}
        }
        if (gridPane.getRowConstraints().size() > 5) {
            gridPane.getRowConstraints().remove(gridPane.getRowConstraints().size() - 1);
            viewGridSize();
            currentBox.setWidth(gridPane.getWidth() / gridPane.getColumnConstraints().size() - 5);
            currentBox.setHeight(gridPane.getHeight() / gridPane.getRowConstraints().size() - 5);
        }
    }

    private void viewGridSize(){
        int r = gridPane.getRowConstraints().size();
        int c = gridPane.getColumnConstraints().size();
        sizeTextField.setText(r + "," + c);
    }

    /**
     * Finds where clicks occur in a GridPane
     */
    public void selectInGrid(double xAxis, double yAxis){
        Bounds bounds = gridPane.getBoundsInParent();

        double startingHeight = bounds.getMinY();
        double startingWidth = bounds.getMinX();

        int rows = gridPane.getRowConstraints().size();
        int cols = gridPane.getColumnConstraints().size();

        double width = gridPane.getWidth();
        double height = gridPane.getHeight();

        double colWidth = width / cols;
        double rowHeight = height / rows;

        double xPos = 0;

        currentCol = 0;

        while ((xAxis - startingWidth) > xPos){
            xPos = currentCol * colWidth;
            currentCol++;
        }
        currentCol = currentCol - 2;

        double yPos = 0;
        currentRow = 0;

        while ((yAxis - startingHeight) > yPos){
            yPos = currentRow * rowHeight;
            currentRow++;
        }
        currentRow = currentRow -  2;

        try {
            gridPane.add(currentBox, currentCol, currentRow);
        }
        catch (Exception e){}
    }


    /**
     * Add a Field in the map
     */
    public void addField(){
        Rectangle field = new Rectangle(currentBox.getWidth(), currentBox.getHeight());
        field.setFill(Paint.valueOf("GREEN"));
        gridPane.add(field, currentCol, currentRow);
    }

    /**
     * Add Water in the map
     */
    public void addWater(){
        Rectangle water = new Rectangle(currentBox.getWidth(), currentBox.getHeight());
        water.setFill(Paint.valueOf("BLUE"));
        gridPane.add(water, currentCol, currentRow);
    }

    /**
     * Add Empty square in the map
     */
    public void addVoid(){
        Rectangle empty = new Rectangle(currentBox.getWidth(), currentBox.getHeight());
        empty.setFill(Paint.valueOf("BLACK"));
        gridPane.add(empty, currentCol, currentRow);
    }

}
