package services;

import controller.ControllerLogin;
import main.DataBaseConnection;
import model.KhoanThu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhoanThuService {
    public List<KhoanThu> getListKhoanThu() throws SQLException {
        List<KhoanThu> list = new ArrayList<>();
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        String query = "SELECT * FROM khoan_thu";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            KhoanThu khoanThu = new KhoanThu(rs.getString("maKhoanThu"), rs.getString("tenKhoanThu"), rs.getString("soTienCanThu"), rs.getString("maHo"), rs.getString("ngayNop"));
            list.add(khoanThu);
        }
        preparedStatement.close();
        connection.close();
        return list;
    }
    public void update(String maKhoanThu, String tenKhoanThu, String soTienCanThu, String maHo, String ngayNop) throws SQLException {
        //connect to db
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        //query cập  nhật quan hệ
        String query = "UPDATE khoan_thu " + "set tenKhoanThu =" + "N'" + tenKhoanThu + "', soTienCanThu = " +   "'" + soTienCanThu + "',maHo =  " + "'"+ maHo + "',ngayNop =  '" + ngayNop + "' where maKhoanThu =" + "'"+ maKhoanThu +"'";
        System.out.println(query);
        Statement stm = connection.createStatement();
        stm.executeUpdate(query);
        connection.close();
    }
    public void add(KhoanThu khoanThu) throws SQLException {
        //connect to db
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        //query cập  nhật quan hệ
        String query = "INSERT INTO khoan_thu(maKhoanThu,tenKhoanThu,soTienCanThu, maHo, ngayNop) VALUES ('" + khoanThu.getMaKhoanThu() + "',N'" + khoanThu.getTenKhoanThu() + "','" + khoanThu.getSoTienCanThu() + "','" + khoanThu.getMaHo() + "','" + khoanThu.getNgayNop()+"')";
        System.out.println(query);
        Statement stm = connection.createStatement();
        stm.executeUpdate(query);
        connection.close();
    }
}
