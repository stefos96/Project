package Layouts.CreateCharacter;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CharacterSql implements Runnable{
    private Connection conn;
    private Statement stmt;
    private MysqlDataSource dataSource = new MysqlDataSource();

    private ArrayList<String> races = new ArrayList<>();
    private ArrayList<String> classes = new ArrayList<>();

    public CharacterSql(){
        dataSource.setUser("stefos96");
        dataSource.setPassword("stefos1996");
        dataSource.setServerName("db50.grserver.gr");
    }

    @Override
    public void run() {
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            ResultSet resultSet = stmt.executeQuery("SELECT name FROM test1.race");
            while (resultSet.next())
                races.add(resultSet.getString("name"));

            resultSet = stmt.executeQuery("SELECT name FROM test1.class");
            while (resultSet.next())
                classes.add(resultSet.getString("name"));

            stmt.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getRaces(){
        return races;
    }

    public ArrayList<String> getClasses(){
        return classes;
    }
}
