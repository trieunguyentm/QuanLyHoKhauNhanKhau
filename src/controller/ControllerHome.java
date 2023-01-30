package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerHome implements Initializable {
    @FXML
    private Button thongKe;
    @FXML
    private Button hoKhau;
    @FXML
    private Button nhanKhau;
    @FXML
    private Button khoanThu;
    @FXML
    private Button taiKhoan;
    @FXML
    private Button tamTrutamVang;
    @FXML
    private BorderPane mainPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Khi vừa vào Scene Home thì sẽ mặc định setCenter  = Scene Thống kê
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ThongKe.fxml"));
        Pane paneThongKe;
        try {
            paneThongKe = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainPane.setCenter(paneThongKe);

        //Hiệu ứng chuột khi di chuyển chuột qua các button
        thongKe.addEventHandler(MouseEvent.MOUSE_MOVED, event -> thongKe.setEffect(new SepiaTone()));
        thongKe.addEventHandler(MouseEvent.MOUSE_EXITED, event -> thongKe.setEffect(null));
        hoKhau.addEventHandler(MouseEvent.MOUSE_MOVED, event -> hoKhau.setEffect(new SepiaTone()));
        hoKhau.addEventHandler(MouseEvent.MOUSE_EXITED, event -> hoKhau.setEffect(null));
        nhanKhau.addEventHandler(MouseEvent.MOUSE_MOVED, event -> nhanKhau.setEffect(new SepiaTone()));
        nhanKhau.addEventHandler(MouseEvent.MOUSE_EXITED, event -> nhanKhau.setEffect(null));
        khoanThu.addEventHandler(MouseEvent.MOUSE_MOVED, event -> khoanThu.setEffect(new SepiaTone()));
        khoanThu.addEventHandler(MouseEvent.MOUSE_EXITED, event -> khoanThu.setEffect(null));
        taiKhoan.addEventHandler(MouseEvent.MOUSE_MOVED, event -> taiKhoan.setEffect(new SepiaTone()));
        taiKhoan.addEventHandler(MouseEvent.MOUSE_EXITED, event -> taiKhoan.setEffect(null));
        tamTrutamVang.addEventHandler(MouseEvent.MOUSE_MOVED, event -> tamTrutamVang.setEffect(new SepiaTone()));
        tamTrutamVang.addEventHandler(MouseEvent.MOUSE_EXITED, event -> tamTrutamVang.setEffect(null));
    }
    @FXML
        //Set center khi click vào Thống Kê
    void clickThongKe() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ThongKe.fxml"));
        Pane thongKe;
        thongKe = loader.load();
        mainPane.setCenter(thongKe);
    }
    @FXML
        //Set center khi click vào Hộ Khẩu
    void clickHoKhau() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HoKhau.fxml"));
        Pane paneHoKhau;
        paneHoKhau = loader.load();
        mainPane.setCenter(paneHoKhau);
    }
    @FXML
        //Set center khi click vào Nhân Khẩu
    void clickNhanKhau() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NhanKhau.fxml"));
        Pane paneNhanKhau;
        paneNhanKhau = loader.load();
        mainPane.setCenter(paneNhanKhau);
    }
    @FXML
        //Set center khi click vào Khoản Thu
    void clickKhoanThu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/KhoanThu.fxml"));
        Pane paneKhoanThu;
        paneKhoanThu = loader.load();
        mainPane.setCenter(paneKhoanThu);
    }
    @FXML
        //Set center khi click vào Tài Khoản
    void clickTaiKhoan() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TaiKhoan.fxml"));
        Pane paneTaiKhoan;
        paneTaiKhoan = loader.load();
        mainPane.setCenter(paneTaiKhoan);
    }
    @FXML
    void clickTamTruTamVang() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TamTruTamVang.fxml"));
        Pane paneTTTV;
        paneTTTV = loader.load();
        mainPane.setCenter(paneTTTV);
    }
}