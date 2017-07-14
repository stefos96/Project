package Layouts;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public interface ViewInterface {
    public void show();
    public void hide();
    public void setButtonListener(String button, EventHandler<? super MouseEvent> listener);
}
