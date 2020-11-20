package notify;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;

enum HorizontalAlignment {
    LEFT, RIGHT, CENTER
}

enum VerticalAlignment {
    TOP, BOTTOM, CENTER
}

public class Notification {
    private final Stage notifyStage;
    private final Pane canvas;
//    private final VerticalAlignment vAlign;
//    private final HorizontalAlignment hAlign;

    Label notifyMessage = new Label("");
    Button closeButton = new Button("Close");

    public Notification(int width, int height, double opacity) {
        closeButton.setFont(new Font(16));
        var buttonBg = new Background(new BackgroundFill(Color.ALICEBLUE, new CornerRadii(1), Insets.EMPTY));
        closeButton.setBackground(buttonBg);
        closeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                notifyStage.close();
            }
        });

        FlowPane root = new FlowPane(notifyMessage, closeButton);
        root.setBackground(Background.EMPTY);
        root.setAlignment(Pos.TOP_RIGHT);

        canvas = new Pane(root);
        canvas.setStyle("-fx-background-color: dimgray;");
        canvas.setPrefSize(width,height);

        notifyStage = new Stage();
        notifyStage.setOpacity(opacity);
        notifyStage.initStyle(StageStyle.UNDECORATED);
        Scene notifyScene = new Scene(canvas);
        notifyStage.setAlwaysOnTop(true);
        notifyStage.setScene(notifyScene);

    }

    public void show() {
        notifyStage.show();
    }

    private void AnimateOpacity() {
        double requiredOpacity = notifyStage.getOpacity();
        notifyStage.opacityProperty().set(0);
        Timeline tl = new Timeline();
        var kv = new KeyValue(notifyStage.opacityProperty(), requiredOpacity);
        var kf = new KeyFrame(Duration.millis(800), kv);
        tl.getKeyFrames().addAll(kf);
        tl.play();
    }
}
