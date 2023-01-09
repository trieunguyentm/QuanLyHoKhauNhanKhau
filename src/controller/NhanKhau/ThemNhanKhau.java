package controller.NhanKhau;

import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.NhanKhau;
import services.NhanKhauService;

import java.sql.SQLException;

public class ThemNhanKhau {
    @FXML
    private TextField tfMaNhanKhau;
    @FXML
    private TextField tfHoTen;
    @FXML
    private TextField tfGioiTinh;
    @FXML
    private TextField tfNgaySinh;
    @FXML
    private TextField tfQueQuan;
    @FXML
    private TextField tfNgheNghiep;
    @FXML
    private TextField tfMaHo;
    @FXML
    private TextField tfQuanHeVoiChuHo;

    // Event Listener on Button.onAction
    @FXML
    public void add(ActionEvent event) throws SQLException, ClassNotFoundException {
       String ma = tfMaNhanKhau.getText();
       String hoTen = tfHoTen.getText();
       String gioiTinh = tfGioiTinh.getText();
       String ngaySinh = tfNgaySinh.getText();
       String queQuan = tfQueQuan.getText();
       String ngheNgiep = tfNgheNghiep.getText();
       String maHo = tfMaHo.getText();
       String quanHe = tfQuanHeVoiChuHo.getText();

        NhanKhau nhanKhau = new NhanKhau(ma, hoTen, gioiTinh, ngaySinh, queQuan, ngheNgiep,maHo, quanHe);
        new NhanKhauService().add(nhanKhau);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
