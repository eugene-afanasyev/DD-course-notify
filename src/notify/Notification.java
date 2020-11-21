package notify;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import notify.alignments.HorizontalAlignment;
import notify.alignments.VerticalAlignment;

public class Notification {
    private final Stage notifyStage;
    private final VerticalAlignment verticalAlignmentAlign;
    private final HorizontalAlignment horizontalAlignmentAlign;

    Label notifyMessage = new Label("");
    private Button closeButton;

    public Notification(int width, int height, double opacity, VerticalAlignment vAl, HorizontalAlignment hAl) {
        verticalAlignmentAlign = vAl;
        horizontalAlignmentAlign = hAl;

        buildCloseButton();

        FlowPane root = new FlowPane(notifyMessage, closeButton);
        root.setBackground(Background.EMPTY);
        root.setAlignment(Pos.TOP_RIGHT);

        Pane canvas = new Pane(root);
        canvas.setStyle("-fx-background-color: #363636;");
        canvas.setPrefSize(width,height);

        notifyStage = new Stage();
        notifyStage.setOpacity(opacity);
        notifyStage.initStyle(StageStyle.UNDECORATED);
        Scene notifyScene = new Scene(canvas);
        notifyStage.setAlwaysOnTop(true);
        notifyStage.setScene(notifyScene);

        setStageAlignment();
    }

    public void setStageAlignment() {

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

    public HorizontalAlignment getHorizontalAlignmentAlign() {
        return horizontalAlignmentAlign;
    }

    public VerticalAlignment getVerticalAlignmentAlign() {
        return verticalAlignmentAlign;
    }
}
