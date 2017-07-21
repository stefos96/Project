package Monster;
import Size.Size;
import org.jsoup.Jsoup;


public class Monster{
    private String name;
    private String altName;
    private String family;
    private Size size;

    /**
     * Strips html tags
     */
    private String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}

