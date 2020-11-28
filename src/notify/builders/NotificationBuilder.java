package notify.builders;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
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

    private HBox controlsPane = new HBox();

    private Label notifyMessage;

    public NotificationBuilder() {
        width = 400;
        height = 300;
    }

    public Notification build() {
        notification = new Notification(
                width, height, opacity,
                verticalAlignment, horizontalAlignment, notifyMessage);
        notification.addControls(controlsPane);

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
        Button okButton = new Button("Ok");
        okButton.setFont(new Font("Bold", 18));
        okButton.setBackground(Background.EMPTY);
        okButton.setTextFill(Paint.valueOf("#bbccdd"));
        okButton.setStyle("-fx-border-width: 1; -fx-border-color: #bbccdd; -fx-border-radius: 1");

        Button cancelButton = new Button("Cancel");
        cancelButton.setFont(new Font(18));
        cancelButton.setBackground(Background.EMPTY);
        cancelButton.setTextFill(Paint.valueOf("#bbccdd"));
        cancelButton.setStyle("-fx-border-width: 1; -fx-border-color: #bbccdd; -fx-border-radius: 1");

        controlsPane = new HBox(okButton, cancelButton);
        controlsPane.setAlignment(Pos.BOTTOM_CENTER);
        controlsPane.setSpacing(5);
    }
}
