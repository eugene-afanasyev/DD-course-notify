package notify.builders;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.WindowEvent;
import notify.alignments.HorizontalAlignment;
import notify.Notification;
import notify.alignments.VerticalAlignment;
import notify.callback.ReturnValuesTuple;

public class NotificationBuilder {
    private Notification notification = new Notification();

    public NotificationBuilder() {
        notification = new Notification();
    }

    public Notification build() {
        return notification;
    }

    public void setMessage(String message) {
        notification.setNotifyMessage(message);
    }

    public void setHeight(int height) {
        notification.setHeight(height);
    }

    public void setWidth(int width) {
        notification.setWidth(width);
    }

    public void setOpacity(double opacity) {
        notification.setOpacity((0 < opacity && opacity < 1) ? opacity : 1);
    }

    public void setHorizontalAlignment(HorizontalAlignment hAlign) {
        notification.setHorizontalAlignment(hAlign);
    }

    public void setVerticalAlignment(VerticalAlignment vAlign) {
        notification.setVerticalAlignment(vAlign);
    }

    public void addControlButtons() {
        notification.addControlButtons();
    }

    public void setLabelComboBox(ObservableList<String> items, String promptText) {
        ObservableList<Label> labels = FXCollections.observableArrayList();
        for (String string :
                items) {
            Label label = new Label(string);
            label.setMinHeight(Region.USE_PREF_SIZE);
            label.setAlignment(Pos.TOP_CENTER);
            label.setFont(new Font(14));
            label.setWrapText(true);
            labels.add(label);
        }
        notification.addComboBox(labels, promptText);
    }

    public void addInputField() {
        notification.addInputField();
    }

    public void addSlidingEffect() {
        notification.addSlidingEffect();
    }

    public void addNotificationSound() {
        notification.addNotificationSound();
    }

    public void setOnNotifiactionClosed(EventHandler<WindowEvent> eventHandler) {
        notification.setOnNotificationClosed(eventHandler);
    }
}
