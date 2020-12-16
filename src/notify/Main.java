package notify;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import notify.alignments.HorizontalAlignment;
import notify.alignments.VerticalAlignment;
import notify.builders.NotificationBuilder;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        ObservableList<Label> labels = FXCollections.observableArrayList(
                new Label("first"), new Label("second"), new Label("third")
        );

        NotificationBuilder nb = new NotificationBuilder();
        nb.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        nb.setVerticalAlignment(VerticalAlignment.BOTTOM);
        nb.setOpacity(0.94);
        nb.setHeight(280);
        nb.setWidth(400);
        nb.setMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        nb.addControlButtons();
        nb.setLabelComboBox(labels, "choose element");
        nb.addInputField();
        nb.addSlidingEffect();

        Notification n = nb.build();
        n.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
