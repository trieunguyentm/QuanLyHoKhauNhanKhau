package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.HoKhau;
import model.NhanKhau;
import services.HoKhauService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerHoKhau implements Initializable {
    @FXML
    private TableView<HoKhau> tvHoKhau;
    @FXML
    private TableColumn<HoKhau, String> colMaHo;
    @FXML
    private TableColumn<NhanKhau, String> colMaChuHo;
    @FXML
    private Button btTachKhau;
    @FXML
    private Button btDoiChuHo;
    @FXML
    private Button btChuyenHo;


    // Event Listener on Button[#tfTachKhau].onAction
    //render ra view Tách Khẩu, ủy quyền tách khẩu cho view này
    @FXML
    public void tachKhau() throws IOException, SQLException, ClassNotFoundException {
        Parent home = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/HoKhau/TachKhau.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(home,600,400));
        stage.setResizable(false);
        //Khóa stage cha lại khi hiện stage con
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Tách hộ khẩu");
        stage.showAndWait();
        showHoKhau();
    }
    // Event Listener on Button.onAction
    //render ra view đổi chủ hooj, ủy quyền
    @FXML
    public void doiChuHo() throws SQLException, ClassNotFoundException, IOException {
        Parent home = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/HoKhau/DoiChuHo.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(home,600,400));
        stage.setResizable(false);
        //Khóa stage cha lại khi hiện stage con
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Đôi chủ hộ");
        stage.showAndWait();
        showHoKhau();
    }

    //render ra view chuyển hộ, ủy quyền
    @FXML
    public void chuyenHo() throws IOException, SQLException, ClassNotFoundException {
        Parent home = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/HoKhau/DoiHoKhau.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(home,600,400));
        stage.setResizable(false);
        //Khóa stage cha lại khi hiện stage con
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Đổi hộ khẩu");
        stage.showAndWait();
        showHoKhau();
    }

    public void showHoKhau() throws ClassNotFoundException, SQLException {
        //lấy danh sách hộ từ database thông qua class service
        List<HoKhau> listHoKhau = new HoKhauService().getListHoKhau();

        ObservableList<HoKhau> listValueTableView = FXCollections.observableArrayList(listHoKhau);
        // thiet lap cac cot cho tableviews
        colMaHo.setCellValueFactory(new PropertyValueFactory<>("maHo"));
        colMaChuHo.setCellValueFactory(new PropertyValueFactory<>("maChuHo"));
        tvHoKhau.setItems(listValueTableView);
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            showHoKhau();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //Hiệu ứng khi di chuyển chuột qua button
        btTachKhau.addEventHandler(MouseEvent.MOUSE_MOVED, event -> btTachKhau.setEffect(new SepiaTone()));
        btTachKhau.addEventHandler(MouseEvent.MOUSE_EXITED, event -> btTachKhau.setEffect(null));
        btDoiChuHo.addEventHandler(MouseEvent.MOUSE_MOVED, event -> btDoiChuHo.setEffect(new SepiaTone()));
        btDoiChuHo.addEventHandler(MouseEvent.MOUSE_EXITED, event -> btDoiChuHo.setEffect(null));
        btChuyenHo.addEventHandler(MouseEvent.MOUSE_MOVED, event -> btChuyenHo.setEffect(new SepiaTone()));
        btChuyenHo.addEventHandler(MouseEvent.MOUSE_EXITED, event -> btChuyenHo.setEffect(null));
    }
}
