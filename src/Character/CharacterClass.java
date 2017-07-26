package Character;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CharacterClass implements Runnable{
    private int classLevel = 0;
    private String name;
    private String type;
    private String alignment;
    private HitDieEnum hitDie;
    private String skills; // TODO skill class
    private int skillPoints;

//    private String skillPointsAbility; it's always 'int'

    private SpellStatEnum spellStat;
    private String proficiencies;
    private String spellType;
    private int epicFeatBaseLevel;
    private int epicFeatInterval;
    private String epicFeatList;

    // Requirments to choose class
    private String requiredRace;
    private String requiredWeaponProficiency;
    private String requiredBaseAttackBonus;
    private String requiredSkill;
    private String requiredFeat;
    private String requiredSpells;
    private String requiredLanguages;
    private String requiredPsionics;
    private String requiredEpicFeat;
    private String requiredSpecial;

    // SpellLists
    private String spellList1;
    private String spellList2;
    private String spellList3;
    private String spellList4;
    private String spellList5;

    public CharacterClass(){}

    public CharacterClass(String className){
        name = className;
    }

    public void setAbilityAdjustment(){

    }

    public String getName() {
        return name;
    }

    public String getAlignment() {
        return alignment;
    }

    public int getHitDie() {
        return hitDie.number;
    }

    public int getClassLevel() {
        return classLevel;
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

            ResultSet rs = stmt.executeQuery("SELECT * FROM test1.class WHERE name='" + name + "'");
            while (rs.next()) {
                String temp; // we need it just to check if some strings are null
                type = rs.getString("type");
                alignment = rs.getString("alignment");
                temp = rs.getString("hit_die").toUpperCase();
                if (temp != null)
                    hitDie = HitDieEnum.valueOf(temp);
                skills = rs.getString("class_skills");
                temp = rs.getString("spell_stat");
                skillPoints = Integer.parseInt(rs.getString("skill_points"));
                if (spellStat != null)
                    spellStat = SpellStatEnum.valueOf(temp);
                proficiencies = rs.getString("proficiencies");
                spellType = rs.getString("spell_type");
                temp = rs.getString("epic_feat_base_level");
                if (temp != null)
                    epicFeatBaseLevel = Integer.parseInt(temp);
                temp = rs.getString("epic_feat_interval");
                if (temp != null)
                    epicFeatInterval = Integer.parseInt(temp);
                epicFeatList = rs.getString("epic_feat_list");
                requiredRace = rs.getString("req_race");
                requiredWeaponProficiency = rs.getString("req_weapon_proficiency");
                requiredBaseAttackBonus = rs.getString("req_base_attack_bonus");
                requiredSkill = rs.getString("req_skill");
                requiredFeat = rs.getString("req_feat");
                requiredSpells = rs.getString("req_spells");
                requiredLanguages = rs.getString("req_languages");
                requiredPsionics = rs.getString("req_psionics");
                requiredEpicFeat = rs.getString("req_epic_feat");
                requiredSpecial = rs.getString("req_special");
                spellList1 = rs.getString("spell_list_1");
                spellList2 = rs.getString("spell_list_2");
                spellList3 = rs.getString("spell_list_3");
                spellList4 = rs.getString("spell_list_4");
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

enum HitDieEnum{
    D4(4),
    D6(6),
    D8(8),
    D10(10),
    D12(12);

    int number;

    HitDieEnum(int number){
        this.number = number;
    }
}

enum SpellStatEnum{
    NULL,
    STR,
    DEX,
    CON,
    INT,
    WIS,
    CHA;
}

