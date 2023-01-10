package controller.NhanKhau;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.KhaiTu;
import services.NhanKhauService;

import java.sql.SQLException;

import javafx.event.ActionEvent;

public class KhaiTuController {
    @FXML
    private TextField tfMaNguoiChet;
    @FXML
    private TextField tfMaNguoiKhai;
    @FXML
    private TextField tfLyDoChet;
    @FXML
    private TextField tfNgayKhai;

    // Event Listener on Button.onAction
    @FXML
    public void baoTu(ActionEvent event) throws ClassNotFoundException, SQLException {
        String maNguoiChet = tfMaNguoiChet.getText();
        String maNguoiKhau = tfMaNguoiKhai.getText();
        String LyDoChet = tfLyDoChet.getText();
        String NgayKhai = tfNgayKhai.getText();

        KhaiTu baotu = new KhaiTu(maNguoiKhau, maNguoiChet, NgayKhai, LyDoChet);
        NhanKhauService sv = new NhanKhauService();
        sv.baoTu(baotu);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
