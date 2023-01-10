package controller.NhanKhau;

import controller.ControllerNhanKhau;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.NhanKhau;
import services.NhanKhauService;

import java.sql.SQLException;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ThemNhanKhau {
    @FXML
    private TextField tfMaNhanKhau;
    @FXML
    private TextField tfHoTen;
    @FXML
    private TextField tfGioiTinh;
    @FXML
    private TextField tfNgaySinh;
    @FXML
    private TextField tfQueQuan;
    @FXML
    private TextField tfNgheNghiep;
    @FXML
    private TextField tfMaHo;
    @FXML
    private TextField tfQuanHeVoiChuHo;

    // Event Listener on Button.onAction
    @FXML
    public void add(ActionEvent event) throws SQLException, ClassNotFoundException {
        //pattern to check data validation in client side
        if(!check()) return;

       String ma = tfMaNhanKhau.getText();
       String hoTen = tfHoTen.getText();
       String gioiTinh = tfGioiTinh.getText();
       String ngaySinh = tfNgaySinh.getText();
       String queQuan = tfQueQuan.getText();
       String ngheNgiep = tfNgheNghiep.getText();
       String maHo = tfMaHo.getText();
       String quanHe = tfQuanHeVoiChuHo.getText();

        NhanKhau nhanKhau = new NhanKhau(ma, hoTen, gioiTinh, ngaySinh, queQuan, ngheNgiep,maHo, quanHe);
        new NhanKhauService().add(nhanKhau);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public boolean check() {
        //TODO: khoảng trắng vẫn có thể nhập không thông qua pattern
        Pattern pattern;
        // kiem tra ma nguoi nhap vao
        // ma nguoi 4 so
        pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
        System.out.println(ControllerNhanKhau.numberOfPeople);
        if (!((pattern.matcher(tfMaNhanKhau.getText()).matches()) && (Integer.parseInt(tfMaNhanKhau.getText()) > ControllerNhanKhau.numberOfPeople))) {
            Alert alert = new Alert(AlertType.WARNING, "Hãy nhập vào mã nhân khẩu hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        //regex hoTen
        pattern = Pattern.compile("^[AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+ [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+(?: [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*)*");
        if (!pattern.matcher(tfHoTen.getText()).matches()) {
            Alert alert = new Alert(AlertType.WARNING, "Hãy nhập vào họ tên hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        //check gioitinh
        if(!(tfGioiTinh.getText().equals("Nam") || tfGioiTinh.getText().equals("Nu"))) {
            Alert alert = new Alert(AlertType.WARNING, "Hãy nhập vào giới tính hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        //regex ngaysinh
        pattern = Pattern.compile("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))");
        if (!pattern.matcher(tfNgaySinh.getText()).matches()) {
            Alert alert = new Alert(AlertType.WARNING, "Hãy nhập vào ngày hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
        if (!pattern.matcher(tfMaHo.getText()).matches()) {
            Alert alert = new Alert(AlertType.WARNING, "Hãy nhập vào mã hộ hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        //que quan, quan he and nghe nghiep
        if(tfQueQuan.getText().equals("")) {
            Alert alert = new Alert(AlertType.WARNING, "Hãy nhập vào quê quán", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        if(tfQuanHeVoiChuHo.getText().equals("")) {
            Alert alert = new Alert(AlertType.WARNING, "Hãy nhập vào quan hệ", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        if(tfNgheNghiep.getText().equals("")) {
            Alert alert = new Alert(AlertType.WARNING, "Hãy nhập vào nghề nghiệp", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        return true;
    }
}
