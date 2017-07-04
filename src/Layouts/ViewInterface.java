package Layouts;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public interface ViewInterface {
    public void show();
    public void hide();
    public void setButtonListener(String button, EventHandler<? super MouseEvent> listener);

    public void setTextField(String textField, String text);
    public String getTextField(String textField);
    public void setLabel(String label, String text);
    public String getComboBoxSelection(String comboBox);
    public boolean areFieldsFilled();
}
