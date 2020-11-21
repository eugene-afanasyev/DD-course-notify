package notify.builders;

import notify.alignments.HorizontalAlignment;
import notify.Notification;
import notify.alignments.VerticalAlignment;

public class NotificationBuilder {
    private Notification notification;

    private int width;
    private int height;
    private double opacity = 1;
    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.CENTER;
    private VerticalAlignment verticalAlignment = VerticalAlignment.CENTER;

    public NotificationBuilder() {
        width = 400;
        height = 300;
    }

    public Notification build() {
        notification = new Notification(
                width, height, opacity,
                verticalAlignment, horizontalAlignment);

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

    public void setHorizontalAlignment(HorizontalAlignment hAlign) {
        horizontalAlignment = hAlign;
    }

    public void setVerticalAlignment(VerticalAlignment vAlign) {
        verticalAlignment = vAlign;
    }
}
