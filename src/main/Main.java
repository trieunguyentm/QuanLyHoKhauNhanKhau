package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //Tạo root
        //thien da tung o day
//        DataBaseConnection conn = new DataBaseConnection();
//        //none sene
//        conn.getConnection("abc", "def");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Login.fxml")));
        //Tạo scene
        Scene scene = new Scene(root, 440, 430);
        //Stage
        stage.setScene(scene);
        stage.setTitle("DEMO");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
