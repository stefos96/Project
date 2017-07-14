package Layouts.MonsterInsertion;
import Layouts.ViewInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
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

public class MonsterInsertion implements ViewInterface {
    private Scene scene;
    private Stage stage;

    private SVGPath nextButton;
    private SVGPath backButton;

    private GridPane gridPane;

    private ListView listView;

    private int columns;
    private int rows;

    private int currentCol;
    private int currentRow;

    private ArrayList<String> monsterInserted = new ArrayList<>();

    public MonsterInsertion() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MonsterInsertion.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);

        nextButton = (SVGPath) scene.lookup("#nextButton");
        backButton = (SVGPath) scene.lookup("#backButton");

        gridPane = (GridPane) scene.lookup("#gridPane");

        TitledPane titledPane;
        titledPane = (TitledPane) scene.lookup("#titledPane");
        listView = (ListView) titledPane.getContent();
        titledPane = null;

        printMonsters();
    }

    @Override
    public void show() {
        stage.show();

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
            case "gridPane":
                gridPane.setOnMouseClicked(listener);
        }
    }

    public void setColumns(int columns){
        this.columns = columns;
    }

    public void setRows(int rows){
        this.rows = rows;
    }

    private void printMonsters(){
        Runnable monstersSql = new MonstersSql();
        monstersSql.run();
        ArrayList<String> monsters = ((MonstersSql) monstersSql).getMonsterList();

        ObservableList<String> monstersObservable = FXCollections.observableArrayList(monsters);
        listView.setItems(monstersObservable);
    }

    /**
     * Simple selection when a click occurs in gridPane highlighting the box it's targetted
     */
    public void selectInGrid(double xAxis, double yAxis){
        Node node = gridPane.getChildren().get(0);
        gridPane.getChildren().clear();
        gridPane.getChildren().add(0,node);

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
        gridPane.add(currentRectangle, currentCol, currentRow);
    }
}
