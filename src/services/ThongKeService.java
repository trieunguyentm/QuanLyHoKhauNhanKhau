package services;

import controller.ControllerLogin;
import main.DataBaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ThongKeService {
    public int demSoLuongHoKhau() throws SQLException {
        //Kết nối DB
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        //Tạo stament thực thi lệnh
        Statement statement = connection.createStatement();
        String query = "select count(1) from ho_khau";
        //Thực thi lệnh
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        int answer = resultSet.getInt(1);
        //Ngắt kết nối
        statement.close();
        connection.close();
        //trả về
        return answer;
    }
    public int demSoLuongNhanKhau() throws SQLException {
        //Kết nối DB
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        //Tạo stament thực thi lệnh
        Statement statement = connection.createStatement();
        String query = "select count(1) from nhan_khau";
        //Thực thi lệnh
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        int answer = resultSet.getInt(1);
        //Ngắt kết nối
        statement.close();
        connection.close();
        //trả về
        return answer;
    }
    public int demSoLuongKhoanThu() throws SQLException {
        //Kết nối DB
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        //Tạo stament thực thi lệnh
        Statement statement = connection.createStatement();
        String query = "select count(1) from khoan_thu";
        //Thực thi lệnh
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        int answer = resultSet.getInt(1);
        //Ngắt kết nối
        statement.close();
        connection.close();
        //trả về
        return answer;
    }
    public int demSoLuongNhanKhauTheoGioiTinh(String gioiTinh) throws SQLException {
        //Kết nối DB
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        //Tạo stament thực thi lệnh
        Statement statement = connection.createStatement();
        String query = "select count(1) from nhan_khau where gioitinh = '" + gioiTinh + "'";
        //Thực thi lệnh
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        int answer = resultSet.getInt(1);
        //Ngắt kết nối
        statement.close();
        connection.close();
        //trả về
        return answer;
    }
}
