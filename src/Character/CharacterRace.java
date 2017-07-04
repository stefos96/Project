package Character;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CharacterRace {
    private String name;
    // Abiltiy Adjustments
    private int raceStr;
    private int raceDex;
    private int raceCon;
    private int raceInt;
    private int raceWis;
    private int raceCha;

    private SizeEnum size;
    private String speed;
    private String bonusFeat;
    private String favoredClass;
    private int str;
    private int dex;
    private int con;
    private int anInt;
    private int wis;
    private int cha;

    public CharacterRace(String raceName){
        name = raceName;
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM test1.race WHERE name='" + raceName + "'");
            while (rs.next()) {
                String abilityAdjustment = rs.getString("ability_adjustments");
                setAbilityAdjustments(abilityAdjustment);
                String temp = rs.getString("size").toUpperCase().trim();
                size = SizeEnum.valueOf(temp);
                speed = rs.getString("speed");
                bonusFeat = rs.getString("bonus_feat");
                favoredClass = rs.getString("favored_class");
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setAbilityAdjustments(String abilityAdjustment){
        int i = 0;
        while (abilityAdjustment.length() - 1 >= i){
            char tempChar = abilityAdjustment.charAt(i);
            if (tempChar == '+'){
                String temp = abilityAdjustment.substring(i+3, i+6);
                String number = String.valueOf(abilityAdjustment.charAt(i + 1));
                switch (temp){
                    case "str": raceStr = Integer.parseInt(number);
                        break;
                    case "con": raceCon = Integer.parseInt(number);
                        break;
                    case "dex": raceDex = Integer.parseInt(number);
                        break;
                    case "int": raceInt = Integer.parseInt(number);
                        break;
                    case "wis": raceWis = Integer.parseInt(number);
                        break;
                    case "cha": raceCha = Integer.parseInt(number);
                        break;
                }
            }
            if (tempChar == '-'){
                String temp = abilityAdjustment.substring(i+3, i+6);
                String number = String.valueOf(abilityAdjustment.charAt(i + 1));
                switch (temp){
                    case "str": raceStr = - Integer.parseInt(number);
                        break;
                    case "con": raceCon = - Integer.parseInt(number);
                        break;
                    case "dex": raceDex = - Integer.parseInt(number);
                        break;
                    case "int": raceInt = - Integer.parseInt(number);
                        break;
                    case "wis": raceWis = - Integer.parseInt(number);
                        break;
                    case "cha": raceCha = - Integer.parseInt(number);
                        break;
                }
            }
            i++;
        }
    }

    public String getName() {
        return name;
    }

    public SizeEnum getSize() {
        return size;
    }

    public String getSpeed() {
        return speed;
    }

    public int getStr() {
        return str;
    }

    public int getDex() {
        return dex;
    }

    public int getCon() {
        return con;
    }

    public int getInt() {
        return anInt;
    }

    public int getWis() {
        return wis;
    }

    public int getCha() {
        return cha;
    }
}

enum SizeEnum{
    SMALL,
    MEDIUM,
    LARGE;
}