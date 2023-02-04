package controller.NhanKhau;

import controller.ControllerNhanKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.NhanKhau;
import services.NhanKhauService;

import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ThemNhanKhau implements Initializable {
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
    @FXML
    private Button xacNhanThongTin;

    // Event Listener on Button.onAction
    @FXML
    public void add(ActionEvent event) throws SQLException {

        //kiểm tra dữ liệu đầu vào có hợp lệ
        if(!check()) return;
        //nếu hợp lệ, lấy dữ liệu đó
        String ma = tfMaNhanKhau.getText();
        String hoTen = tfHoTen.getText();
        String gioiTinh = tfGioiTinh.getText();
        String ngaySinh = tfNgaySinh.getText();
        String queQuan = tfQueQuan.getText();
        String ngheNgiep = tfNgheNghiep.getText();
        String maHo = tfMaHo.getText();
        String quanHe = tfQuanHeVoiChuHo.getText();
        //tạo đối tượng nhân khẩu để thêm, thực hiện gọi towis class service để thêm người
        try {
            NhanKhau nhanKhau = new NhanKhau(ma, hoTen, gioiTinh, ngaySinh, queQuan, ngheNgiep, maHo, quanHe);
            new NhanKhauService().add(nhanKhau);
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào thông tin chính xác!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public boolean check() throws SQLException {

        //TODO: khoảng trắng vẫn có thể nhập không thông qua pattern
        Pattern pattern;
        // kiem tra ma nguoi nhap vao
        // ma nguoi 4 so
        pattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
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
        if(!(tfGioiTinh.getText().equals("Nam") || tfGioiTinh.getText().equals("Nữ"))) {
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

        //checjk ngày sinh
        String dateBirthOfBoss = new NhanKhauService().getInfOfHomeOwner(tfMaHo.getText());
        if(tfQuanHeVoiChuHo.getText().toLowerCase().contains("con") || tfQuanHeVoiChuHo.getText().toLowerCase().contains("cháu")) {
            if(Integer.parseInt(dateBirthOfBoss.substring(0, 4)) + 10 >= Integer.parseInt(tfNgaySinh.getText().substring(0, 4))) {
                Alert alert = new Alert(AlertType.WARNING, "năm sinh của con/cháu không thể nhỏ hơn năm sinh của chủ", ButtonType.OK);
                alert.setHeaderText(null);
                alert.showAndWait();
                return false;
            }
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
        // check gái/ trai
        if(tfGioiTinh.getText().equals("Nam")) {
            if(tfQuanHeVoiChuHo.getText().contains("gái") ||  tfQuanHeVoiChuHo.getText().contains("vợ") || tfQuanHeVoiChuHo.getText().contains("bà")){
                Alert alert = new Alert(AlertType.WARNING, "giới tính - quan hệ không phù hợp: giới tính là nam", ButtonType.OK);
                alert.setHeaderText(null);
                alert.showAndWait();
                return false;
            }
        }
        else {
            if(tfQuanHeVoiChuHo.getText().contains("trai") ||  tfQuanHeVoiChuHo.getText().contains("chồng") || tfQuanHeVoiChuHo.getText().contains("ông")) {
                Alert alert = new Alert(AlertType.WARNING, "giới tính - quan hệ không phù hợp: giới tính đã nhập là nữ", ButtonType.OK);
                alert.setHeaderText(null);
                alert.showAndWait();
                return false;
            }
        }

        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        xacNhanThongTin.addEventHandler(MouseEvent.MOUSE_MOVED, event -> xacNhanThongTin.setEffect(new SepiaTone()));
        xacNhanThongTin.addEventHandler(MouseEvent.MOUSE_EXITED, event -> xacNhanThongTin.setEffect(null));
    }
}