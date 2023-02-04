package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.MouseEvent;
import model.DinhChinh;
import model.HoKhau;
import services.DinhChinhService;
import services.HoKhauService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerDinhChinh implements Initializable {
    @FXML
    private TableView<DinhChinh> tvDinhChinh;
    @FXML
    private TableColumn<DinhChinh, String> colThongTinThayDoi;
    @FXML
    private TableColumn<DinhChinh, String> colThayDoiTu;
    @FXML
    private TableColumn<DinhChinh, String> colThayDoiThanh;
    @FXML
    private TableColumn<DinhChinh, String> colNgayThayDoi;
    @FXML
    private TableColumn<DinhChinh, String> colNguoiThayDoi;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    @FXML
    private TextField tfSearch;
    @FXML
    public void search() throws SQLException {
        ObservableList<DinhChinh> listValueTableView_tmp;
        String input = tfSearch.getText();
        List<DinhChinh> listDinhChinh = new DinhChinhService().getListDinhChinh(input);
        ObservableList<DinhChinh> listValueTableView = FXCollections.observableArrayList(listDinhChinh);

        if (input.length() == 0) {
            tvDinhChinh.setItems(listValueTableView);
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào thông tin cần tìm kiếm!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        int index = 0;
        List<DinhChinh> listDinhChinhModelsSearch = new ArrayList<>();
        for ( DinhChinh dinhChinh : listDinhChinh) {
            listDinhChinhModelsSearch.add(dinhChinh);
            index++;
        }
        listValueTableView_tmp = FXCollections.observableArrayList(listDinhChinhModelsSearch);
        tvDinhChinh.setItems(listValueTableView_tmp);

        // neu khong tim thay thong tin can tim kiem -> thong bao toi nguoi dung khong
        // tim thay
        if (index == 0) {
            tvDinhChinh.setItems(listValueTableView); // hien thi toan bo thong tin
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Không tìm thấy thông tin!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }


    }
}