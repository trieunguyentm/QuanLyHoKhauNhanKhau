package controller;

import controller.KhoanThu.ControllerSuaKhoanThu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.DataBaseConnection;
import model.KhoanThu;
import model.NhanKhau;
import services.KhoanThuService;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ControllerKhoanThu implements Initializable {
    @FXML
    private TableView<KhoanThu> tvKhoanThu;
    @FXML
    private TableColumn<NhanKhau, String> maKhoanThu;
    @FXML
    private TableColumn<NhanKhau, String> tenKhoanThu;
    @FXML
    private TableColumn<NhanKhau, String> soTienCanThu;
    @FXML
    private TableColumn<NhanKhau, String> maHo;
    @FXML
    private TableColumn<NhanKhau, String> ngayNop;
    @FXML
    private Button btThem;
    @FXML
    private Button btXoa;
    @FXML
    private Button btSua;
    @FXML
    private TextField tfTimKiem;
    @FXML
    public void clickThem() throws IOException, SQLException {
        Parent home = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/KhoanThu/ThemKhoanThu.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(home,600,400));
        stage.setResizable(false);
        //Khóa stage cha lại khi hiện stage con
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Thêm khoản thu");
        stage.showAndWait();
        showKhoanThu();
    }
    @FXML
    public void clickXoa() throws SQLException {
            //tao mot object khoản thu
            KhoanThu khoanThuModel = tvKhoanThu.getSelectionModel().getSelectedItem();
            if (khoanThuModel == null) {
                Alert alert1 = new Alert(Alert.AlertType.WARNING, "Chọn khoản thu cần xóa !", ButtonType.OK);
                alert1.setHeaderText(null);
                alert1.showAndWait();
                return;
            }
            //Hiển thị thông báo
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận");
            alert.setContentText("Bạn có muốn xóa thông tin này không ?");
            alert.setHeaderText(null);
            Optional<ButtonType> optional = alert.showAndWait();
            if(optional.get() == ButtonType.OK)
            {
                //connect DB
                DataBaseConnection connectionToDB = new DataBaseConnection();
                Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
                //tạo statement
                Statement stm = connection.createStatement();
                //query
                String query = "DELETE from khoan_thu where maKhoanThu = '" + khoanThuModel.getMaKhoanThu() + "'";
                System.out.println(query);
                stm.executeUpdate(query);
                connection.close();
                showKhoanThu();
            }

    }
    @FXML
    public void clickSua() throws IOException, SQLException {
        //tao mot object khoản thu
        KhoanThu khoanThuModel = tvKhoanThu.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/KhoanThu/SuaKhoanThu.fxml"));
        Parent home = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(home, 600, 400));
        ControllerSuaKhoanThu suaKhoanThu = loader.getController();

        // bat loi truong hop khong hop le
        if (suaKhoanThu == null)
            return;
        if (khoanThuModel == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Chọn khoản thu cần sửa !", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }
        //đổ data đó vaof view sua nhan khau
        suaKhoanThu.setKhoanThu(khoanThuModel);

        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Sửa thông tin");
        stage.showAndWait();
        showKhoanThu();
    }
    public void showKhoanThu() throws SQLException {
        //Lấy danh sách khoản thu từ service
        List<KhoanThu> listKhoanThu = new KhoanThuService().getListKhoanThu();

        ObservableList<KhoanThu> listValueTableView = FXCollections.observableArrayList(listKhoanThu);
        // thiet lap cac cot cho tableviews
        maKhoanThu.setCellValueFactory(new PropertyValueFactory<>("maKhoanThu"));
        tenKhoanThu.setCellValueFactory(new PropertyValueFactory<>("tenKhoanThu"));
        soTienCanThu.setCellValueFactory(new PropertyValueFactory<>("soTienCanThu"));
        maHo.setCellValueFactory(new PropertyValueFactory<>("maHo"));
        ngayNop.setCellValueFactory(new PropertyValueFactory<>("ngayNop"));
        tvKhoanThu.setItems(listValueTableView);
    }
    @FXML
    public void search() throws SQLException {
        ObservableList<KhoanThu> listValueTableView_tmp;
        String input = tfTimKiem.getText();
        List<KhoanThu> listKhoanThu = new KhoanThuService().getListKhoanThu();
        ObservableList<KhoanThu> listValueTableView = FXCollections.observableArrayList(listKhoanThu);

        if (input.length() == 0) {
            tvKhoanThu.setItems(listValueTableView);
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào thông tin cần tìm kiếm!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        int index = 0;
        List<KhoanThu> listKhoanThuModelsSearch = new ArrayList<>();
        for ( KhoanThu khoanThu : listKhoanThu) {
            if (khoanThu.getMaKhoanThu().contains(input)) {
                listKhoanThuModelsSearch.add(khoanThu);
                index++;
            }
        }
        listValueTableView_tmp = FXCollections.observableArrayList(listKhoanThuModelsSearch);
        tvKhoanThu.setItems(listValueTableView_tmp);

        // neu khong tim thay thong tin can tim kiem -> thong bao toi nguoi dung khong
        // tim thay
        if (index == 0) {
            tvKhoanThu.setItems(listValueTableView); // hien thi toan bo thong tin
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Không tìm thấy thông tin!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showKhoanThu();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //Hiệu ứng khi di chuyển chuột qua button
        btThem.addEventHandler(MouseEvent.MOUSE_MOVED, event -> btThem.setEffect(new SepiaTone()));
        btThem.addEventHandler(MouseEvent.MOUSE_EXITED, event -> btThem.setEffect(null));
        btXoa.addEventHandler(MouseEvent.MOUSE_MOVED, event -> btXoa.setEffect(new SepiaTone()));
        btXoa.addEventHandler(MouseEvent.MOUSE_EXITED, event -> btXoa.setEffect(null));
        btSua.addEventHandler(MouseEvent.MOUSE_MOVED, event -> btSua.setEffect(new SepiaTone()));
        btSua.addEventHandler(MouseEvent.MOUSE_EXITED, event -> btSua.setEffect(null));
        //Xử lý khi người dùng xóa thông tin tìm kiếm ở textfield Search
        tfTimKiem.textProperty().addListener((observableValue, s, t1) -> {
            if(Objects.equals(t1, "")) {
                try {
                    showKhoanThu();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
