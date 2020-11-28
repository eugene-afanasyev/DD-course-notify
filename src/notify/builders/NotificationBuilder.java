package notify.builders;

import javafx.scene.paint.Paint;
import notify.alignments.HorizontalAlignment;
import notify.Notification;
import notify.alignments.VerticalAlignment;

public class NotificationBuilder {
    private Notification notification;

    private int width;
    private int height;
    private double opacity = 1;
    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.CENTER;
    private VerticalAlignment verticalAlignment = VerticalAlignment.TOP;
    private String notificationMessage = "";

    public NotificationBuilder() {
        width = 400;
        height = 300;
    }

    public Notification build() {
        notification = new Notification(
                width, height, opacity,
                verticalAlignment, horizontalAlignment);

        notification.buildMessage(25, Paint.valueOf("#F0FFFF"), notificationMessage, 10);

        return notification;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setOpacity(double opacity) {
        this.opacity = (0 < opacity && opacity < 1) ? opacity : 1;
    }

    public void setHorizontalAlignment(HorizontalAlignment hAlign) {
        horizontalAlignment = hAlign;
    }

    public void setVerticalAlignment(VerticalAlignment vAlign) {
        verticalAlignment = vAlign;
    }

    public void setNotificationMessage(String message) {
        notificationMessage = message;
    }

    public void addControlButtons() {

    }
}
