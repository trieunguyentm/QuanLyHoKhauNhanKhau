package services;

import main.DataBaseConnection;
import model.HoKhau;
import model.NhanKhau;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoKhauService {

    //thao tác chuyển hộ khẩu trong database
    public boolean chuyenHoKhau(String maHoCu, String maHoMoi, String maNhanKhau) throws SQLException {
        //get connection
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        //câu lệnh sql để update mã hộ
        String query = "UPDATE nhan_khau " + "set maHo = " + "'" +maHoMoi + "'" + "where maNhanKhau =" + "'" + maNhanKhau +"'";
        //thực thi câu lệnh trên
        Statement stm = connection.createStatement();
        stm.executeUpdate(query);
        //cap nhat bảng dinh_chinh với mã hộ laf mã ho cu - chuyển hộ tương ứng 1 người ra đi
        Util.getHistoryQuery(maHoCu, "chuyen di", maHoCu, maHoMoi, maNhanKhau);
        //cap nhat bang lich su cho ho moi
        Util.getHistoryQuery(maHoMoi, "chuyen den", maHoCu, maHoMoi, maNhanKhau);
        connection.close();
        return false;
    }
    //thao tác tách hộ khẩu với database
    public boolean tachHoKhau(HoKhau hoKhau, String maHoCu) throws SQLException {
        //connect to DB
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        //câu lệnh đê tạo hộ mới
        String query = "insert into ho_khau(maHo, maChuHo)" + " values (?, ?)";
        //thực thi câu lệnh trên
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, hoKhau.getMaHo());
        preparedStatement.setString(2, hoKhau.getMaChuHo());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        //cap nhat ma ho cua chu ho khau moi
        String query3 = "UPDATE nhan_khau " + "set maHo = " + "'" +hoKhau.getMaHo() + "', quanHeVoiChuHo = 'chuHo' ," +"ghiChu = 'can xac minh quan he cua ho nay' " + "where maNhanKhau =" + "'" + hoKhau.getMaChuHo()+"'";
        Statement stm = connection.createStatement();
        stm.executeUpdate(query3);
        //ghi nhan lich su ho moi ở bảng ghi_nhan
        Util.getHistoryQuery(hoKhau.getMaHo(), "lap ho khau", "khong", hoKhau.getMaHo(), hoKhau.getMaChuHo());
        //ghi nhan việc người rowif đi ở hộ cũ
        Util.getHistoryQuery(maHoCu, "chuyen di", maHoCu, hoKhau.getMaHo(), hoKhau.getMaChuHo());

        connection.close();
        return true;
    }
    //thao tác sửa chủ hộ với db
    public boolean suaChuHo(String maHo, String maChuCu, String maChuMoi) throws SQLException {
        //get connection
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        //lập query chỉnh sửa hộ
        String query = "UPDATE ho_khau " + "set maChuHo =" + "'" + maChuMoi + "'" + " where maHo =" + "'"+ maHo +"'";
        //thực thi câu lệnh trên
        Statement stm = connection.createStatement();
        stm.executeUpdate(query);
        //cập nhật vai trò của chủ mới là chuHo
        String query2 = "UPDATE nhan_khau " + "set quanHeVoiChuHo = 'chuHo' " + ", ghiChu = 'can xac minh quan he cua ho nay' " + "where maNhanKhau =" + "'" +maChuMoi+"'";
        Statement stm2 = connection.createStatement();
        stm.executeUpdate(query2);
        //cập nhật vai trò của chủ cũ là chuHoCu
        String query3 = "UPDATE nhan_khau " + "set quanHeVoiChuHo = 'chuHoCu' " + "where maNhanKhau =" + "'" +maChuCu+"'";
        Statement stm3 = connection.createStatement();
        stm.executeUpdate(query3);

        //ghi nhận lịch sử thay đổi
        Util.getHistoryQuery(maHo, "doi chu ho", maChuCu, maChuMoi, maChuMoi);

        connection.close();
        return true;
    }

    //lấy ra danh sách các hộ từ database
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
