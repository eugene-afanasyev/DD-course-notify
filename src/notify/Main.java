package notify;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import notify.builders.NotificationBuilder;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        NotificationBuilder nb = new NotificationBuilder();
        Notification n = nb.build();
        n.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
