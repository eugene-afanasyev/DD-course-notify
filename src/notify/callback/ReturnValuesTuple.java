package notify.callback;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class ReturnValuesTuple {
    NotificationButton notificationButton;
    String comboboxValue;
    String textFieldValue;

    public ReturnValuesTuple(
            NotificationButton notificationButton,
            String comboboxValue, String textFieldValue) {
        this.notificationButton = notificationButton;
        this.comboboxValue = comboboxValue;
        this.textFieldValue = textFieldValue;
    }

    public NotificationButton getNotificationButton() {
        return notificationButton;
    }

    public void setNotificationButton(NotificationButton notificationButton) {
        this.notificationButton = notificationButton;
    }

    public String getComboboxValue() {
        return comboboxValue;
    }

    public void setComboboxValue(String comboboxValue) {
        this.comboboxValue = comboboxValue;
    }

    public String getTextFieldValue() {
        return textFieldValue;
    }

    public void setTextFieldValue(String textFieldValue) {
        this.textFieldValue = textFieldValue;
    }
}

