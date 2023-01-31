package controller.TamTruTamVang;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.MouseEvent;
import model.TamTru;
import services.TamTruTamVangService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TraCuuTamTru implements Initializable {
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn colID;
    @FXML
    private TableColumn colHoTen;
    @FXML
    private TableColumn colCMND;
    @FXML
    private TableColumn colSDT;
    @FXML
    private TableColumn colTuNgay;
    @FXML
    private TableColumn colDenNgay;
    @FXML
    private TableColumn colLiDo;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btSearch;
    private List<TamTru> listTamTru;
    private ObservableList<TamTru> listValueTableView;
    @FXML
    public void search(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<TamTru> listValueTableView_tmp = null;
        String input = tfSearch.getText();
        listTamTru = new TamTruTamVangService().getListTamTru();

        if (input.length() == 0) {
            tableView.setItems(listValueTableView);
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào thông tin cần tìm kiếm!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        int index = 0;
        List<TamTru> listHoKhauModelsSearch = new ArrayList<>();
        for (TamTru tamTru : listTamTru) {
            if (tamTru.getHoTen().contains(input)) {
                listHoKhauModelsSearch.add(tamTru);
                index++;
            }
        }
        listValueTableView_tmp = FXCollections.observableArrayList(listHoKhauModelsSearch);
        tableView.setItems(listValueTableView_tmp);

        // neu khong tim thay thong tin can tim kiem -> thong bao toi nguoi dung khong
        // tim thay
        if (index == 0) {
            tableView.setItems(listValueTableView); // hien thi toan bo thong tin
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Không tìm thấy thông tin!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }


    }
    public void showTamTru() throws ClassNotFoundException, SQLException {
        //get list nhân khẩu từ database
        listTamTru = new TamTruTamVangService().getListTamTru();
        //thuộc tính static để xem hiện tại có bao nhiêu người

        listValueTableView = FXCollections.observableArrayList(listTamTru);
        // thiet lap cac cot cho tableviews
        colID.setCellValueFactory(new PropertyValueFactory<TamTru, Integer>("id"));
        colHoTen.setCellValueFactory(new PropertyValueFactory<TamTru, String>("hoTen"));
        colCMND.setCellValueFactory(new PropertyValueFactory<TamTru, String>("CMND"));
        colSDT.setCellValueFactory(new PropertyValueFactory<TamTru, String>("soDienThoaiNguoiDangKy"));
        colTuNgay.setCellValueFactory(new PropertyValueFactory<TamTru, String>("tuNgay"));
        colDenNgay.setCellValueFactory(new PropertyValueFactory<TamTru, String>("denNgay"));
        colLiDo.setCellValueFactory(new PropertyValueFactory<TamTru, String>("lyDo"));
        tableView.setItems(listValueTableView);
        // thiet lap gia tri cho combobox
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showTamTru();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        btSearch.addEventHandler(MouseEvent.MOUSE_MOVED, event -> btSearch.setEffect(new SepiaTone()));
        btSearch.addEventHandler(MouseEvent.MOUSE_EXITED, event -> btSearch.setEffect(null));
    }
}