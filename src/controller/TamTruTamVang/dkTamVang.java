package controller.TamTruTamVang;

import controller.ControllerNhanKhau;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.TamVang;
import services.TamTruTamVangService;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class dkTamVang {
    @FXML
    private TextField tfMaNhanKhau;
    @FXML
    private TextField tfTuNgay;
    @FXML
    private TextField tfDenNgay;
    @FXML
    private TextField tfLiDo;
    @FXML
    private TextField tfNoiTamTru;

    // Event Listener on Button.onAction
    @FXML
    public void add(ActionEvent event) throws SQLException {
        // TODO Autogenerated
        if(!check()) return;

        String maNhanKhau = tfMaNhanKhau.getText();
        String noiTamTru = tfNoiTamTru.getText();
        String tuNgay = tfTuNgay.getText();
        String denNgay = tfDenNgay.getText();
        String liDo = tfLiDo.getText();

        TamVang tamVang = new TamVang(maNhanKhau, noiTamTru, tuNgay, denNgay, liDo);
        new TamTruTamVangService().themTamVang(tamVang);

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public boolean check() {
        //TODO: khoảng trắng vẫn có thể nhập không thông qua pattern
        Pattern pattern;
        // kiem tra ma nguoi nhap vao
        // ma nguoi 4 so
        pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
        if (!((pattern.matcher(tfMaNhanKhau.getText()).matches()) )) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã nhân khẩu hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        //regex tu ngay
        pattern = Pattern.compile("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))");
        if (!pattern.matcher(tfTuNgay.getText()).matches()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào ngày hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        //regex den ngay
        pattern = Pattern.compile("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))");
        if (!pattern.matcher(tfDenNgay.getText()).matches()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào ngày hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        //que quan, quan he and nghe nghiep
        if(tfNoiTamTru.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào nơi tạm trú", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        if(tfLiDo.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào lí do", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        return true;
    }
}
