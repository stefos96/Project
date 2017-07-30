package Skills;
import SpellStat.SpellStat;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.jsoup.Jsoup;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Skills implements Runnable{
    private String name;
    private SpellStat keyAbility;

    private boolean psionic;
    private boolean trained;
    private boolean armorCheck;

    private String description;
    private String skillCheck;
    private String action;
    private String tryAgain;

    private String special;
    private String restriction;
    private String synergy;
    private String epicUse;
    private String untrained;

    private String fullText;


    /**
     * Skills constructor that creates a new thread and sets it's properties via sql quary
     * @param name the name of the skill
     */
    public Skills(String name) {
        this.name = name;
        Thread thread = new Thread(this);
        thread.start();
    }

    private String htmlToText(String html) {
        return Jsoup.parse(html).text();
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM test1.skill WHERE name='" + name + "'");
            while (rs.next()) {
                keyAbility = SpellStat.valueOf(rs.getString("key_ability"));

                psionic = Boolean.getBoolean(rs.getString("psionic"));
                trained = Boolean.getBoolean(rs.getString("trained"));
                armorCheck = Boolean.getBoolean(rs.getString("armor_check"));

                String temp = rs.getString("description");
                if (temp != null)
                    this.description = htmlToText(temp);

                skillCheck = rs.getString("skill_check");
                action = rs.getString("action");

                temp = rs.getString("try_again");
                if (temp != null)
                    this.tryAgain = temp;

                temp = rs.getString("special");
                if (temp != null)
                    this.special = temp;

                temp = rs.getString("restriction");
                if (temp != null)
                    this.restriction = temp;

                temp = rs.getString("synergy");
                if (temp != null)
                    this.synergy = temp;

                temp = rs.getString("epic_use");
                if (temp != null)
                    this.epicUse = htmlToText(temp);

                temp = rs.getString("untrained");
                if (temp != null)
                    this.untrained = temp;

                temp = rs.getString("full_text");
                fullText = htmlToText(temp);
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
