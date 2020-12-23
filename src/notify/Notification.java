package notify;

import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import javafx.util.Callback;
import javafx.util.Duration;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import notify.alignments.HorizontalAlignment;
import notify.alignments.VerticalAlignment;
import notify.callback.NotificationButton;
import notify.callback.ReturnValuesTuple;

import java.awt.*;

class NotificationThread extends Thread {
    final Stage stage;

    NotificationThread(Stage notificationStage) {
        stage = notificationStage;
    }

    @Override
    public void run() {
        stage.show();
//        while (!isInterrupted()) {
//
//        }
    }
}

public class Notification {
    private Scene notifyScene;
    private Stage notifyStage = new Stage();

    private int width;
    private int height;
    private double opacity = 1;
    private VerticalAlignment verticalAlignment;
    private HorizontalAlignment horizontalAlignment;

    private VBox contentBox = new VBox();
    private BorderPane canvas = new BorderPane();
    private FlowPane controlsPane = new FlowPane();

    private Button closeButton;
    private Button okButton;
    private Button cancelButton;

    private Label notifyMessage = new Label("");
    private TextField textField = new TextField();
    private ComboBox<Label> labelComboBox = new ComboBox<>();

    private boolean isSliding = false;

    private Player nPlayer;
    private boolean isSoundPlaying = false;

    ReturnValuesTuple returnValuesTuple = new ReturnValuesTuple(
            NotificationButton.CLOSE, "", "");

    public Notification() {
        verticalAlignment = VerticalAlignment.CENTER;
        horizontalAlignment = HorizontalAlignment.RIGHT;

        height = 200;
        width = 300;

        defaultConfig();
    }

    protected void defaultConfig() {
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
        notifyScene.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());

        try {
            nPlayer = new Player(getClass().getResourceAsStream("/default.mp3"));
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }

        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                returnValuesTuple.setTextFieldValue(textField.getText());
            }
        });

        labelComboBox.valueProperty().addListener(new ChangeListener<Label>() {
            @Override
            public void changed(ObservableValue<? extends Label> observableValue, Label label, Label t1) {
                returnValuesTuple.setComboboxValue(t1.getText());
            }
        });
    }

    public ReturnValuesTuple getReturnValues() {
        return returnValuesTuple;
    }

    public void addSlidingEffect() {
        isSliding = true;
    }

    private void addPosTranslation() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int translatePos = verticalAlignment == VerticalAlignment.TOP ? -250 :
                (int)screenSize.getHeight() + 250;

        TranslateTransition translateTransition = new TranslateTransition();
        Node root = notifyScene.getRoot();
        root.setTranslateY(translatePos);
        translateTransition.setDuration(Duration.seconds(1));
        translateTransition.setNode(root);
        translateTransition.setByY(-translatePos);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
    }

    public void addNotificationSound() {
        isSoundPlaying = true;
    }

    public void addInputField() {
        textField = new TextField();
        textField.setStyle("-fx-text-fill: #e9f2ff; -fx-font-size: 18;" +
                "-fx-background-color: #868686; -fx-border-width: 3;" +
                "-fx-border-color: #afcaff");
        textField.setText("input field");
        contentBox.getChildren().addAll(textField);
    }

    public void addComboBox(ObservableList<Label> items, String promptText) {
        labelComboBox = new ComboBox<>(items);
        labelComboBox.setPromptText(promptText);
        labelComboBox.setCellFactory(new Callback<ListView<Label>, ListCell<Label>>() {
            @Override
            public ListCell<Label> call(ListView<Label> labelListView) {
                final ListCell<Label> cell = new ListCell<Label>() {
                    {
                        super.setPrefWidth(50);
                    }

                    @Override
                    public void updateItem(Label item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getText());
                            setTextFill(Paint.valueOf("#ffffff"));
                        }
                    }
                };
                return cell;
            }
        });
        BorderPane bp = new BorderPane();
        bp.setCenter(labelComboBox);
        contentBox.getChildren().add(bp);
    }

    public void setNotifyMessage(String str) {
        notifyMessage.setMinHeight(Region.USE_PREF_SIZE);
        notifyMessage.setAlignment(Pos.TOP_CENTER);
        notifyMessage.setPadding(new Insets(10));
        notifyMessage.setFont(new Font(25));
        notifyMessage.setTextFill(Paint.valueOf("#F0FFFF"));
        notifyMessage.setWrapText(true);
        notifyMessage.setText(str);

        contentBox.getChildren().addAll(notifyMessage);
        contentBox.setPrefSize(width, height * 0.6);
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

    public void setOnNotificationClosed(EventHandler<WindowEvent> event) {
        notifyStage.setOnCloseRequest(event);
    }

    protected void setStageAlignment() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        switch (horizontalAlignment) {
            case LEFT:
                notifyStage.setX(30);
                break;
            case RIGHT:
                notifyStage.setX(screenSize.width - notifyStage.getWidth() - 30);
                break;
            default:
                notifyStage.setX((int) (screenSize.width / 2) - notifyStage.getWidth() / 2);
                break;
        }

        switch (verticalAlignment) {
            case TOP:
                notifyStage.setY(30);
                break;
            case BOTTOM:
                notifyStage.setY(screenSize.height - notifyStage.getMinHeight() - 30);
                break;
            default:
                notifyStage.setY((int) (screenSize.height / 2) - notifyStage.getMinHeight() / 2);
                break;
        }
    }

    protected void buildCloseButton() {
        closeButton = new Button();
        Image btnIcon = new Image(getClass().getResource("/close-icon.png").toExternalForm());
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

    public ReturnValuesTuple show() {
        // building scene
        notifyStage = new Stage();
        notifyStage.setWidth(width);
        notifyStage.setMinHeight(height);
        notifyStage.setOpacity(opacity);
        notifyStage.initStyle(StageStyle.TRANSPARENT);
        notifyStage.setAlwaysOnTop(true);
        notifyStage.setScene(notifyScene);
        notifyScene.setFill(Color.rgb(0,0,0,0));
        setStageAlignment();

        notifyStage.show();

        if (isSliding)
            addPosTranslation();

        if (isSoundPlaying) {
            NotificationSoundThread nst = new NotificationSoundThread(nPlayer);
            nst.start();
        }

        return returnValuesTuple;
    }

    public Label getCBoxSelectedItem() {
        return labelComboBox.getSelectionModel().getSelectedItem();
    }

    public String getTextFieldInput() {
        return textField.getText();
    }

    private void animateOpacity() {
        double requiredOpacity = notifyStage.getOpacity();
        notifyStage.opacityProperty().set(0);
        Timeline tl = new Timeline();
        var kv = new KeyValue(notifyStage.opacityProperty(), requiredOpacity);
        var kf = new KeyFrame(Duration.millis(1200), kv);
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

    public ReturnValuesTuple getReturnValuesTuple() {
        return returnValuesTuple;
    }
}

class NotificationSoundThread extends Thread {
    Player player;
    NotificationSoundThread(Player nPlayer){
        player = nPlayer;
    }

    @Override
    public void run () {
        try {
            player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}
