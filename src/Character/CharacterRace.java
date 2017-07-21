package Character;
import Size.Size;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CharacterRace implements Runnable{
    private String name;
    // Abiltiy Adjustments
    private int raceStr;
    private int raceDex;
    private int raceCon;
    private int raceInt;
    private int raceWis;
    private int raceCha;

    private Size size;
    private int speed;

    private String bonusFeat;
    private String favoredClass;

    public CharacterRace(){}

    public CharacterRace(String raceName){
        name = raceName;
    }

    /**
     * Sets all ability adjustments from a string like +2 con, -2 wis
     * TODO: cases where it's 'any'
     */
    public void setAbilityAdjustments(String abilityAdjustment){
        abilityAdjustment = abilityAdjustment.replaceAll(",", "");

        int i = 0;
        while (i < abilityAdjustment.length()) {
            try {
                char prefix = abilityAdjustment.charAt(i);
                int number = Integer.parseInt(abilityAdjustment.substring(i + 1, i + 2));

                if (prefix == '-')
                    number = number * -1;

                String ability = abilityAdjustment.substring(i + 3, i + 6);

                switch (ability){
                    case "str":
                        raceStr = number;
                        break;
                    case "dex":
                        raceDex = number;
                        break;
                    case "con":
                        raceCon = number;
                        break;
                    case "int":
                        raceInt = number;
                        break;
                    case "wis":
                        raceWis = number;
                        break;
                    case "cha":
                        raceCha = number;
                        break;
                }
            }
            catch (Exception e){}


            i += 7;
        }
    }

    public void setSize(String size){
        this.size = Size.valueOf(size.toUpperCase());
    }

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void run() {
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM test1.race");
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getRaceStr() {
        return raceStr;
    }

    public int getRaceDex() {
        return raceDex;
    }

    public int getRaceCon() {
        return raceCon;
    }

    public int getRaceInt() {
        return raceInt;
    }

    public int getRaceWis() {
        return raceWis;
    }

    public int getRaceCha() {
        return raceCha;
    }
}

