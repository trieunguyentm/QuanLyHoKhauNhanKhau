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
import java.util.regex.Pattern;

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
        Pattern pattern;
        // kiem tra ma nguoi nhap vao
        // ma nguoi 4 so
        pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
        if (!((pattern.matcher(tfMaHoCu.getText()).matches()) || (pattern.matcher(tfMaHoMoi.getText()).matches()) )) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã ho hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
        if (!pattern.matcher(tfMaChuHoMoi.getText()).matches()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã nhan khau hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }

        pattern = Pattern.compile("(([0-9][0-9][0-9][0-9]\\s){0,100})");
        if (!pattern.matcher(tfDanhSach.getText() + " ").matches()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào danh sach hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }


        return  true;
    }
}
