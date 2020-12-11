package notify.builders;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import notify.alignments.HorizontalAlignment;
import notify.Notification;
import notify.alignments.VerticalAlignment;

public class NotificationBuilder {
    private Notification notification = new Notification();

    private int width;
    private int height;
    private double opacity = 1;
    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.CENTER;
    private VerticalAlignment verticalAlignment = VerticalAlignment.TOP;

    private Label notifyLabel;

    private boolean controlsAdded = false;

    public NotificationBuilder() {
        notification = new Notification();
        width = 400;
        height = 300;
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
}
