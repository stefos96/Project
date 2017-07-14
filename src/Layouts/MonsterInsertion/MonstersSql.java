package Layouts.MonsterInsertion;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;


public class MonstersSql implements Runnable{

    private Connection conn;
    private Statement stmt;
    private MysqlDataSource dataSource = new MysqlDataSource();

    private ResultSet monsters;
    private ArrayList<String> monsterList = new ArrayList<>();


    public MonstersSql(){
        dataSource.setUser("stefos96");
        dataSource.setPassword("stefos1996");
        dataSource.setServerName("db50.grserver.gr");
    }

    @Override
    public void run() {
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            monsters = stmt.executeQuery("SELECT name FROM test1.monster");
            while (monsters.next())
                monsterList.add(monsters.getString("name"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getMonsterList(){
        return monsterList;
    }
}
