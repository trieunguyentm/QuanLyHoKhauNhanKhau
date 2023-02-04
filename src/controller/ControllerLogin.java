package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import main.DataBaseConnection;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {
    public static final String userDataBase = "sa";
    public static final String passworDataBase = "123456";
    public static User taikhoan;
    @FXML
    private TextField userLogin;
    @FXML
    private PasswordField passLogin;
    @FXML
    private Button buttonLogin;
    @FXML
    private Button buttonLoginGuess;
    @FXML
    private Label messageLabel;

    //Xử lý sự kiện khi ấn Đăng nhập
    public void clickLoginButton() throws RuntimeException {
        String userName = userLogin.getText();
        String password = passLogin.getText();
        // Xử lý trường hợp user chưa nhập đủ thông tin tài khoản mật khẩu
        if(Objects.equals(userName, "") || Objects.equals(password, ""))
        {
            messageLabel.setTextFill(Paint.valueOf("RED"));
            messageLabel.setText("Vui lòng nhập đầy đủ tài khoản và mật khẩu");
        }
        // Xử lý việc kiểm tra tài khoản mật khẩu có chính xác hay không
        else {
            messageLabel.setTextFill(Paint.valueOf("YELLOW"));
            messageLabel.setText("Đang đăng nhập...");
            //Kết nối với database
            DataBaseConnection dataBaseConnection = new DataBaseConnection();
            Connection connection = dataBaseConnection.getConnection(userDataBase,passworDataBase);
            String verifyLogin = "SELECT count(1) FROM users WHERE userName = '" + userName + "' AND pass = '" + password +"'";
            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(verifyLogin);
                boolean status = false;//Đánh dấu trạng thái đăng nhập
                while (resultSet.next())
                {
                    if(resultSet.getInt(1) == 1)//Đăng nhập đúng
                    {
                        status = true;
                        messageLabel.setTextFill(Paint.valueOf("GREEN"));
                        messageLabel.setText("Đăng nhập thành công");
                        //Nếu đăng nhập đúng, cần lấy thông tin tài khoản
                        String getDataAccount = "SELECT * FROM users WHERE userName = '" + userName + "' AND pass = '" + password +"'";
                        resultSet = statement.executeQuery(getDataAccount);
                        resultSet.next();
                        ControllerLogin.taikhoan = new User(resultSet.getInt(1), userName, resultSet.getString("vaiTro"), resultSet.getString("tenNguoiDung"), resultSet.getString("ngaySinh"), resultSet.getString("gioiTinh"));
                    }
                    else {//Đăng nhập sai
                        messageLabel.setTextFill(Paint.valueOf("RED"));
                        messageLabel.setText("Thông tin tài khoản hoặc mật khẩu không chính xác");
                    }
                }

                if(status)//Nếu đăng nhập thành công
                {
                    //Đóng kết nối database
                    statement.close();
                    connection.close();
                    //Chuyển màn hình sang màn hình home
                    Stage stage = (Stage) buttonLogin.getScene().getWindow();
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Home.fxml")));
                    Scene scene = new Scene(root, 1000, 600);
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    //Xử lý sự kiện khi ấn Đăng nhập
    public void clickLoginGuessButton() throws RuntimeException {
        String userName = "guess";
        String password = "guess";
        messageLabel.setTextFill(Paint.valueOf("GREEN"));
        messageLabel.setText("Đăng nhập thành công");
        String getDataAccount = "SELECT * FROM users WHERE userName = '" + userName + "' AND pass = '" + password +"'";
        try{
            //Kết nối với database
            DataBaseConnection dataBaseConnection = new DataBaseConnection();
            Connection connection = dataBaseConnection.getConnection(userDataBase,passworDataBase);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getDataAccount);
            resultSet.next();
            ControllerLogin.taikhoan = new User(resultSet.getInt(1), userName, resultSet.getString("vaiTro"), resultSet.getString("tenNguoiDung"), resultSet.getString("ngaySinh"), resultSet.getString("gioiTinh"));

            //Đóng kết nối database
            statement.close();
            connection.close();

            //Chuyển màn hình sang màn hình home
            Stage stage = (Stage) buttonLogin.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Home.fxml")));
            Scene scene = new Scene(root, 1000, 600);
            stage.setScene(scene);
            stage.show();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Xử lý đăng nhập khi người dùng ấn Enter
    public void pressEnter()
    {
        buttonLogin.getScene().setOnKeyPressed(keyEvent -> clickLoginButton());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Hiệu ứng khi di chuyển chuột vào các component
        buttonLogin.addEventHandler(MouseEvent.MOUSE_MOVED,event -> buttonLogin.setEffect(new SepiaTone()));
        buttonLogin.addEventHandler(MouseEvent.MOUSE_EXITED,event -> buttonLogin.setEffect(null));
        buttonLoginGuess.addEventHandler(MouseEvent.MOUSE_MOVED,event -> buttonLoginGuess.setEffect(new SepiaTone()));
        buttonLoginGuess.addEventHandler(MouseEvent.MOUSE_EXITED,event -> buttonLoginGuess.setEffect(null));
        userLogin.addEventHandler(MouseEvent.MOUSE_MOVED,event -> userLogin.setEffect(new SepiaTone()));
        userLogin.addEventHandler(MouseEvent.MOUSE_EXITED,event -> userLogin.setEffect(null));
        passLogin.addEventHandler(MouseEvent.MOUSE_MOVED,event -> passLogin.setEffect(new SepiaTone()));
        passLogin.addEventHandler(MouseEvent.MOUSE_EXITED,event -> passLogin.setEffect(null));
        //Tooltip
        userLogin.setText("admin");
        passLogin.setText("admin");
        userLogin.setTooltip(new Tooltip("Nhập thông tin tài khoản"));
        passLogin.setTooltip(new Tooltip("Nhập thông tin mật khẩu"));
    }
}
