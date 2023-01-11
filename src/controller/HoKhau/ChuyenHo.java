package controller.HoKhau;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.HoKhauService;

import java.sql.SQLException;


public class ChuyenHo {
    @FXML
    private TextField tfMaNhanKhau;
    @FXML
    private TextField tfMaHoCu;
    @FXML
    private TextField tfMaHoMoi;

    public boolean chuyenHo(ActionEvent event) throws SQLException {
        String maNhanKhau = tfMaNhanKhau.getText();
        String maHoCu = tfMaHoCu.getText();
        String maHoMoi = tfMaHoMoi.getText();

        new HoKhauService().chuyenHoKhau(maHoCu, maHoMoi, maNhanKhau);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        return true;
    }
}
