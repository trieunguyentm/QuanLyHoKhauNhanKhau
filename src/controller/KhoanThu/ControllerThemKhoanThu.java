package controller.KhoanThu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.KhoanThu;
import services.KhoanThuService;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerThemKhoanThu implements Initializable {
    @FXML
    private TextField tfMaKhoanThu;
    @FXML
    private TextField tfTenKhoanThu;
    @FXML
    private TextField tfSoTien;
    @FXML
    private TextField tfMaHo;
    @FXML
    private DatePicker tfNgayNop;
    @FXML
    private Button btXacNhan;
    public boolean check()
    {
        String maKhoanThu = tfMaKhoanThu.getText();
        String tenKhoanThu = tfTenKhoanThu.getText();
        String soTien = tfSoTien.getText();
        String maHo = tfMaHo.getText();
        String ngayNop = tfNgayNop.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return !Objects.equals(maKhoanThu, "") && !Objects.equals(tenKhoanThu, "") && !Objects.equals(soTien, "") && !Objects.equals(maHo, "") && !Objects.equals(ngayNop, "");
    }
    @FXML
    public void update(ActionEvent event) throws SQLException {
        if(!check())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào đầy đủ thông tin!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        String maKhoanThu = tfMaKhoanThu.getText();
        String tenKhoanThu = tfTenKhoanThu.getText();
        String soTien = tfSoTien.getText();
        String maHo = tfMaHo.getText();
        String ngayNop = tfNgayNop.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //thêm khoản thu
        KhoanThu khoanThu = new KhoanThu(maKhoanThu, tenKhoanThu, soTien, maHo, ngayNop);
        new KhoanThuService().add(khoanThu);

        //Đóng stage
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btXacNhan.addEventHandler(MouseEvent.MOUSE_MOVED, event -> btXacNhan.setEffect(new SepiaTone()));
        btXacNhan.addEventHandler(MouseEvent.MOUSE_EXITED, event -> btXacNhan.setEffect(null));
    }
}
