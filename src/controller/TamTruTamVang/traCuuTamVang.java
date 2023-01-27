package controller.TamTruTamVang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import model.TamTru;
import model.TamVang;
import services.TamTruTamVangService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
public class traCuuTamVang implements  Initializable{
    @FXML
    private TableView tbView;
    @FXML
    private TableColumn colID;
    @FXML
    private TableColumn colMaNhanKhau;
    @FXML
    private TableColumn colNoiTamTru;
    @FXML
    private TableColumn colTuNgay;
    @FXML
    private TableColumn colDenNgay;
    @FXML
    private TableColumn colLiDo;
    private List<TamVang> listTamVang;
    private ObservableList<TamVang> listValueTableView;

    @FXML
    private TextField tfSearch;
    @FXML
    public void search(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<TamVang> listValueTableView_tmp = null;
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
            if (tamVang.getMaNhanKhau().equals(input)) {
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
        colID.setCellValueFactory(new PropertyValueFactory<TamTru, Integer>("id"));
        colMaNhanKhau.setCellValueFactory(new PropertyValueFactory<TamTru, String>("maNhanKhau"));
        colNoiTamTru.setCellValueFactory(new PropertyValueFactory<TamTru, String>("noiTamTru"));
        colTuNgay.setCellValueFactory(new PropertyValueFactory<TamTru, String>("tuNgay"));
        colDenNgay.setCellValueFactory(new PropertyValueFactory<TamTru, String>("denNgay"));
        colLiDo.setCellValueFactory(new PropertyValueFactory<TamTru, String>("lyDo"));
        tbView.setItems(listValueTableView);
        // thiet lap gia tri cho combobox
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showTamVang();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
