package Equipment;
import Coins.Coins;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import Dice.Dice;
import org.jsoup.Jsoup;

public class Equipment implements Runnable{
    private String name;

    private String family;
    private String category;
    private String subcategory;
    private int cost;

    // dmg_s and it's number like 1d6/1d6
    private int dmgsNumber;
    private Dice dmgs;

    private int armorShieldBonus;
    private int maxDexBonus;

    // dmg_m and it's number like 1d6/1d4
    private int dmgmNumber;
    private Dice dmgm;

    // in lb. like "40 lb."
    private int weight;

    // multiplier where it can be X2 or 18-20/X3 which means either you can deal flat extra damage or the multiplier effect
    // hasFlatCritical indicates if it also gives the option for flat damage
    private boolean hasFlatCritical;
    private int critical;
    private int flatCritical;
    // some times it has something like 4 ft.
    private int criticalFt;

    // always negative
    private int armorCheckPenalty;

    // varchar with "20%" will become 0.2
    private double arcanceSpellFailureChance;


    private int rangeIncrement;
    // true if it's in feet
    private boolean isRangeInFt;


    private int speed30;
    private int speed20;

    private String type;

    private String fullText;


    /**
     * Sets all it's properties in a new thread where it runs an Sql query
     * @param name the name of the equipment
     */
    public Equipment(String name) {
        this.name = name;
        Thread thread = new Thread(name);
        thread.start();
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

            ResultSet rs = stmt.executeQuery("SELECT * FROM test1.equipment WHERE name='" + name + "'");
            while (rs.next()) {
                String temp; // to check for nulls

                family = rs.getString("family");
                category = rs.getString("category");

                temp = rs.getString("subcategory");
                if (temp != null)
                    subcategory = temp;

                Coins coin = Coins.COPPER;
                cost = coin.stringValueToCopper(rs.getString("cost"));

                temp = rs.getString("dmg_s");
                if (temp != null) {
                    dmgsNumber = Integer.parseInt(String.valueOf(temp.charAt(0)));
                    temp = temp.substring(1, temp.length());
                    dmgs = Dice.valueOf(temp.toUpperCase());
                }

                temp = rs.getString("armor_shield_bonus");
                if (temp != null)
                    armorShieldBonus = Integer.parseInt(temp);

                temp = rs.getString("maximum_dex_bonus");
                if (temp != null)
                    maxDexBonus = Integer.parseInt(temp);

                temp = rs.getString("dmg_m");
                if (temp != null) {
                    dmgmNumber = Integer.parseInt(String.valueOf(temp.charAt(0)));
                    temp = temp.substring(1, temp.length());
                    dmgm = Dice.valueOf(temp.toUpperCase());
                }

                temp = rs.getString("weight");
                if (temp != null) {
                    temp = temp.replaceAll(" lb.", "");
                    weight = Integer.parseInt(temp);
                }

                temp = rs.getString("critical");
                if (temp != null) {
                    if (temp.contains("/")) {
                        hasFlatCritical = true;
                        flatCritical = Integer.parseInt(temp.substring(0, 1));
                        critical = Integer.parseInt(String.valueOf(temp.charAt(temp.length())));
                    }
                    else if (temp.contains("ft.")) {
                        temp = temp.replaceAll(" ft.", "");
                        criticalFt = Integer.parseInt(temp);
                    }
                    else
                        critical = Integer.parseInt(String.valueOf(temp.charAt(temp.length())));
                }

                temp = rs.getString("armor_check_penalty");
                if (temp != null)
                    armorCheckPenalty = Integer.parseInt(temp); // there is a chance it will have a problem with minus numbers

                temp = rs.getString("arcance_spell_failure_chance");
                if (temp != null) {
                    temp = temp.replaceAll("%", "");
                    arcanceSpellFailureChance = Integer.parseInt(temp) / 100;
                }

                temp = rs.getString("range_increment");
                if (temp != null) {
                    if (temp.contains("ft.")) {
                        isRangeInFt = true;
                        temp = temp.replaceAll(" .ft", "");
                        rangeIncrement = Integer.parseInt(temp);
                    }
                    else if (temp.contains("lb.")) {
                        isRangeInFt = false;
                        temp = temp.replaceAll(" lb.", "");
                        rangeIncrement = Integer.parseInt(temp);
                    }
                }

                temp = rs.getString("speed_30");
                if (temp != null) {
                    temp = temp.replaceAll(" ft.", "");
                    speed30 = Integer.parseInt(temp);
                }

                temp = rs.getString("type");
                if (temp != null)
                    type = temp;

                temp = rs.getString("speed_20");
                if (temp != null) {
                    temp = temp.replaceAll(" ft.", "");
                    speed20 = Integer.parseInt(temp);
                }

                fullText = rs.getString("full_text");
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
