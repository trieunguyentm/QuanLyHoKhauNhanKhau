package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.NhanKhau;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import services.NhanKhauService;

public class ControllerNhanKhau implements Initializable{
    @FXML
    private TableView tvNhanKhau;
    @FXML
    private TableColumn colID;
    @FXML
    private TableColumn colHoVaTen;
    @FXML
    private TableColumn colTuoi;
    @FXML
    private TableColumn colGioiTinh;
    @FXML
    private TableColumn colGhiChu;
    @FXML
    private TableColumn colQuanHe;
    @FXML
    private TableColumn colMaHo;
    public static int numberOfPeople;
    private ObservableList<NhanKhau> listValueTableView;
    private List<NhanKhau> listNhanKhau;

    // Event Listener on Button.onAction
    @FXML
    public void themNhanKhau(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        Parent home = FXMLLoader.load(getClass().getResource("/view/NhanKhau/themNhanKhau.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(home,800,600));
        stage.setResizable(false);
        stage.showAndWait();
        showNhanKhau();
    }
    // Event Listener on Button.onAction
    @FXML
    public void baoTu(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        Parent home = FXMLLoader.load(getClass().getResource("/view/NhanKhau/baoTu.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(home,600,400));
        stage.setResizable(false);
        stage.showAndWait();
        showNhanKhau();
    }

    @FXML
    public void suaThongTin(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        Parent home = FXMLLoader.load(getClass().getResource("/view/NhanKhau/update.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(home,600,400));
        stage.setResizable(false);
        stage.showAndWait();
        showNhanKhau();
    }

    public void showNhanKhau() throws ClassNotFoundException, SQLException {
        listNhanKhau = new NhanKhauService().getListNhanKhau();
        numberOfPeople = listNhanKhau.size();
        listValueTableView = FXCollections.observableArrayList(listNhanKhau);

        // tao map anh xa gia tri Id sang maHo

        // thiet lap cac cot cho tableviews
        colID.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("maNhanKhau"));
        colHoVaTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
        colTuoi.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("ngaySinh"));
        colGioiTinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("gioiTinh"));
        colMaHo.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("maHo"));
        colQuanHe.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("quanHeVoiChuHo"));
        colGhiChu.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("ghiChu"));
        tvNhanKhau.setItems(listValueTableView);
        // thiet lap gia tri cho combobox
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            System.out.println("go to init");
            showNhanKhau();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}