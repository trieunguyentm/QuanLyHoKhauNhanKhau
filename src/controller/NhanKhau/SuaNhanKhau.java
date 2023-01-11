package controller.NhanKhau;

import controller.ControllerNhanKhau;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.NhanKhau;
import services.NhanKhauService;

import java.sql.SQLException;
import java.util.regex.Pattern;

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
        //kiểm tra xem dữ liệu nhập có hợp lệ
        if(!check()) return;
        //thao tác đến database để update người đc chọn
        new NhanKhauService().update(tfMaNhanKhau.getText(), tfQuanHeVoiChuHo.getText(), tfMaHo.getText(), tfHoTen.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    //mục đích chinh để đổ data từ view vào các textfield nhằm nhập dễ dàng hơn
    public void setNhanKhau(NhanKhau nhanKhauModel) {
        tfMaNhanKhau.setText(nhanKhauModel.getMaNhanKhau());
        tfQuanHeVoiChuHo.setText(nhanKhauModel.getQuanHeVoiChuHo());
        tfMaHo.setText(nhanKhauModel.getMaHo());
        tfHoTen.setText(nhanKhauModel.getHoTen());
    }
    public boolean check() {
        Pattern pattern;
        //regex ma ho
        pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
        if (!pattern.matcher(tfMaHo.getText()).matches()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã hộ hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        //regex ma nguoi
        pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
        if (!((pattern.matcher(tfMaNhanKhau.getText()).matches()) )) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã nhân khẩu hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }

        //regex hoTen
        pattern = Pattern.compile("^[AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+ [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+(?: [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*)*");
        if (!pattern.matcher(tfHoTen.getText()).matches()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào họ tên hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }
        return true;

    }
}
