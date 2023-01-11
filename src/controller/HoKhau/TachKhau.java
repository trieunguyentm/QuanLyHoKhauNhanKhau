package controller.HoKhau;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.HoKhau;
import services.HoKhauService;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class TachKhau {
    @FXML
    private TextField tfMaHoMoi;
    @FXML
    private TextField tfMaChuHoMoi;
    @FXML
    private TextField tfDanhSach;
    @FXML
    private TextField tfMaHoCu;

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
        HoKhau hoKhau = new HoKhau(maHoMoi, maChuMoi);
        new HoKhauService().tachHoKhau(hoKhau, maHoCu);

        //tach ma cua nhung nguoi muon tach khau tu string
        String[] toSplit = danhSach.split(" ");
        //update ho khau cua tung nguoi muốn tách khẩu
        for(int i = 0; i < toSplit.length; i++) {
            new HoKhauService().chuyenHoKhau(maHoCu, maHoMoi, toSplit[i]);
        }
        //thông báo cho người nhập cần cập nhật quan hệ cho các thành viên trong gia đình đó
        Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy cập nhật quan hệ của các thành viên hộ trên", ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
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
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã ho hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
        if (!pattern.matcher(tfMaChuHoMoi.getText()).matches()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã nhan khau hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }

        pattern = Pattern.compile("(([0-9][0-9][0-9][0-9]\\s){0,100})");
        if (!pattern.matcher(tfDanhSach.getText() + " ").matches()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào danh sach hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }


        return  true;
    }
}
