package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import services.ThongKeService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerThongKe implements Initializable {
    @FXML
    private Label SLHK;
    @FXML
    private Label SLNK;
    @FXML
    private Label SLKT;
    @FXML
    private Label SLNKTheoGioiTinh;
    @FXML
    private ComboBox<String> comboBoxGioiTinh;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Thiết lập các giá trị cho combobox
        comboBoxGioiTinh.setItems(FXCollections.observableArrayList("Nam", "Nu"));
        //Xác định số lượng HK
        try {
            SLHK.setText(String.valueOf(new ThongKeService().demSoLuongHoKhau()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Xác định số lượng NK
        try {
            SLNK.setText(String.valueOf(new ThongKeService().demSoLuongNhanKhau()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Xác định số lượng Khoản thu
        try {
            SLKT.setText(String.valueOf(new ThongKeService().demSoLuongKhoanThu()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void changeCombobox()
    {
        //Xác định số lượng nhân khẩu theo giới tính
            try {
                SLNKTheoGioiTinh.setText(String.valueOf(new ThongKeService().demSoLuongNhanKhauTheoGioiTinh(comboBoxGioiTinh.getValue())));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}
