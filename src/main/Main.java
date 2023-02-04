package main;

import controller.ControllerLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //get connection
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        //lập query chỉnh sửa hộ
        String query = "update nhan_khau set quanHeVoiChuHo=N'Chủ hộ' where maNhanKhau='0014'";
        Statement stm = connection.createStatement();
        stm.executeUpdate(query);
        connection.close();
        //Tạo root
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Login.fxml")));
        //Tạo scene
        Scene scene = new Scene(root, 440, 430);
        //Stage
        stage.setScene(scene);
        stage.setTitle("Quản lý hộ khẩu và nhân khẩu");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
