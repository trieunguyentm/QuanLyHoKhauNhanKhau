package controller.TamTruTamVang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.MouseEvent;
import model.TamTru;
import model.TamVang;
import services.TamTruTamVangService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
public class TraCuuTamVang implements  Initializable{
    public Button search;
    @FXML
    private TableView<TamVang> tbView;
    @FXML
    private TableColumn<TamTru, Integer> colID;
    @FXML
    private TableColumn<TamTru, String> colMaNhanKhau;
    @FXML
    private TableColumn<TamTru, String> colNoiTamTru;
    @FXML
    private TableColumn<TamTru, String> colTuNgay;
    @FXML
    private TableColumn<TamTru, String> colDenNgay;
    @FXML
    private TableColumn<TamTru, String> colLiDo;
    private List<TamVang> listTamVang;
    private ObservableList<TamVang> listValueTableView;

    @FXML
    private TextField tfSearch;
    @FXML
    public void search() throws SQLException {
        ObservableList<TamVang> listValueTableView_tmp;
        String input = tfSearch.getText();
        listTamVang = new TamTruTamVangService().getListTamVang();

        if (input.length() == 0) {
            tbView.setItems(listValueTableView);
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào thông tin cần tìm kiếm!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        int index = 0;
        List<TamVang> listHoKhauModelsSearch = new ArrayList<>();
        for (TamVang tamVang : listTamVang) {
            if (tamVang.getMaNhanKhau().contains(input)) {
                listHoKhauModelsSearch.add(tamVang);
                index++;
            }
        }
        listValueTableView_tmp = FXCollections.observableArrayList(listHoKhauModelsSearch);
        tbView.setItems(listValueTableView_tmp);

        // neu khong tim thay thong tin can tim kiem -> thong bao toi nguoi dung khong
        // tim thay
        if (index == 0) {
            tbView.setItems(listValueTableView); // hien thi toan bo thong tin
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Không tìm thấy thông tin!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }


    }
    public void showTamVang() throws ClassNotFoundException, SQLException {
        //get list nhân khẩu từ database
        listTamVang = new TamTruTamVangService().getListTamVang();
        //thuộc tính static để xem hiện tại có bao nhiêu người

        listValueTableView = FXCollections.observableArrayList(listTamVang);
        // thiet lap cac cot cho tableviews
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMaNhanKhau.setCellValueFactory(new PropertyValueFactory<>("maNhanKhau"));
        colNoiTamTru.setCellValueFactory(new PropertyValueFactory<>("noiTamTru"));
        colTuNgay.setCellValueFactory(new PropertyValueFactory<>("tuNgay"));
        colDenNgay.setCellValueFactory(new PropertyValueFactory<>("denNgay"));
        colLiDo.setCellValueFactory(new PropertyValueFactory<>("lyDo"));
        tbView.setItems(listValueTableView);
        // thiet lap gia tri cho combobox
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showTamVang();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        search.addEventHandler(MouseEvent.MOUSE_MOVED, event -> search.setEffect(new SepiaTone()));
        search.addEventHandler(MouseEvent.MOUSE_EXITED, event -> search.setEffect(null));
    }
}