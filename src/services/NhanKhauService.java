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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NhanKhauService{

    // phương thức phục vụ mục đích thêm người vào bảng nhan_khau
    public boolean add(NhanKhau nhanKhauModel) throws ClassNotFoundException, SQLException {
        //get connection
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        //query như trong thực hành SQL, thêm người vào bảng nhan_khau
        String query = "insert into nhan_khau(maNhanKhau, hoTen, gioiTinh, ngaySinh, queQuan, ngheNghiep, maHo, quanHeVoiChuHo)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
        //mấy cái lằng nhà lằng nhằng để cho câu lệnh trên được thực hiện vào database (nó sẽ làm câu lệnh thực hiện giống việc chạy sqlserver managerment studio
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

        //thêm người xong thì cập nhật lịch sử thay đổi ở bảng dinh_chinh, loại thay đổi là 'them nguoi'
        Util.getHistoryQuery(nhanKhauModel.getMaHo(), "them nguoi", "khong", nhanKhauModel.getMaHo(), nhanKhauModel.getMaNhanKhau());
        connection.close();
        return true;
    }
    //phuongw thúc để thao tác với case báo tử
	public boolean baoTu(KhaiTu baotu) throws SQLException, ClassNotFoundException {
        //get connection
        Connection connection = new DataBaseConnection().getConnection(null, null);
        //query như trong thực hành SQL, thêm người chết vào bảng khai_tu
		String query = "INSERT INTO khai_tu( maNguoiKhai, maNguoiChet, ngayKhai, lyDoChet)" + " values ( ?, ?, ?, ?)";
        //mấy cái lằng nhà lằng nhằng để cho câu lệnh trên được thực hiện vào database (nó sẽ làm câu lệnh thực hiện giống việc chạy sqlserver managerment studio
		PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, baotu.getMaNguoiKhai());
		preparedStatement.setString(2, baotu.getMaNguoiChet());
		preparedStatement.setString(3, baotu.getDate());
		preparedStatement.setString(4, baotu.getLyDoChet());
		preparedStatement.executeUpdate();
		preparedStatement.close();
        //sau khi báo tử, cần cập nhật phần ghiChu trong bảng nhan_khau của người bị chết thành 'đa chetA'
		String query2 = "update nhan_khau set ghiChu = N'đã chết' where maNhanKhau = " + baotu.getMaNguoiChet() ;
		Statement stm = connection.createStatement();
		stm.executeUpdate(query2);

        //get ma ho cua nguoi bi chết để điền vào bảng dinh_chinh
        String maHo = null;
        String getMaHoQuery = "select maHo from nhan_khau where maNhanKhau = " + "'" + baotu.getMaNguoiChet()+"'";
        PreparedStatement stmGetMaHo = (PreparedStatement) connection.prepareStatement(getMaHoQuery);
        ResultSet rs = stmGetMaHo.executeQuery();
        while (rs.next()) {
            maHo = rs.getString("maHo");
        }
        preparedStatement.close();
        //sau khi có được mã hộ của người chết, cập nhật bảng dinh_chinh để lưu lịch sử thay đổi
        Util.getHistoryQuery(maHo, "báo tử", "khong", "da chet", baotu.getMaNguoiChet());
		connection.close();
		return true;
	}
    //cập nhật thông tin cho bảng nhân khẩu
    public boolean update(String maNhanKhau, String quanHe, String maHo, String hoTen) throws ClassNotFoundException, SQLException {
        //connect to db
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        //query cập  nhật quan hệ
        String query = "UPDATE nhan_khau " + "set quanHeVoiChuHo =" + "N'" + quanHe + "', maHo = " +   "'" + maHo + "',hoTen =  " + "N'"+ hoTen + "' where maNhanKhau =" + "'"+ maNhanKhau +"'";
        Statement stm = connection.createStatement();
        stm.executeUpdate(query);
        connection.close();
        return true;
    }
    //lấy danh sách dữ liệu là một list object của đối tượng NhanKhau
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
