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
import services.HoKhauService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class DoiChuHo implements Initializable {
        @FXML
        private TextField tfMaHo;
        @FXML
        private TextField tfMaChuCu;
        @FXML
        private TextField tfMaChuMoi;
        @FXML
        private Button xacNhanThayDoi;

        // Event Listener on Button.onAction
        @FXML
        public void doiChuHo(ActionEvent event) throws SQLException {
                //method check() phía dưới phục vụ việc kiểm tra data người dùng nhập vào, nếu sai thì không làm gì cả
                if(!check()) return;
                //sửa chủ hộ
                try {
                        new HoKhauService().suaChuHo(tfMaHo.getText(), tfMaChuCu.getText(), tfMaChuMoi.getText());
                        //alert cap nhat quan he
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy cập nhật quan hệ của các thành viên hộ trên", ButtonType.OK);
                        alert.setHeaderText(null);
                        alert.showAndWait();
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
                //them regex
                Pattern pattern;
                // kiem tra ma nguoi nhap vao
                //pattern: tìm treen internet
                pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
                if (!((pattern.matcher(tfMaChuMoi.getText()).matches()) || (pattern.matcher(tfMaChuCu.getText()).matches()) )) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã nhân khẩu hợp lệ!", ButtonType.OK);
                        alert.setHeaderText(null);
                        alert.showAndWait();
                        return false;
                }
                //regex check mã hộ
                pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
                if (!pattern.matcher(tfMaHo.getText()).matches()) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã hộ hợp lệ!", ButtonType.OK);
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
