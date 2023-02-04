package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import model.NhanKhau;
import services.KhaiTuService;
import services.NhanKhauService;
import services.ThongKeService;

import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerThongKe implements Initializable {
    @FXML
    private Label SLHK;
    @FXML
    private Label SLNK;
    @FXML
    private Label SLKT;

    @FXML
    private Label SLTT;

    @FXML
    private Label SLTV;

    @FXML
    private Label SLNKTheoGioiTinh;

    @FXML
    private CategoryAxis TKNKTTTx;
    @FXML
    private LineChart<String, Integer> TKNKTheoThoiGian;

    @FXML
    private PieChart tkGioiTinh;

    @FXML
    private BarChart<String, Integer> tkGioiTinhTheoTuoi;

    @FXML
    private ComboBox<String> comboBoxGioiTinh;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Thiết lập các giá trị cho combobox
        comboBoxGioiTinh.setItems(FXCollections.observableArrayList("Nam", "Nữ"));
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
        //Xác định số lượng tạm trú tạm vắng
        try {
            SLTT.setText(String.valueOf(new ThongKeService().demSoLuongTamTru()));
            SLTV.setText(String.valueOf(new ThongKeService().demSoLuongTamVang()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            int nam = new ThongKeService().demSoLuongNhanKhauTheoGioiTinh("Nam");
            int nu = new ThongKeService().demSoLuongNhanKhauTheoGioiTinh("Nữ");
            double pnam = ((double) nam * 100) / (nam + nu);
            double pnu = ((double) nu * 100) / (nam + nu );
            DecimalFormat df = new DecimalFormat("#.##");
            ObservableList<PieChart.Data> pieChartsData =
                    FXCollections.observableArrayList(
                            new PieChart.Data(" Nam (" + df.format(pnam) + "%)", nam),
                            new PieChart.Data(" Nữ (" + df.format(pnu) + "%)", nu));
            tkGioiTinh.setData(pieChartsData);

            String[] pieColors = { "blue", "red"};
            int i = 0;
            for (PieChart.Data data : pieChartsData) {
                data.getNode().setStyle(
                        "-fx-pie-color: " + pieColors[i % pieColors.length] + ";"
                );
                i++;
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        List<NhanKhau> listNhanKhau = null;
        try {
            listNhanKhau = NhanKhauService.getListNhanKhau();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int namNhoNhat = 0, namduoild = 0, nuduoild = 0, namld = 0, nuld = 0, namngoaild = 0, nungoaild = 0;
        LocalDate cur = LocalDate.now();

        for (NhanKhau nhanKhau: listNhanKhau) {
            String[] date = nhanKhau.getNgaySinh().split("-");
            LocalDate tmp = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
            int year = Period.between(tmp, cur).getYears();
            if (year > namNhoNhat) {
                namNhoNhat = year;
            }
            boolean daChet = true;
            try {
                daChet = KhaiTuService.daChet(nhanKhau.getMaNhanKhau());
                if (daChet){
                    System.out.println(nhanKhau.getMaNhanKhau() + "Die");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (!daChet) {
                if (nhanKhau.getGioiTinh().equals("Nam")) {
                    if (year < 15)
                        namduoild++;
                    else if (year < 65)
                        namld++;
                    else
                        namngoaild++;
                }

                if (nhanKhau.getGioiTinh().equals("Nữ")) {
                    if (year < 15)
                        nuduoild++;
                    else if (year < 65)
                        nuld++;
                    else
                        nungoaild++;
                }
            }
        }

        XYChart.Series<String, Integer> series1 = new XYChart.Series();
        series1.setName("Nam");
        series1.getData().add(new XYChart.Data("Dưới độ tuổi lao động", namduoild));
        series1.getData().add(new XYChart.Data("Trong độ tuổi lao động", namld));
        series1.getData().add(new XYChart.Data("Ngoài độ tuổi lao động", namngoaild));
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Nữ");
        series2.getData().add(new XYChart.Data("Dưới độ tuổi lao động", nuduoild));
        series2.getData().add(new XYChart.Data("Trong độ tuổi lao động", nuld));
        series2.getData().add(new XYChart.Data("Ngoài độ tuổi lao động", nungoaild));
        tkGioiTinhTheoTuoi.getData().addAll(series1, series2);

        XYChart.Series series3 = new XYChart.Series();

        int x=0, y=namNhoNhat;
        while (x < 15){
            if (y < namNhoNhat /15 ) {
                break;
            }
            y = y - namNhoNhat / 15;
            int year = LocalDate.now().getYear()- y;
            try {
                int soLuongNhanKhau = ThongKeService.demSoLuongNhanKhauTheoThoiGian(year);
                series3.getData().add(new XYChart.Data(""+ year, soLuongNhanKhau));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            x++;
        }
        if (y != 0) {
            int year = LocalDate.now().getYear();
            try {
                int soLuongNhanKhau = ThongKeService.demSoLuongNhanKhauTheoThoiGian(year);
                series3.getData().add(new XYChart.Data(""+ year, soLuongNhanKhau));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        TKNKTTTx.setLabel("Năm sinh trước năm");
        TKNKTheoThoiGian.getData().add(series3);
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
