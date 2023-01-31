package controller.KhoanThu;

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
import model.KhoanThu;
import services.KhoanThuService;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerSuaKhoanThu implements Initializable {
    @FXML
    private TextField tfMaKhoanThu;
    @FXML
    private TextField tfTenKhoanThu;
    @FXML
    private TextField tfSoTien;
    @FXML
    private TextField tfMaHo;
    @FXML
    private TextField tfNgayNop;
    @FXML
    private Button btXacNhan;
    public void setKhoanThu(KhoanThu khoanThuModel) {
        tfMaKhoanThu.setText(khoanThuModel.getMaKhoanThu());
        tfTenKhoanThu.setText(khoanThuModel.getTenKhoanThu());
        tfSoTien.setText(khoanThuModel.getSoTienCanThu());
        tfMaHo.setText(khoanThuModel.getMaHo());
        tfNgayNop.setText(khoanThuModel.getNgayNop());
    }
    @FXML
    public void update(ActionEvent event) throws SQLException {
        //Hiển thị thông báo
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setContentText("Bạn có muốn sửa thông tin ?");
        alert.setHeaderText(null);
        Optional<ButtonType> optional = alert.showAndWait();
        if(optional.get() == ButtonType.OK)
        {
            //thao tác đến database để update người đc chọn
            try {
                new KhoanThuService().update(tfMaKhoanThu.getText(), tfTenKhoanThu.getText(), tfSoTien.getText(), tfMaHo.getText(), tfNgayNop.getText());
            }
            catch (Exception e) {
                Alert alert1 = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào thông tin chính xác!", ButtonType.OK);
                alert1.setHeaderText(null);
                alert1.showAndWait();
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btXacNhan.addEventHandler(MouseEvent.MOUSE_MOVED, event -> btXacNhan.setEffect(new SepiaTone()));
        btXacNhan.addEventHandler(MouseEvent.MOUSE_EXITED, event -> btXacNhan.setEffect(null));
    }
}
