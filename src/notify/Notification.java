package notify;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    private Stage notifyStage;

    private int width;
    private int height;

    private VBox contentBox = new VBox();
    private BorderPane canvas = new BorderPane();

    private VerticalAlignment verticalAlignment;
    private HorizontalAlignment horizontalAlignment;
    private FlowPane controlsPane = new FlowPane();

    private Label notifyMessage = new Label("");
    private Button closeButton;

    private Button okButton;
    private Button cancelButton;

    private double opacity = 1;

    public Notification() {
        verticalAlignment = VerticalAlignment.TOP;
        horizontalAlignment = HorizontalAlignment.RIGHT;

        height = 200;
        width = 300;

        defaultConfig();
    }

    public void setNotifyMessage(String str) {
        notifyMessage.setMinHeight(Region.USE_PREF_SIZE);
        notifyMessage.setAlignment(Pos.TOP_CENTER);
        notifyMessage.setPadding(new Insets(10));
        notifyMessage.setFont(new Font(25));
        notifyMessage.setTextFill(Paint.valueOf("#F0FFFF"));
        notifyMessage.setWrapText(true);
        notifyMessage.setText(str);
    }

    protected void defaultConfig() {
        contentBox.getChildren().addAll(notifyMessage);
        contentBox.setPrefSize(width, height * 0.6);

        canvas.setCenter(contentBox);
        BorderPane.setAlignment(contentBox, Pos.TOP_CENTER);

        buildCloseButton();

        FlowPane menuPane = new FlowPane(closeButton);
        menuPane.setAlignment(Pos.TOP_LEFT);

        canvas.setTop(menuPane);
        canvas.setStyle("-fx-background-color: #363636;");
        canvas.setPrefSize(width, height);

        canvas.setBottom(controlsPane);
        controlsPane.setAlignment(Pos.TOP_CENTER);
        controlsPane.setHgap(5);

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

    public void addControlButtons() {
        okButton = new Button("Ok");
        okButton.setFont(new Font("Bold", 18));
        okButton.setBackground(Background.EMPTY);
        okButton.setTextFill(Paint.valueOf("#bbccdd"));
        okButton.setStyle("-fx-border-width: 1; -fx-border-color: #bbccdd; -fx-border-radius: 1");
        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                notifyStage.close();
            }
        });

        cancelButton = new Button("Cancel");
        cancelButton.setFont(new Font(18));
        cancelButton.setBackground(Background.EMPTY);
        cancelButton.setTextFill(Paint.valueOf("#bbccdd"));
        cancelButton.setStyle("-fx-border-width: 1; -fx-border-color: #bbccdd; -fx-border-radius: 1");
        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                notifyStage.close();
            }
        });

        controlsPane.getChildren().addAll(okButton, cancelButton);
    }

    protected void setStageAlignment() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        switch (horizontalAlignment) {
            case LEFT -> notifyStage.setX(30);
            case RIGHT -> notifyStage.setX(screenSize.width - notifyStage.getWidth() - 30);
            default -> notifyStage.setX((int) (screenSize.width / 2) - notifyStage.getWidth() / 2);
        }

        switch (verticalAlignment) {
            case TOP -> notifyStage.setY(30);
            case BOTTOM -> notifyStage.setY(screenSize.height - notifyStage.getHeight() - 30);
            default -> notifyStage.setY((int) (screenSize.height / 2) - notifyStage.getHeight() / 2);
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

    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    public double getOpacity() {
        return opacity;
    }

    public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public HorizontalAlignment getHorizontalAlign() {
        return horizontalAlignment;
    }

    public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public VerticalAlignment getVerticalAlign() {
        return verticalAlignment;
    }
}
