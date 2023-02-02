package services;

import controller.ControllerLogin;
import main.DataBaseConnection;

import java.sql.*;
import java.time.LocalDate;

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

    public int demSoLuongTamTru() throws SQLException{
        //Kết nối DB
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        //Tạo stament thực thi lệnh
        String query = "select count(1) from tam_tru where tuNgay < ? and denNgay > ?";
        Date date = Date.valueOf(LocalDate.now());
        //Thực thi lệnh
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDate(1, date);
        statement.setDate(2, date);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int answer = resultSet.getInt(1);
        //Ngắt kết nối
        statement.close();
        connection.close();
        //trả về
        System.out.println(answer);
        return answer;
    }
    public int demSoLuongTamVang() throws SQLException{
        //Kết nối DB
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        //Tạo stament thực thi lệnh
        String query = "select count(1) from tam_vang where tuNgay < ? and denNgay > ?";
        Date date = Date.valueOf(LocalDate.now());
        //Thực thi lệnh
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDate(1, date);
        statement.setDate(2, date);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int answer = resultSet.getInt(1);
        //Ngắt kết nối
        statement.close();
        connection.close();
        //trả về
        System.out.println(answer);
        return answer;
    }
    public int demSoLuongNhanKhauTheoGioiTinh(String gioiTinh) throws SQLException {
        //Kết nối DB
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        //Tạo stament thực thi lệnh
        Statement statement = connection.createStatement();
        String query = "select count(1) from nhan_khau where gioiTinh = N'" + gioiTinh + "'";
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

    public static int demSoLuongNhanKhauTheoThoiGian(int year) throws SQLException {
        //Kết nối DB
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        //Tạo stament thực thi lệnh
        Statement statement = connection.createStatement();
        String query = "select count(1) from nhan_khau where ngaySinh < '"+ year + "-01-01'";
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

    public int nguoiGiaNhat() throws SQLException {
        //Kết nối DB
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        //Tạo stament thực thi lệnh
        Statement statement = connection.createStatement();
        String query = "select top 1 from nhan_khau order by ngaySinh DESC ";
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
