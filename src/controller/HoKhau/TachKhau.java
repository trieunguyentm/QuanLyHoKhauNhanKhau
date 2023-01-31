package controller.HoKhau;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.HoKhau;
import services.HoKhauService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class TachKhau implements Initializable {
    @FXML
    private TextField tfMaHoMoi;
    @FXML
    private TextField tfMaChuHoMoi;
    @FXML
    private TextField tfDanhSach;
    @FXML
    private TextField tfMaHoCu;
    @FXML
    private Button xacNhanThayDoi;

    // Event Listener on Button.onAction
    @FXML
    public void xacNhanTachKhau(ActionEvent event) throws SQLException {
        //method check() phía dưới phục vụ việc kiểm tra data người dùng nhập vào, nếu sai thì không làm gì cả
        if(!check()) return;

        String maHoMoi = tfMaHoMoi.getText();
        String maChuMoi = tfMaChuHoMoi.getText();
        String danhSach = tfDanhSach.getText();
        String maHoCu = tfMaHoCu.getText();

        //ý tưởng:
        //-tạo  một hộ mới với một người làm chủ hộ, sửa thông tin người này
        //- những người còn lại sẽ sửa thông tin maHo của họ

        //tao ho khau moi voi chu ho moi, sau đó truyền dữ liệu này vào class service để thao tác tới database
        try {
            HoKhau hoKhau = new HoKhau(maHoMoi, maChuMoi);
            new HoKhauService().tachHoKhau(hoKhau, maHoCu);

            //tach ma cua nhung nguoi muon tach khau tu string
            String[] toSplit = danhSach.split(" ");
            //update ho khau cua tung nguoi muốn tách khẩu
            for (String s : toSplit) {
                new HoKhauService().chuyenHoKhau(maHoCu, maHoMoi, s);
            }
            //thông báo cho người nhập cần cập nhật quan hệ cho các thành viên trong gia đình đó
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy cập nhật quan hệ của các thành viên hộ trên", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào thông tin chính xác!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        //đóng sence đó
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    public boolean check() {
        //them regex
        Pattern pattern;
        // kiem tra ma nguoi nhap vao
        // ma nguoi 4 so
        pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
        if (!((pattern.matcher(tfMaHoCu.getText()).matches()) || (pattern.matcher(tfMaHoMoi.getText()).matches()) )) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã hộ hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
        if (!pattern.matcher(tfMaChuHoMoi.getText()).matches()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã nhân ẩu hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }

        pattern = Pattern.compile("(([0-9][0-9][0-9][0-9]\\s){0,100})");
        if (!pattern.matcher(tfDanhSach.getText() + " ").matches()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào danh sách hợp lệ!", ButtonType.OK);
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
