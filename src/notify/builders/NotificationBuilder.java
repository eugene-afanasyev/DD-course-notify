package notify.builders;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
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

    private Label notifyMessage;

    public NotificationBuilder() {
        width = 400;
        height = 300;
    }

    public Notification build() {
        notification = new Notification(
                width, height, opacity,
                verticalAlignment, horizontalAlignment, notifyMessage);

        return notification;
    }

    public void buildMessage(int fontSize, Paint paint, String message, int padding) {
        notifyMessage = new Label(message);
        notifyMessage.setMinHeight(Region.USE_PREF_SIZE);
        notifyMessage.setAlignment(Pos.TOP_CENTER);
        notifyMessage.setPadding(new Insets(padding));
        notifyMessage.setFont(new Font(fontSize));
        notifyMessage.setTextFill(paint);
        notifyMessage.setWrapText(true);

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

    public void addControlButtons() {

    }
}
