package Item;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.jsoup.Jsoup;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Item implements Runnable{
    private String name;

    private String category; // propably can be done with an enum
    private String subcategory;

    private boolean specialAbility;

    private String aura;

    private int casterLevel;

    // in copper pieces
    private String price;

    private int manifesterLevel;

    private String prereq;

    // don't know what this is for when we have a price
    private String cost; // TODO some work 2,100 GP + 160 XP or

    private int weight;

    private String fullText;

    /**
     * Constructor that sets all it's properties via sql statement which runs in a new thread
     * @param name the item's name
     */
    public Item(String name) {
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM test1.item WHERE name='" + name + "'");

            while (rs.next()) {
                String temp;

                category = rs.getString("category");

                temp = rs.getString("subcategory");
                if (temp != null)
                    subcategory = temp;

                specialAbility = Boolean.getBoolean(rs.getString("special_ability"));

                temp = rs.getString("caster_level");
                if (temp != null)
                    casterLevel = Integer.parseInt(temp);

                // TODO: sometimes it's +6 bonus
                price = rs.getString("price");
                 /*
                temp = rs.getString("price");
                temp = temp.replaceAll(" gp", "");
                price = Integer.parseInt(temp);
                */

                temp = rs.getString("manifester_level");
                if (temp != null)
                    manifesterLevel = Integer.parseInt(temp);

                prereq = rs.getString("prereq");

                cost = rs.getString("cost");

                temp = rs.getString("weight");
                if (temp != null) {
                    temp = temp.replaceAll(" lb", "");
                    weight = Integer.parseInt(temp);
                }

                fullText = htmlToText(rs.getString("full_text"));
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private String htmlToText(String html) {
        return Jsoup.parse(html).text();
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getAura() {
        return aura;
    }

    public int getCasterLevel() {
        return casterLevel;
    }

    public String getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public String getFullText() {
        return fullText;
    }
}
