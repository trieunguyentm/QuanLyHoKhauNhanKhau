package test;

import controller.ControllerLogin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.DataBaseConnection;
import org.junit.Test;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Objects;


public class TestLogin extends ApplicationTest{
    private FXMLLoader loader;
    @Override
    public void start(Stage stage) throws Exception {
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        //lập query chỉnh sửa hộ
        String query = "update nhan_khau set quanHeVoiChuHo=N'Chủ hộ' where maNhanKhau='0014'";
        Statement stm = connection.createStatement();
        stm.executeUpdate(query);
        connection.close();
        //Tạo root
        loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(
                "/view" +
                "/Login.fxml")));
        Parent root = loader.load();
        //Tạo scene
        Scene scene = new Scene(root, 440, 430);
        //Stage
        stage.setScene(scene);
        stage.setTitle("Quản lý hộ khẩu và nhân khẩu");
        stage.show();
    }
    @Test
    public void check_login_button(){
        // Kiem tra chuoi ki tu cua nut dang nhap
        Button loginButton = loader.<ControllerLogin>getController().getButtonLogin();
        Button guestLoginButton = loader.<ControllerLogin>getController().getButtonLoginGuess();
        Assertions.assertThat(loginButton.getText()).contains("Đăng nhập");
        Assertions.assertThat(guestLoginButton.getText()).contains("Đăng nhập vào tài khoản khách");

    }
    @Test
    public void check_successful_msg(){
        // Nhap dung thong tin
        Button loginButton = loader.<ControllerLogin>getController().getButtonLogin();
        Label errorMessage = loader.<ControllerLogin>getController().getMessageLabel();
        clickOn(loginButton);
        Assertions.assertThat(errorMessage.getText()).contains("Đăng nhập thành công");
    }
    @Test
    public void check_error_msg(){
        // Nhap sai ten dang nhap
        Button loginButton = loader.<ControllerLogin>getController().getButtonLogin();
        Label errorMessage = loader.<ControllerLogin>getController().getMessageLabel();
        TextField userLogin = loader.<ControllerLogin>getController().getUserLogin();
        userLogin.setText("admin1");
        clickOn(loginButton);
        Assertions.assertThat(errorMessage.getText()).contains("Thông tin tài khoản hoặc mật khẩu không chính xác");
    }
}
