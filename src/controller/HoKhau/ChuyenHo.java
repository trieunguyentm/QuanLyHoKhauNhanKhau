package controller.HoKhau;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.HoKhauService;

import java.sql.SQLException;
import java.util.regex.Pattern;


public class ChuyenHo {
    @FXML
    private TextField tfMaNhanKhau;
    @FXML
    private TextField tfMaHoCu;
    @FXML
    private TextField tfMaHoMoi;

    public boolean chuyenHo(ActionEvent event) throws SQLException {
        if(!check()) return false;
        String maNhanKhau = tfMaNhanKhau.getText();
        String maHoCu = tfMaHoCu.getText();
        String maHoMoi = tfMaHoMoi.getText();

        new HoKhauService().chuyenHoKhau(maHoCu, maHoMoi, maNhanKhau);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        return true;
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
        if (!pattern.matcher(tfMaNhanKhau.getText()).matches()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã nhan khau lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        return  true;
    }
}
