package controller;

import controller.NhanKhau.SuaNhanKhau;
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
import model.NhanKhau;
import services.NhanKhauService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerNhanKhau implements Initializable{
    @FXML
    private TableView<NhanKhau> tvNhanKhau;
    @FXML
    private TableColumn<NhanKhau, String> colID;
    @FXML
    private TableColumn<NhanKhau, String> colHoVaTen;
    @FXML
    private TableColumn<NhanKhau, String> colTuoi;
    @FXML
    private TableColumn<NhanKhau, String> colGioiTinh;
    @FXML
    private TableColumn<NhanKhau, String> colGhiChu;
    @FXML
    private TableColumn<NhanKhau, String> colQuanHe;
    @FXML
    private TableColumn<NhanKhau, String> colMaHo;
    @FXML
    private Button btThemNhanKhau;
    @FXML
    private Button btBaoTu;
    @FXML
    private Button btSuaThongTin;
    public static int numberOfPeople;

    // Event Listener on Button.onAction
    //render ra màn hình của phần thêm nhân khẩu, ủy quyền chức năng tương ứng với mô hình MVC chứa view đó
    @FXML
    public void themNhanKhau() throws IOException, ClassNotFoundException, SQLException {
        Parent home = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/NhanKhau/themNhanKhau.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(home,575,566));
        stage.setResizable(false);
        //Khóa stage cha lại khi hiện stage con
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Thêm nhân khẩu");
        stage.showAndWait();
        showNhanKhau();
    }
    // Event Listener on Button.onAction
    //render ra màn hình view báo tử, ủy quyền chức năng tương ứng với mô hình MVC chứa view đó
    @FXML
    public void baoTu() throws IOException, ClassNotFoundException, SQLException {
        Parent home = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/NhanKhau/baoTu.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(home,600,400));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Báo tử");
        stage.showAndWait();
        showNhanKhau();
    }

    //render ra màn hình của phần sửa thông tin, ủy quyền chức năng tương ứng với mô hình MVC chứa view đó
    @FXML
    public void suaThongTin() throws IOException, SQLException, ClassNotFoundException {
        //tao mot nhan khau object
        NhanKhau nhanKhauModel = tvNhanKhau.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/NhanKhau/update.fxml"));
        Parent home = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(home, 600, 400));
        SuaNhanKhau suaNhanKhau = loader.getController();

        // bat loi truong hop khong hop le
        if (suaNhanKhau == null)
            return;
        if (nhanKhauModel == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Chọn hộ khẩu cần sửa !", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }
        //đổ data đó vaof view sua nhan khau
        suaNhanKhau.setNhanKhau(nhanKhauModel);

        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Sửa thông tin");
        stage.showAndWait();
        showNhanKhau();
    }

    //set giá trị cho bảng tableview để nó render ra các data của bảng nhân khẩu
    public void showNhanKhau() throws ClassNotFoundException, SQLException {
        //get list nhân khẩu từ database
        List<NhanKhau> listNhanKhau = new NhanKhauService().getListNhanKhau();
        //thuộc tính static để xem hiện tại có bao nhiêu người
        numberOfPeople = listNhanKhau.size();

        ObservableList<NhanKhau> listValueTableView = FXCollections.observableArrayList(listNhanKhau);
        // thiet lap cac cot cho tableviews
        colID.setCellValueFactory(new PropertyValueFactory<>("maNhanKhau"));
        colHoVaTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        colTuoi.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        colGioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        colMaHo.setCellValueFactory(new PropertyValueFactory<>("maHo"));
        colQuanHe.setCellValueFactory(new PropertyValueFactory<>("quanHeVoiChuHo"));
        colGhiChu.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));
        tvNhanKhau.setItems(listValueTableView);
        // thiet lap gia tri cho combobox
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            showNhanKhau();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        btThemNhanKhau.addEventHandler(MouseEvent.MOUSE_MOVED, event -> btThemNhanKhau.setEffect(new SepiaTone()));
        btThemNhanKhau.addEventHandler(MouseEvent.MOUSE_EXITED, event -> btThemNhanKhau.setEffect(null));
        btBaoTu.addEventHandler(MouseEvent.MOUSE_MOVED, event -> btBaoTu.setEffect(new SepiaTone()));
        btBaoTu.addEventHandler(MouseEvent.MOUSE_EXITED, event -> btBaoTu.setEffect(null));
        btSuaThongTin.addEventHandler(MouseEvent.MOUSE_MOVED, event -> btSuaThongTin.setEffect(new SepiaTone()));
        btSuaThongTin.addEventHandler(MouseEvent.MOUSE_EXITED, event -> btSuaThongTin.setEffect(null));
    }

    @FXML
    private TextField tfSearch;
    //private ObservableList<TamTru> listValueTableView;
    @FXML
    public void search() throws SQLException {

        ObservableList<NhanKhau> listValueTableView_tmp;
        String input = tfSearch.getText();
        //kết nối table view với listNhanKhau
        List<NhanKhau> listNhanKhau = new NhanKhauService().getListNhanKhau();
        ObservableList<NhanKhau> listValueTableView = FXCollections.observableArrayList(listNhanKhau);
        if (input.length() == 0) {
            tvNhanKhau.setItems(listValueTableView);
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào thông tin cần tìm kiếm!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        int index = 0;
        List<NhanKhau> listHoKhauModelsSearch = new ArrayList<>();
        for (NhanKhau nhanKhau : listNhanKhau) {
            if (nhanKhau.getHoTen().contains(input)) {
                listHoKhauModelsSearch.add(nhanKhau);
                index++;
            }
        }
        //Kết nối bảng tìm kiếm được với listHoKhauModelsSearch
        listValueTableView_tmp = FXCollections.observableArrayList(listHoKhauModelsSearch);
        tvNhanKhau.setItems(listValueTableView_tmp);

        // neu khong tim thay thong tin can tim kiem -> thong bao toi nguoi dung khong
        // tim thay
        if (index == 0) {
            tvNhanKhau.setItems(listValueTableView); // hien thi toan bo thong tin
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Không tìm thấy thông tin!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }


    }

}