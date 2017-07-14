package Layouts;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javafx.scene.shape.Rectangle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Model of the MVC patern where all data pass to the controller
 */
public class Model {

    private int columns;
    private int rows;

    private ArrayList<ArrayList<Rectangle>> rectangleArray = new ArrayList<>();

    /**
     * Get a resultSet(table) from the database
     * @param table a table name
     * @return a table in the form of a ResultSet
     */
    public ResultSet resultSet(String table){
        MysqlDataSource dataSource = new MysqlDataSource();
        Connection conn;
        Statement stmt;

        dataSource.setUser("stefos96");
        dataSource.setPassword("stefos1996");
        dataSource.setServerName("db50.grserver.gr");

        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            return stmt.executeQuery("SELECT name FROM test1." + table);
        }
        catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    /**
     * Set the array size for the map creation and for the monster insertion
     */
    public void updateArraySize(int rows, int columns){
        this.columns = columns;
        this.rows = rows;
    }

    public int getColumns(){
        return columns;
    }

    public int getRows(){
        return rows;
    }

    public void setRectangleArray(ArrayList<ArrayList<Rectangle>> rectangleArray){
        this.rectangleArray = rectangleArray;
    }
}
