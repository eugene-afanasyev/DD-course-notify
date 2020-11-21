package notify;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import notify.alignments.HorizontalAlignment;
import notify.alignments.VerticalAlignment;
import notify.builders.NotificationBuilder;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        NotificationBuilder nb = new NotificationBuilder();
        nb.setHorizontalAlignment(HorizontalAlignment.CENTER);
        nb.setVerticalAlignment(VerticalAlignment.CENTER);
        nb.setOpacity(0.94);
        nb.setHeight(600);
        nb.setWidth(500);

        Notification n = nb.build();
        n.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
