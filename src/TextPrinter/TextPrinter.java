package TextPrinter;
import javafx.scene.control.TextArea;

/**
 * Takes a String and a javafx.TextArea and it prints the string in a type writer effect
 */
public class TextPrinter implements Runnable{
    private TextArea textArea;
    private String sentence;

    public TextPrinter(TextArea textArea, String sentence){
        this.textArea = textArea;
        this.sentence = sentence;
    }

    @Override
    public void run() {
        for (int i = 0; i < sentence.length(); i++) {
            try {
                textArea.appendText(String.valueOf(sentence.charAt(i)));
                Thread.sleep(20);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
