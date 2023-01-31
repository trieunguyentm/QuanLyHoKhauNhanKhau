package controller.NhanKhau;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.KhaiTu;
import services.NhanKhauService;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class KhaiTuController implements Initializable {
    @FXML
    private TextField tfMaNguoiChet;
    @FXML
    private TextField tfMaNguoiKhai;
    @FXML
    private TextField tfLyDoChet;
    @FXML
    private TextField tfNgayKhai;
    @FXML
    private Button khaiBao;

    // Event Listener on Button.onAction
    @FXML
    public void baoTu(ActionEvent event) {
        //check xem data từ các textfield người dùng nhập có hợp lệ không
        if(!check()) return;

        String maNguoiChet = tfMaNguoiChet.getText();
        String maNguoiKhau = tfMaNguoiKhai.getText();
        String LyDoChet = tfLyDoChet.getText();
        String NgayKhai = tfNgayKhai.getText();
        //tạo khai tử object, truyền vào class service để thực hiện thao tác với database
        try {
            KhaiTu baotu = new KhaiTu(maNguoiKhau, maNguoiChet, NgayKhai, LyDoChet);
            NhanKhauService sv = new NhanKhauService();
            sv.baoTu(baotu);
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào thông tin chính xác!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public boolean check() {
        Pattern pattern;

        //regex ma nguoi die
        pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
        if (!((pattern.matcher(tfMaNguoiChet.getText()).matches()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã người mất hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        //regex ma nguoi khai
        pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
        if (!((pattern.matcher(tfMaNguoiKhai.getText()).matches()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã người khai hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }

        return true;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        khaiBao.addEventHandler(MouseEvent.MOUSE_MOVED, event -> khaiBao.setEffect(new SepiaTone()));
        khaiBao.addEventHandler(MouseEvent.MOUSE_EXITED, event -> khaiBao.setEffect(null));
    }
}