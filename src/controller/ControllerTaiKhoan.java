package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.DataBaseConnection;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerTaiKhoan implements Initializable {
    @FXML
    private TextField textTenTaiKhoan;
    @FXML
    private TextField textVaiTro;
    @FXML
    private TextField textTenNguoiDung;
    @FXML
    private DatePicker textNgaySinh;
    @FXML
    private ComboBox<String> textGioiTinh;
    //Lấy dữ liệu tài khoản được chuyển từ Scene Home
    @FXML
    private Button thayDoiThongTin;
    @FXML
    private Button dangXuat;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Hiển thị tên tài khoản

        textTenTaiKhoan.setText(ControllerLogin.taikhoan.getTenTaiKhoan());
        //Hiển thị tên vai trò

        textVaiTro.setText(ControllerLogin.taikhoan.getVaiTro());
        //Hiển thị tên người dùng

        textTenNguoiDung.setText(ControllerLogin.taikhoan.getTenNguoiDung());
        //Hiển thị ngày tháng năm sinh

        String date = ControllerLogin.taikhoan.getNgaySinh();//YYYY-MM-dd
        LocalDate localDate = LocalDate.parse(date);
        textNgaySinh.setValue(localDate);
        //Hiển thị giới tính

        textGioiTinh.setItems(FXCollections.observableArrayList("Nam", "Nữ"));
        textGioiTinh.setValue(ControllerLogin.taikhoan.getGioiTinh());
        //Hiệu ứng khi di chuyển chuột qua các button

        dangXuat.addEventHandler(MouseEvent.MOUSE_MOVED, event -> dangXuat.setEffect(new SepiaTone()));
        dangXuat.addEventHandler(MouseEvent.MOUSE_EXITED,event -> dangXuat.setEffect(null));
        thayDoiThongTin.addEventHandler(MouseEvent.MOUSE_MOVED,event -> thayDoiThongTin.setEffect(new SepiaTone()));
        thayDoiThongTin.addEventHandler(MouseEvent.MOUSE_EXITED,event -> thayDoiThongTin.setEffect(null));

    }
    public void clickDangXuat() throws IOException {
        //Hiển thị thông báo

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setContentText("Bạn có muốn đăng xuất ?");
        alert.setHeaderText(null);
        Optional<ButtonType> optional = alert.showAndWait();
        //Kiểm tra lựa chọn

        if(optional.get() == ButtonType.OK)
        {
            //Chuyển scene về màn hình Login
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Login.fxml")));
            Scene scene = new Scene(root, 440, 430);
            Stage stage = (Stage) dangXuat.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
    public void clickThayDoiThongTin() throws SQLException {
        //Hiển thị thông báo

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setContentText("Bạn có muốn thay đổi thông tin");
        alert.setHeaderText(null);
        Optional<ButtonType> optional = alert.showAndWait();
        //Kiểm tra lựa chọn

        if(optional.get() == ButtonType.OK)
        {
            //Đồng ý thay đổi, lấy thông tin
            ControllerLogin.taikhoan = new User(ControllerLogin.taikhoan.getId(), ControllerLogin.taikhoan.getTenTaiKhoan(), ControllerLogin.taikhoan.getVaiTro(), textTenNguoiDung.getText(), textNgaySinh.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), textGioiTinh.getValue());
            //Cập nhật lại CSDL
            DataBaseConnection dataBaseConnection = new DataBaseConnection();
            Connection connection = dataBaseConnection.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
            String updateAccount = "UPDATE users SET tenNguoiDung = N'"+ textTenNguoiDung.getText() + "', ngaySinh = N'"+ textNgaySinh.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +"', gioiTinh = N'"+ textGioiTinh.getValue() +"' WHERE userName = 'tester'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateAccount);
            //Update thành công
            Alert updateAlert = new Alert(Alert.AlertType.INFORMATION);
            updateAlert.setTitle("Thông Báo");
            updateAlert.setContentText("Thay đổi thông tin thành công");
            updateAlert.setHeaderText(null);
        }
    }
}
