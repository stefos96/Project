package Layouts;


import javafx.scene.control.TextArea;

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
                Thread.sleep(50);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
