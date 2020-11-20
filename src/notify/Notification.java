package notify;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

enum HorizontalAlignment {

}

enum VerticalAlignment {
    
}

public class Notification {
    private Stage notifyStage;
    private Scene notifyScene;
    private Group notifyComponents;
    Label notifyMessage = new Label("");               // текстовая метка
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

        notifyStage = new Stage();
        notifyStage.initStyle(StageStyle.UNDECORATED);
        notifyStage.setOpacity(opacity);
        notifyScene = new Scene(root, width, height, Color.DIMGRAY);
        notifyStage.setAlwaysOnTop(true);
        notifyStage.setScene(notifyScene);
    }

    public void show() {
        notifyStage.show();
    }
}