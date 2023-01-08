package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerTaiKhoan implements Initializable {
    @FXML
    Label labelTenTaiKhoan;
    @FXML
    Label labelVaiTro;
    @FXML
    Label labelTenNguoiDung;
    @FXML
    Label labelNgaySinh;
    @FXML
    Label labelGioiTinh;
    static User taikhoan;
    //Lấy dữ liệu tài khoản được chuyển từ Scene Home
    @FXML
    Button thayDoiThongTin;
    @FXML
    Button dangXuat;
    public void getData(User taikhoan)
    {
        ControllerTaiKhoan.taikhoan = taikhoan;
        System.out.println(taikhoan.getTenTaiKhoan());
        System.out.println(taikhoan.getVaiTro());
        System.out.println(taikhoan.getTenNguoiDung());
        System.out.println(taikhoan.getNgaySinh());
        System.out.println(taikhoan.getGioiTinh());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelTenTaiKhoan.setText(ControllerTaiKhoan.taikhoan.getTenTaiKhoan());
        labelVaiTro.setText(ControllerTaiKhoan.taikhoan.getVaiTro());
        labelTenNguoiDung.setText(ControllerTaiKhoan.taikhoan.getTenNguoiDung());
        labelNgaySinh.setText(ControllerTaiKhoan.taikhoan.getNgaySinh());
        labelGioiTinh.setText(ControllerTaiKhoan.taikhoan.getGioiTinh());
        dangXuat.addEventHandler(MouseEvent.MOUSE_MOVED, event -> dangXuat.setEffect(new SepiaTone()));
        dangXuat.addEventHandler(MouseEvent.MOUSE_EXITED,event -> dangXuat.setEffect(null));
        thayDoiThongTin.addEventHandler(MouseEvent.MOUSE_MOVED,event -> thayDoiThongTin.setEffect(new SepiaTone()));
        thayDoiThongTin.addEventHandler(MouseEvent.MOUSE_EXITED,event -> thayDoiThongTin.setEffect(null));
    }
    public void clickDangXuat() throws IOException {
        //Hiển thị thông báo
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setContentText(null);
        alert.setHeaderText("Bạn có muốn đăng xuất ?");
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
    public void clickThayDoiThongTin()
    {

    }
}
