package controller.NhanKhau;

import controller.ControllerNhanKhau;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.KhaiTu;
import services.NhanKhauService;

import java.sql.SQLException;
import java.util.regex.Pattern;

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
        if(!check()) return;

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

    public boolean check() {
        Pattern pattern;

        //regex ma nguoi die
        pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
        if (!((pattern.matcher(tfMaNguoiChet.getText()).matches()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã nguoi die hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        //regex ma nguoi khai
        pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
        if (!((pattern.matcher(tfMaNguoiKhai.getText()).matches()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã nguoi khai hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }

        return true;

    }
}
