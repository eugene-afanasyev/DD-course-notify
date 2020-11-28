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
        nb.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        nb.setVerticalAlignment(VerticalAlignment.TOP);
        nb.setOpacity(0.94);
        nb.setHeight(200);
        nb.setWidth(300);
        nb.setNotificationMessage("dsas d as d as da s d as d a s d as  d a sd  as d as d  as d a s da sd  as dasdasdasd a sd asdeww frwrev ybb");

        Notification n = nb.build();
        n.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
