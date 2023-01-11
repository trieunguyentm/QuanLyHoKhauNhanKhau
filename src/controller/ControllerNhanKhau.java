package controller;
import controller.NhanKhau.SuaNhanKhau;
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

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.HoKhau;
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
    //render ra màn hình của phần thêm nhân khẩu, ủy quyền chức năng tương ứng với mô hình MVC chứa view đó
    @FXML
    public void themNhanKhau(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        Parent home = FXMLLoader.load(getClass().getResource("/view/NhanKhau/themNhanKhau.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(home,575,566));
        stage.setResizable(false);
        stage.showAndWait();
        showNhanKhau();
    }
    // Event Listener on Button.onAction
    //render ra màn hình view báo tử, ủy quyền chức năng tương ứng với mô hình MVC chứa view đó
    @FXML
    public void baoTu(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        Parent home = FXMLLoader.load(getClass().getResource("/view/NhanKhau/baoTu.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(home,600,400));
        stage.setResizable(false);
        stage.showAndWait();
        showNhanKhau();
    }

    //render ra màn hình của phần sửa thông tin, ủy quyền chức năng tương ứng với mô hình MVC chứa view đó
    @FXML
    public void suaThongTin(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        //tao mot nhan khau object
        NhanKhau nhanKhauModel = (NhanKhau) tvNhanKhau.getSelectionModel().getSelectedItem();
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
        stage.showAndWait();
        showNhanKhau();
    }

    //set giá trị cho bảng tableview để nó render ra các data của bảng nhân khẩu
    public void showNhanKhau() throws ClassNotFoundException, SQLException {
        //get list nhân khẩu từ database
        listNhanKhau = new NhanKhauService().getListNhanKhau();
        //thuộc tính static để xem hiện tại có bao nhiêu người
        numberOfPeople = listNhanKhau.size();

        listValueTableView = FXCollections.observableArrayList(listNhanKhau);
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