package Monster;
import Dice.Dice;
import Size.Size;
import SpellStat.SpellStat;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.sun.javafx.image.IntPixelGetter;
import org.jsoup.Jsoup;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Monster implements Runnable{
    private String name;
    private String altName;
    private String family;
    private Size size;
    private String type;
    private String descritpor;

    // something like "66d12 + 360(700 hp)
    private int hitDiceNumber;
    private Dice hitDice;
    private int hp;
    private int overallHp;

    private int initiative;

    private String speed;

    private int armorClass;
    private int touch;
    private int flatFooted;

    private int baseAttack;

    private int grapple;

    private String attack;
    private String fullAttack;

    private int space;
    private int reach;

    private String specialAttacks;
    private String specialQualities;

    private int fort;
    private int ref;
    private int will;

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private String skills;
    private String bonusFeats;
    private String feats;
    private String epicFeats;

    private String environment;

    private String organization;

    private int challengeRation;

    // TODO: enum would be helpfull
    private String treasure;

    // TODO: enum already exists, analyze it and use it
    private String alignment;

    // TODO: analyzation is needed most propably (sadly)
    private String advancement;

    private String specialAbilities;

    private String statBlock;

    private String fullText;

    /**
     * Sets it's properties by setting the monster's name and running asynchronously an sql query that retrieves the data
     * @param name the monster's name which based on sets it's properties
     */
    public Monster(String name) {
        this.name = name;
        Thread thread = new Thread(this);
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

            ResultSet rs = stmt.executeQuery("SELECT * FROM test1.monster WHERE name='" + name + "'");
            while (rs.next()) {
                String temp;

                family = rs.getString("family");

                temp = rs.getString("altname");
                if (temp != null)
                    altName = temp;

                temp = rs.getString("size");
                size = Size.valueOf(temp.toUpperCase());

                type = rs.getString("type");

                temp = rs.getString("desriptor");
                if (temp != null)
                    descritpor = temp;



                // hit_dice "66d12 (792 hp)" or "27d8+189 (425 hp)" or "38d10+684 (893 hp)"
                temp = rs.getString("hit_dice");
                temp = temp.replaceAll(",", "");

                int indexOfD = temp.charAt('d');
                hitDiceNumber = Integer.parseInt(temp.substring(0, indexOfD - 1));

                // cases "d8+" or "d8 *" "d12+*" or "d12 *"
                String hitDiceTemp = temp.substring(indexOfD, indexOfD + 2).toUpperCase();
                if (hitDiceTemp.contains("+"))
                    hitDice = Dice.valueOf(hitDiceTemp.substring(0, 1));
                else if (hitDiceTemp.contains(" "))
                    hitDice = Dice.valueOf(hitDiceTemp.substring(0, 1));
                else
                    hitDice = Dice.valueOf(hitDiceTemp);

                int indexOfParStart = temp.indexOf("(");
                int indexOfParEnd = temp.indexOf(")");

                if (temp.contains("+")) {
                    int indexOfPlus = temp.indexOf("+");
                    hp = Integer.parseInt(temp.substring(indexOfPlus, indexOfParStart - 1));
                }

                overallHp = Integer.parseInt(temp.substring(indexOfParStart + 1, indexOfParEnd - 1));

                temp = rs.getString("initiative");

                indexOfParStart = temp.indexOf("(");
                indexOfParEnd = temp.indexOf(")");

                // TODO: find if it's +6dex and +2 improved initiative
                if (temp.indexOf("+") == 0) {
                    int indexOfPlus = temp.indexOf("+");
                    initiative = Integer.parseInt(temp.substring(indexOfPlus + 1, indexOfParStart - 2));
                }
                else if (temp.indexOf("-") == 0) {
                    int indexOfMinus = temp.indexOf("-");
                    initiative = Integer.parseInt(temp.substring(indexOfMinus + 1, indexOfParStart - 2)) * -1;
                }


                // TODO: a lot of work here
                speed = rs.getString("speed");

                // 21 (-1 size, +2 Dex, +10 natural), touch 11, flat-footed 19
                temp = rs.getString("armor_class");
                // clear everything in parenthesis
                temp = temp.substring(0, temp.indexOf("(") - 2) + temp.substring(temp.indexOf(")") + 1);
                // we now have "21, touch 11, flat-footed 19"
                temp = temp.replaceAll(",", "");
                temp = temp.replaceAll(" ", "");
                // we now have "21touch11flat-footed19"
                int indexOfTouch = temp.indexOf("touch");
                int indexofFlat = temp.indexOf("flat-footed");
                String armor = temp.substring(0, indexOfTouch - 1);
                String touch = temp.substring(indexOfTouch + 4, indexofFlat - 1);
                String flat = temp.substring(indexofFlat + 11, temp.length());
                armorClass = Integer.parseInt(armor);
                this.touch = Integer.parseInt(touch);
                this.flatFooted = Integer.parseInt(flat);

                temp = rs.getString("base_attack");
                if (temp.contains("+"))
                    baseAttack = Integer.parseInt(temp.substring(1));
                else if (temp.contains("-"))
                    baseAttack = Integer.parseInt(temp.substring(1)) * -1;

                temp = rs.getString("grapple");
                if (temp.contains("+"))
                    grapple = Integer.parseInt(temp.substring(1));
                else if (temp.contains("-"))
                    grapple = Integer.parseInt(temp.substring(1)) * -1;

                // TODO: a lot of analyzation is needed here
                attack = rs.getString("attack");

                // TODO: same here
                fullAttack = rs.getString("full_attack");

                temp = rs.getString("space");
                temp = temp.replaceAll(" ft.", "");
                space = Integer.parseInt(temp);


                temp = rs.getString("reach");
                reach = Integer.parseInt(temp.substring(0, temp.indexOf(" ft.") - 1));

                specialAttacks = rs.getString("special_attacks");

                specialQualities = rs.getString("special_qualities");


            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Strips html tags
     */
    private String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}