package controller.HoKhau;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.HoKhau;
import services.HoKhauService;

import java.sql.SQLException;

public class TachKhau {
    //TODO: alert update quanHeVoiChuHo sau khi nhap xong
    @FXML
    private TextField tfMaHoMoi;
    @FXML
    private TextField tfMaChuHoMoi;
    @FXML
    private TextField tfDanhSach;
    @FXML
    private TextField tfMaHoCu;

    // Event Listener on Button.onAction
    @FXML
    public void xacNhanTachKhau(ActionEvent event) throws SQLException {
        if(!check()) return;
        String maHoMoi = tfMaHoMoi.getText();
        String maChuMoi = tfMaChuHoMoi.getText();
        String danhSach = tfDanhSach.getText();
        String maHoCu = tfMaHoCu.getText();
        //tao ho khau moi voi chu ho moi
        HoKhau hoKhau = new HoKhau(maHoMoi, maChuMoi);
        new HoKhauService().tachHoKhau(hoKhau, maHoCu);
        //thay doi maHo cua nhung nguoi muon tach khau
        //tach ma cua nhung nguoi muon tach khau tu string
        String[] toSplit = danhSach.split(" ");
        //update ho khau cua tung nguoi
        for(int i = 0; i < toSplit.length; i++) {
            new HoKhauService().chuyenHoKhau(maHoCu, maHoMoi, toSplit[i]);
        }
        Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy cập nhật quan hệ của các thành viên hộ trên", ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    public boolean check() {
        //them regex
        return true;
    }
}
