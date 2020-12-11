package notify;

import javafx.application.Application;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import notify.alignments.HorizontalAlignment;
import notify.alignments.VerticalAlignment;
import notify.builders.NotificationBuilder;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        NotificationBuilder nb = new NotificationBuilder();
        nb.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        nb.setVerticalAlignment(VerticalAlignment.TOP);
        nb.setOpacity(0.94);
        nb.setHeight(200);
        nb.setWidth(300);
        nb.setMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        nb.addControlButtons();

        Notification n = nb.build();
        n.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
