package controller.HoKhau;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import services.HoKhauService;
import services.NhanKhauService;

import java.sql.SQLException;

public class DoiChuHo {
        @FXML
        private TextField tfMaHo;
        @FXML
        private TextField tfMaChuCu;
        @FXML
        private TextField tfMaChuMoi;

        // Event Listener on Button.onAction
        @FXML
        public void doiChuHo(ActionEvent event) throws SQLException {
                if(!check()) return;
                new HoKhauService().suaChuHo(tfMaHo.getText(), tfMaChuCu.getText(), tfMaChuMoi.getText());

                //alert cap nhat quan he
                Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy cập nhật quan hệ của các thành viên hộ trên", ButtonType.OK);
                alert.setHeaderText(null);
                alert.showAndWait();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
        }

        public boolean check() {
                //them regex
                return true;
        }
}
