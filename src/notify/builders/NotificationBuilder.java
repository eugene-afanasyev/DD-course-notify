package notify.builders;

import notify.Notification;

public class NotificationBuilder {
    private Notification notification;

    private int width;
    private int height;
    private double opacity = 1;

    public NotificationBuilder() {
        width = 400;
        height = 300;
    }

    public Notification build() {
        notification = new Notification(
                width, height, opacity);
        return notification;
    }

    public void setHeight(int height) {
        this.height = height % 500;
    }

    public void setWidth(int width) {
        this.width = width % 600;
    }

    public void setOpacity(double opacity) {
        this.opacity = (0 < opacity && opacity < 1) ? opacity : 1;
    }
}
