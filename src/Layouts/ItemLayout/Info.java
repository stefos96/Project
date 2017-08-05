package Layouts.ItemLayout;
import Layouts.ViewInterface;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class Info implements ViewInterface{
    private Scene scene;
    private Stage stage;

    private ScrollPane columnScrollPane;
    private ScrollPane contentScrollPane;

    private VBox columnVBox;
    private VBox contentVBox;

    private ToolBar toolBar;

    private TextField searchTextField;
    private Circle searchButton;
    private ComboBox comboBox;

    private String table;
    private String name;

    private ArrayList<String> contentArrayList = new ArrayList<>();
    private ArrayList<String> columnArrayList = new ArrayList<>();



    public Info() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Info.fxml"));
        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);

        columnScrollPane = (ScrollPane) scene.lookup("#columnScrollPane");
        contentScrollPane = (ScrollPane) scene.lookup("#contentScrollPane");

        columnScrollPane.addEventFilter(ScrollEvent.ANY, new ParallelScroll());
        contentScrollPane.addEventFilter(ScrollEvent.ANY, new ParallelScroll());

        columnVBox = (VBox) columnScrollPane.getContent();
        contentVBox = (VBox) contentScrollPane.getContent();


        toolBar = (ToolBar) scene.lookup("#toolBar");

        searchTextField = (TextField) scene.lookup("#searchTextField");
        searchButton = (Circle) scene.lookup("#searchButton");
        comboBox = (ComboBox) scene.lookup("#comboBox");

        // add the table names to the comboBox
        setComboBox();

        // add change listener to the comboBox
        this.comboBox.valueProperty().addListener(new ComboBoxChangeValue());

        // Button listener to searchButton
        searchButton.setOnMouseClicked(new SearchButton());
    }


    public void setContent() {
        for (String sentence: contentArrayList){
            Label label = new Label(sentence);
            label.setTooltip(new Tooltip(sentence));
            Separator separator = new Separator();
            contentVBox.getChildren().add(label);
            contentVBox.getChildren().add(separator);
        }

        for (String sentence: columnArrayList){
            Label columnLabel = new Label(sentence);
            Separator columnSeparator = new Separator();
            columnVBox.getChildren().add(columnLabel);
            columnVBox.getChildren().add(columnSeparator);
        }
    }


    private void findItem() {
        // Clear arrays so they dont have any data
        contentVBox.getChildren().clear();
        columnVBox.getChildren().clear();
        columnArrayList.clear();
        contentArrayList.clear();
        toolBar.getItems().clear();

        // SQL connections
        Connection conn;
        Statement stmt;
        MysqlDataSource dataSource = new MysqlDataSource();

        dataSource.setUser("stefos96");
        dataSource.setPassword("stefos1996");
        dataSource.setServerName("db50.grserver.gr");

        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM test1." + table + " WHERE name='" + name + "'");

            // Column names
            ResultSetMetaData rsmd = rs.getMetaData();

            // Add the column names into the columnArrayList
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                columnArrayList.add(rsmd.getColumnName(i));
            }

            // Get the content and put it in the contentArrayList
            rs.next();
            int i = 1;
            try {
                while (true) {
                    contentArrayList.add(rs.getString(i));
                    i++;
                }
            }
            catch (Exception e) {
                System.out.println("rs contents ended");
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception e){
            System.out.println("Database error or wrong query");
        }
        finally {
            if (contentArrayList.isEmpty())
                giveBubbleOptions();
        }
    }





    /**
     * In case the word the user gave to the searchTextField has more than one result
     * this method will create SVGpaths like bubbles that are selectable so the user can
     * view the desired result.
     *
     */
    private void giveBubbleOptions() {
        // array for the bubbles
        ArrayList<String> arrayList = new ArrayList<>();

        // SQL connections
        Connection conn;
        Statement stmt;
        MysqlDataSource dataSource = new MysqlDataSource();

        dataSource.setUser("stefos96");
        dataSource.setPassword("stefos1996");
        dataSource.setServerName("db50.grserver.gr");

        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT name FROM test1." + table + " WHERE name like '%" + name + "%'");

            while (rs.next()) {
                arrayList.add(rs.getString("name"));
            }

            if (!arrayList.isEmpty()) {
                for (String name: arrayList) {
                    Button button = new Button(name);
                    // just bellow
                    button.setOnMouseClicked(new BubbleClicked());
                    toolBar.getItems().add(button);
                }
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    class BubbleClicked implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
             name = ((Button) event.getSource()).getText();
             findItem();
             setContent();
        }
    }




    // table names to the comboBox
    private void setComboBox() {
        ArrayList<String> tableArrayList = new ArrayList<>();
        tableArrayList.add("class");
        tableArrayList.add("class_table");
        tableArrayList.add("domain");
        tableArrayList.add("equipment");
        tableArrayList.add("feat");
        tableArrayList.add("item");
        tableArrayList.add("monster");
        tableArrayList.add("power");
        tableArrayList.add("race");
        tableArrayList.add("skill");
        tableArrayList.add("spell");

        comboBox.setItems(FXCollections.observableArrayList(tableArrayList));
    }


    class ParallelScroll implements EventHandler<ScrollEvent> {
        @Override
        public void handle(ScrollEvent event) {
            if (event.getSource().equals(contentScrollPane))
                columnScrollPane.setVvalue(contentScrollPane.getVvalue());
            else
                contentScrollPane.setVvalue(columnScrollPane.getVvalue());
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
    }

    @Override
    public void setButtonListener(String comboBox, ChangeListener changeListener) {
    }

    // When the comboBox selection changes, change the table value
    class ComboBoxChangeValue implements ChangeListener {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            table = comboBox.getSelectionModel().getSelectedItem().toString();
        }
    }

    // Search
    class SearchButton implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            name = searchTextField.getText();
            findItem();
            setContent();
        }
    }
}