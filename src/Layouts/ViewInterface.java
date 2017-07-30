package Layouts;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public interface ViewInterface {
    public void show();
    public void hide();
    public void setButtonListener(String button, EventHandler<? super MouseEvent> listener);
    public void setButtonListener(String comboBox, ChangeListener changeListener);
}
