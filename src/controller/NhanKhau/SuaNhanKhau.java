package controller.NhanKhau;

import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import services.NhanKhauService;

import java.sql.SQLException;

public class SuaNhanKhau {
    @FXML
    private TextField tfMaNhanKhau;
    @FXML
    private TextField tfQuanHeVoiChuHo;
    @FXML
    private TextField tfMaHo;
    @FXML
    private TextField tfHoTen;

    // Event Listener on Button.onAction
    @FXML
    public void update(ActionEvent event) throws SQLException, ClassNotFoundException {
        new NhanKhauService().update(tfMaNhanKhau.getText(), tfQuanHeVoiChuHo.getText(), tfMaHo.getText(), tfHoTen.getText());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
