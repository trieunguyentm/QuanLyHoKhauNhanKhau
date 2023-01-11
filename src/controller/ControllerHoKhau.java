package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.HoKhau;
import model.NhanKhau;
import services.HoKhauService;
import services.NhanKhauService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerHoKhau implements Initializable {
    @FXML
    private Button tfTachKhau;
    @FXML
    private TableView tvHoKhau;
    @FXML
    private TableColumn colMaHo;
    @FXML
    private TableColumn colMaChuHo;
    @FXML
    private TableColumn colGhiChu;

    private ObservableList<HoKhau> listValueTableView;
    private List<HoKhau> listHoKhau;

    // Event Listener on Button[#tfTachKhau].onAction
    //render ra view Tách Khẩu, ủy quyền tách khẩu cho view này
    @FXML
    public void tachKhau(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        Parent home = FXMLLoader.load(getClass().getResource("/view/HoKhau/TachKhau.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(home,600,400));
        stage.setResizable(false);
        stage.showAndWait();
        showHoKhau();
    }
    // Event Listener on Button.onAction
    //render ra view đổi chủ hooj, ủy quyền
    @FXML
    public void doiChuHo(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        Parent home = FXMLLoader.load(getClass().getResource("/view/HoKhau/DoiChuHo.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(home,600,400));
        stage.setResizable(false);
        stage.showAndWait();
        showHoKhau();
    }

    //render ra view chuyển hộ, ủy quyền
    @FXML
    public void chuyenHo(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        Parent home = FXMLLoader.load(getClass().getResource("/view/HoKhau/DoiHoKhau.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(home,600,400));
        stage.setResizable(false);
        stage.showAndWait();
        showHoKhau();
    }

    public void showHoKhau() throws ClassNotFoundException, SQLException {
        //lấy danh sách hộ từ database thông qua class service
        listHoKhau = new HoKhauService().getListHoKhau();

        listValueTableView = FXCollections.observableArrayList(listHoKhau);
        // thiet lap cac cot cho tableviews
        colMaHo.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("maHo"));
        colMaChuHo.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("maChuHo"));
        tvHoKhau.setItems(listValueTableView);
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            showHoKhau();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
