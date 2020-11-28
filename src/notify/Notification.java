package notify;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import notify.alignments.HorizontalAlignment;
import notify.alignments.VerticalAlignment;

import java.awt.*;

public class Notification {
    private Scene notifyScene;
    private final Stage notifyStage;

    private final int width;
    private final int height;

    private VBox contentBox = new VBox();
    private BorderPane canvas = new BorderPane();

    private final VerticalAlignment verticalAlignmentAlign;
    private final HorizontalAlignment horizontalAlignmentAlign;

    private Label notifyMessage;
    private Button closeButton;


    public Notification(
            int width, int height, double opacity,
            VerticalAlignment vAl, HorizontalAlignment hAl,
            Label notifyMessage) {

        this.height = height;
        this.width = width;

        verticalAlignmentAlign = vAl;
        horizontalAlignmentAlign = hAl;

        contentBox.getChildren().addAll(notifyMessage);
        contentBox.setPrefSize(width, height * 0.6);

        canvas.setCenter(contentBox);
        BorderPane.setAlignment(contentBox, Pos.TOP_CENTER);

        buildCloseButton();

        // building canvas
        FlowPane menuPane = new FlowPane(closeButton);
        menuPane.setAlignment(Pos.TOP_LEFT);

        canvas.setTop(menuPane);
        canvas.setStyle("-fx-background-color: #363636;");
        canvas.setPrefSize(width, height);

        notifyScene = new Scene(canvas);

        // building scene
        notifyStage = new Stage();
        notifyStage.setWidth(width);
        notifyStage.setMinHeight(height);
        notifyStage.setOpacity(opacity);
        notifyStage.initStyle(StageStyle.UNDECORATED);
        notifyStage.setAlwaysOnTop(true);
        notifyStage.setScene(notifyScene);

        setStageAlignment();
    }

    public void addControls(Node node) {
        contentBox.getChildren().addAll(node);
    }

    protected void setStageAlignment() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        switch (horizontalAlignmentAlign) {
            case LEFT:
                notifyStage.setX(30);
                break;
            case RIGHT:
                notifyStage.setX(screenSize.width - notifyStage.getWidth() - 30);
                break;
            default:
                notifyStage.setX((int)(screenSize.width / 2) - notifyStage.getWidth() / 2);
                break;
        }

        switch (verticalAlignmentAlign) {
            case TOP:
                notifyStage.setY(30);
                break;
            case BOTTOM:
                notifyStage.setY(screenSize.height - notifyStage.getHeight() - 30);
                break;
            default:
                notifyStage.setY((int)(screenSize.height / 2) - notifyStage.getHeight() / 2);
                break;
        }
    }

    protected void buildCloseButton() {
        closeButton = new Button();
        Image btnIcon = new Image(getClass().getResourceAsStream("resources/close-icon.png"));
        ImageView iconImg = new ImageView(btnIcon);

        closeButton.setBackground(Background.EMPTY);
        closeButton.setGraphic(iconImg);

        closeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                notifyStage.close();
            }
        });
    }

    protected void show() {
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
