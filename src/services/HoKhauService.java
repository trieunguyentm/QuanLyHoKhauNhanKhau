package services;

import main.DataBaseConnection;
import model.HoKhau;
import model.NhanKhau;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoKhauService {

    //method phu tro cho tach Ho Khau
    public boolean chuyenHoKhau(String maHoCu, String maHoMoi, String maNhanKhau) throws SQLException {
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        String query = "UPDATE nhan_khau " + "set maHo = " + "'" +maHoMoi + "'" + "where maNhanKhau =" + "'" + maNhanKhau +"'";
        Statement stm = connection.createStatement();
        stm.executeUpdate(query);
        //cap nhat bang lich su cho ho cu
        Util.getHistoryQuery(maHoCu, "chuyen di", maHoCu, maHoMoi, maNhanKhau);
        //cap nhat bang lich su cho ho moi
        Util.getHistoryQuery(maHoMoi, "chuyen den", maHoCu, maHoMoi, maNhanKhau);
        connection.close();
        return false;
    }
    public boolean tachHoKhau(HoKhau hoKhau, String maHoCu) throws SQLException {
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        String query = "insert into ho_khau(maHo, maChuHo)" + " values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, hoKhau.getMaHo());
        preparedStatement.setString(2, hoKhau.getMaChuHo());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        //cap nhat ma ho cua chu ho khau moi
        String query3 = "UPDATE nhan_khau " + "set maHo = " + "'" +hoKhau.getMaHo() + "', quanHeVoiChuHo = 'chuHo' " + "where maNhanKhau =" + "'" + hoKhau.getMaChuHo()+"'";
        Statement stm = connection.createStatement();
        stm.executeUpdate(query3);
        //ghi nhan lich su ho moi
        Util.getHistoryQuery(hoKhau.getMaHo(), "lap ho khau", "khong", hoKhau.getMaHo(), hoKhau.getMaChuHo());
        //ghi nhan chuyen nguoi cua ho cu
        Util.getHistoryQuery(maHoCu, "chuyen di", maHoCu, hoKhau.getMaHo(), hoKhau.getMaChuHo());

        connection.close();
        return true;
    }

    public boolean suaChuHo(String maHo, String maChuCu, String maChuMoi) throws SQLException {
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        String query = "UPDATE ho_khau " + "set maChuHo =" + "'" + maChuMoi + "'" + " where maHo =" + "'"+ maHo +"'";
        Statement stm = connection.createStatement();
        stm.executeUpdate(query);
        //cap nhat vai tro cho chu ho moi
        String query2 = "UPDATE nhan_khau " + "set quanHeVoiChuHo = 'chuHo' " + "where maNhanKhau =" + "'" +maChuMoi+"'";
        Statement stm2 = connection.createStatement();
        stm.executeUpdate(query2);
        //cap nhat vai tro cho chu ho cu
        String query3 = "UPDATE nhan_khau " + "set quanHeVoiChuHo = 'chuHoCu' " + "where maNhanKhau =" + "'" +maChuCu+"'";
        Statement stm3 = connection.createStatement();
        stm.executeUpdate(query3);
        //ghi nhan lich su sua chu ho
        Util.getHistoryQuery(maHo, "doi chu ho", maChuCu, maChuMoi, maChuMoi);

        connection.close();
        return true;
    }
    public List<HoKhau> getListHoKhau() throws SQLException {
        List<HoKhau> list = new ArrayList<>();
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        String query = "SELECT * FROM ho_khau";
        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            HoKhau hoKhau = new HoKhau(rs.getString("maHo"), rs.getString("maChuHo"));
            list.add(hoKhau);
        }
        preparedStatement.close();
        connection.close();
        return list;
    }
}
