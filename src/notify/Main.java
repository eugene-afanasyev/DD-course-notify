package notify;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import notify.alignments.HorizontalAlignment;
import notify.alignments.VerticalAlignment;
import notify.builders.NotificationBuilder;
import notify.callback.ReturnValuesTuple;

public class Main extends Application {

    String textField;
    String combobox;
    ReturnValuesTuple rtv;


    @Override
    public void start(Stage stage) throws Exception{
        ObservableList<String> strings = FXCollections.observableArrayList(
                "first", "second", "third"
        );

        NotificationBuilder nb = new NotificationBuilder();
        nb.setHorizontalAlignment(HorizontalAlignment.CENTER);
        nb.setVerticalAlignment(VerticalAlignment.BOTTOM);
        nb.setOpacity(0.94);
        nb.setHeight(280);
        nb.setWidth(400);
        nb.setMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        nb.addControlButtons();
        nb.setLabelComboBox(strings, "choose element");
        nb.addInputField();
        nb.addSlidingEffect();
        nb.addNotificationSound();

        Notification n = nb.build();
        n.show();
        n.setOnNotificationClosed(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                rtv = n.getReturnValues();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
