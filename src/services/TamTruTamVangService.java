package services;

import main.DataBaseConnection;
import model.NhanKhau;
import model.TamTru;
import model.TamVang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TamTruTamVangService {

    public boolean themTamTru(TamTru tamTru) throws SQLException {
        //get connection
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        //query như trong thực hành SQL, thêm người vào bảng nhan_khau
        String query = "insert into tam_tru(hoTen ,CMND,  soDienThoaiNguoiDangKy, tuNgay, denNgay, lyDo)" + " values (?, ?, ?, ?, ?, ?)";
        //mấy cái lằng nhà lằng nhằng để cho câu lệnh trên được thực hiện vào database (nó sẽ làm câu lệnh thực hiện giống việc chạy sqlserver managerment studio
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, tamTru.getHoTen());
        preparedStatement.setString(2, tamTru.getCMND());
        preparedStatement.setString(3, tamTru.getSoDienThoaiNguoiDangKy());
        preparedStatement.setString(4, tamTru.getTuNgay());
        preparedStatement.setString(5, tamTru.getDenNgay());
        preparedStatement.setString(6, tamTru.getLyDo());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return true;
    }

    public boolean themTamVang(TamVang tamVang) throws SQLException {
        //get connection
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        //query như trong thực hành SQL, thêm người vào bảng nhan_khau
        String query = "insert into tam_vang(maNhanKhau ,noiTamTru, tuNgay, denNgay, lyDo)" + " values (?, ?, ?, ?, ?)";
        //mấy cái lằng nhà lằng nhằng để cho câu lệnh trên được thực hiện vào database (nó sẽ làm câu lệnh thực hiện giống việc chạy sqlserver managerment studio
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, tamVang.getMaNhanKhau());
        preparedStatement.setString(2, tamVang.getNoiTamTru());
        preparedStatement.setString(3, tamVang.getTuNgay());
        preparedStatement.setString(4, tamVang.getDenNgay());
        preparedStatement.setString(5, tamVang.getLyDo());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return true;
    }

    public List<TamTru> getListTamTru() throws ClassNotFoundException, SQLException {
        List<TamTru> list = new ArrayList<>();
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        String query = "SELECT * FROM tam_tru";
        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            TamTru tamTru = new TamTru(rs.getInt("id"),rs.getString("hoTen"), rs.getString("CMND"), rs.getString("soDienThoaiNguoiDangKy"), rs.getString("tuNgay"), rs.getString("denNgay"), rs.getString("lyDo"));
            list.add(tamTru);
        }
        preparedStatement.close();
        connection.close();
        return list;
    }

    public List<TamVang> getListTamVang() throws ClassNotFoundException, SQLException {
        List<TamVang> list = new ArrayList<>();
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        String query = "SELECT * FROM tam_vang";
        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            TamVang tamVang = new TamVang(rs.getInt("id"),rs.getString("maNhanKhau"), rs.getString("noiTamTru"), rs.getString("tuNgay"), rs.getString("denNgay"), rs.getString("lyDo"));
            list.add(tamVang);
        }
        preparedStatement.close();
        connection.close();
        return list;
    }
}
