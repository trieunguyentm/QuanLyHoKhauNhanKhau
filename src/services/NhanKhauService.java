package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.DataBaseConnection;
import model.KhaiTu;
import model.NhanKhau;



public class NhanKhauService{

    public boolean add(NhanKhau nhanKhauModel) throws ClassNotFoundException, SQLException {
        //thieu nhieu field
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        String query = "insert into nhan_khau(maNhanKhau, hoTen, gioiTinh, ngaySinh, queQuan, ngheNghiep, maHo, quanHeVoiChuHo)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, nhanKhauModel.getMaNhanKhau());
        preparedStatement.setString(2, nhanKhauModel.getHoTen());
        preparedStatement.setString(3, nhanKhauModel.getGioiTinh());
        preparedStatement.setString(4, nhanKhauModel.getNgaySinh());
        preparedStatement.setString(5, nhanKhauModel.getQueQuan());
        preparedStatement.setString(6, nhanKhauModel.getNgheNghiep());
        preparedStatement.setString(7, nhanKhauModel.getMaHo());
        preparedStatement.setString(8, nhanKhauModel.getQuanHeVoiChuHo());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return true;
    }

	public boolean baoTu(KhaiTu baotu) throws SQLException, ClassNotFoundException {
        Connection connection = new DataBaseConnection().getConnection(null, null);
		String query = "INSERT INTO khai_tu( maNguoiKhai, maNguoiChet, ngayKhai, lyDoChet)" + " values ( ?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, baotu.getMaNguoiKhai());
		preparedStatement.setString(2, baotu.getMaNguoiChet());
		preparedStatement.setString(3, baotu.getDate());
		preparedStatement.setString(4, baotu.getLyDoChet());
		preparedStatement.executeUpdate();
		preparedStatement.close();
		String query2 = "update nhan_khau set ghiChu = 'da chet' where maNhanKhau = " + baotu.getMaNguoiChet() ;
		Statement stm = connection.createStatement();
		stm.executeUpdate(query2);
		connection.close();
		return true;
	}

    public boolean update(String maNhanKhau, String quanHe, String maHo, String hoTen) throws ClassNotFoundException, SQLException {
        //thieu nhieu field
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        String query = "UPDATE nhan_khau " + "set quanHeVoiChuHo =" + "N'" + quanHe + "', maHo = " +   "'" + maHo + "',hoTen =  " + "N'"+ hoTen + "' where maNhanKhau =" + "'"+ maNhanKhau +"'";
        System.out.println(query);
        Statement stm = connection.createStatement();
        stm.executeUpdate(query);
        connection.close();
        return true;
    }

    public List<NhanKhau> getListNhanKhau() throws ClassNotFoundException, SQLException {
        List<NhanKhau> list = new ArrayList<>();
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        String query = "SELECT * FROM nhan_khau";
        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            NhanKhau nhanKhau = new NhanKhau(rs.getString("maNhanKhau"), rs.getString("hoTen"), rs.getString("gioiTinh"), rs.getString("ngaySinh"), rs.getString("queQuan"), rs.getString("ngheNghiep"), rs.getString("maHo"), rs.getString("quanHeVoiChuHo"), rs.getString("ghiChu"));
            list.add(nhanKhau);
        }
        preparedStatement.close();
        connection.close();
        return list;
    }
}
