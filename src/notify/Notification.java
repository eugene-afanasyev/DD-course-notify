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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
    private Button closeButton;

    public Notification(int width, int height, double opacity) {
        buildCloseButton();

        FlowPane root = new FlowPane(notifyMessage, closeButton);
        root.setBackground(Background.EMPTY);
        root.setAlignment(Pos.TOP_RIGHT);

        canvas = new Pane(root);
        canvas.setStyle("-fx-background-color: #363636;");
        canvas.setPrefSize(width,height);

        notifyStage = new Stage();
        notifyStage.setOpacity(opacity);
        notifyStage.initStyle(StageStyle.UNDECORATED);
        Scene notifyScene = new Scene(canvas);
        notifyStage.setAlwaysOnTop(true);
        notifyStage.setScene(notifyScene);

    }

    public void buildCloseButton() {
        closeButton = new Button();
        Image btnIcon = new Image(getClass().getResourceAsStream("resources/close-icon.png"));
        btnIcon.widthProperty().add(1   );

        closeButton.setBackground(Background.EMPTY);
        closeButton.setGraphic(new ImageView(btnIcon));

        closeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                notifyStage.close();
            }
        });
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
