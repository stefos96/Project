package Layouts.Menu;
import Layouts.MainGame.MainGame;
import Races.RacesEnum;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainMenu extends Application implements EventHandler<MouseEvent>{
    private Scene scene;

    private SVGPath enterGameButton;
    private SVGPath loadButton;
    private SVGPath exitButton;
    private TextField nameTextField;
    private ComboBox raceComboBox;
    private ComboBox classComboBox;
    private ArrayList <String> classArray = new ArrayList<>();
    private ArrayList <String> raceArray = new ArrayList<>();
    private MysqlDataSource dataSource = new MysqlDataSource();
    private Connection conn;
    private Statement stmt;

    @Override
    public void start(Stage primaryStage) throws Exception{

        dataSource.setUser("stefos96");
        dataSource.setPassword("stefos1996");
        dataSource.setServerName("db50.grserver.gr");

        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM test1.class ORDER BY name");
            while (rs.next()) {
                classArray.add(rs.getString("name"));
            }
            rs = stmt.executeQuery("SELECT name FROM test1.race ORDER BY name");
            while (rs.next()) {
                raceArray.add(rs.getString("name"));
            }
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Something went wrong");
            alert.setContentText("Connection failure");
            alert.showAndWait();
        }



        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        scene = new Scene(root,430,540);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Name TextField
        nameTextField = (TextField) scene.lookup("#nameTextField");

        // Enter game button and it's listener
        enterGameButton = (SVGPath) scene.lookup("#enterGameButton");
        enterGameButton.setOnMouseClicked(this);

        // Enter game button and it's listener
        loadButton = (SVGPath) scene.lookup("#loadButton");
        loadButton.setOnMouseClicked(this);

        // Enter game button and it's listener
        exitButton = (SVGPath) scene.lookup("#exitButton");
        exitButton.setOnMouseClicked(this);

        // Race comboBox;
        raceComboBox = (ComboBox) scene.lookup("#raceComboBox");
        raceComboBox.getItems().setAll(raceArray);

        // Class comboBox;
        classComboBox = (ComboBox) scene.lookup("#classComboBox");
        classComboBox.getItems().setAll(classArray);
    }


    @Override
    public void handle(MouseEvent event) {
        // New game button click that closes this form and opens the MainGame form
        if (event.getSource() == enterGameButton){
            if (areFieldsFilled()) {
                HashMap<String, String> myClass = new HashMap<>();
                try {
                    String selectedClass = (String) classComboBox.getSelectionModel().getSelectedItem();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM test1.class WHERE name='" + selectedClass +"'");
                    while (rs.next()) {
                        myClass.put("name", rs.getString("name"));
                        myClass.put("alignment", rs.getString("alignment"));
                        myClass.put("hit_die", rs.getString("hit_die"));
                        myClass.put("class_skills", rs.getString("class_skills"));
                        myClass.put("spell_stat", rs.getString("spell_stat"));
                        myClass.put("proficiencies", rs.getString("proficiencies"));
                        myClass.put("spell_type", rs.getString("spell_type"));
                        myClass.put("epic_feat_base_level", rs.getString("epic_feat_base_level"));
                        myClass.put("epic_feat_interval", rs.getString("epic_feat_interval"));
                        myClass.put("epic_feat_list", rs.getString("epic_feat_list"));
                        myClass.put("req_race", rs.getString("req_race"));
                    }

                    rs.close();
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Something went wrong");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }

                String name = nameTextField.getText();
                String race = (String) raceComboBox.getSelectionModel().getSelectedItem();
                // Closes current window
                Stage stage = (Stage) enterGameButton.getScene().getWindow();
                stage.close();
                try {
                    MainGame a = new MainGame(name, myClass);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Fill all the fields");
                alert.setContentText("Enter your name and pick a race");

                alert.showAndWait();
            }
        }

        // LoadButton opens a file dialog
        if (event.getSource() == loadButton) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose a load File");
            fileChooser.showOpenDialog(loadButton.getScene().getWindow());
        }

        // Exit game button
        if (event.getSource() == exitButton)
            System.exit(0);
    }

    // Returns
    private boolean areFieldsFilled(){
        return !(nameTextField.getText().equals("")
                || raceComboBox.getSelectionModel().isEmpty()
                || classComboBox.getSelectionModel().isEmpty());
    }


    public static void main(String[] args) {
        launch(args);
    }
}