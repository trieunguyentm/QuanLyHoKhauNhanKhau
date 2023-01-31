package controller.HoKhau;

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
import model.NhanKhau;
import services.HoKhauService;
import services.NhanKhauService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class ChuyenHo implements Initializable {
    @FXML
    private TextField tfMaNhanKhau;
    @FXML
    private TextField tfMaHoCu;
    @FXML
    private TextField tfMaHoMoi;
    @FXML
    private Button xacNhanThayDoi;

    public boolean chuyenHo(ActionEvent event) throws SQLException {
        //method check() phía dưới phục vụ việc kiểm tra data người dùng nhập vào, nếu sai thì không làm gì cả
        if(!check()) return false;
        //lấy dữ liệu từ các textfield sau khi check chúng đã hợp lệ
        String maNhanKhau = tfMaNhanKhau.getText();
        String maHoCu = tfMaHoCu.getText();
        String maHoMoi = tfMaHoMoi.getText();
        //thao tác đến database sau khi lấy được dữ liệu
        try {
            new HoKhauService().chuyenHoKhau(maHoCu, maHoMoi, maNhanKhau);
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào thông tin chính xác!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        return true;
    }

    public boolean check() {
        //them regex
        Pattern pattern;
        // kiem tra ma nguoi nhap vao
        // ma nguoi 4 so, có dạng xxxx nên regex tương  ứng [0-9][0-9][0-9][0-9]
        pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
        if (!((pattern.matcher(tfMaHoCu.getText()).matches()) || (pattern.matcher(tfMaHoMoi.getText()).matches()) )) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã hộ hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
        if (!pattern.matcher(tfMaNhanKhau.getText()).matches()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã nhân khẩu hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        return  true;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        xacNhanThayDoi.addEventHandler(MouseEvent.MOUSE_MOVED, event -> xacNhanThayDoi.setEffect(new SepiaTone()));
        xacNhanThayDoi.addEventHandler(MouseEvent.MOUSE_EXITED, event -> xacNhanThayDoi.setEffect(null));
    }
}
