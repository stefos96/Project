package Skills;
import org.jsoup.Jsoup;

public class Skills {
    private String name;
    private String key_ability;

    private boolean psionic;
    private boolean trained;
    private boolean armor_check;

    public String html2text(String html) {
        return Jsoup.parse(html).text();
    }

}
