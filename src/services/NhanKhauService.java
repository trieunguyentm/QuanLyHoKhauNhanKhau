package services;

import controller.ControllerLogin;
import main.DataBaseConnection;
import model.KhaiTu;
import model.NhanKhau;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanKhauService{

    public String getInfOfHomeOwner(String maHo) throws SQLException {
        //get connection
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);

        String ngaySinh = null;
        String getMaHoQuery = "select ngaySinh from nhan_khau where maHo = " + "'" + maHo+"'" + "and quanHeVoiChuHo = N'chủ hộ'";
        PreparedStatement stmGetNgaySinh = connection.prepareStatement(getMaHoQuery);
        ResultSet rs = stmGetNgaySinh.executeQuery();
        while (rs.next()) {
            ngaySinh = rs.getString("ngaySinh");
        }
        stmGetNgaySinh.close();
        connection.close();
        return ngaySinh;
    }
    // phương thức phục vụ mục đích thêm người vào bảng nhan_khau
    public void add(NhanKhau nhanKhauModel) throws SQLException {
        //get connection
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
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
    }
    //phuongw thúc để thao tác với case báo tử
	public void baoTu(KhaiTu baotu) throws SQLException {
        //get connection
        Connection connection = new DataBaseConnection().getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
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
		String query2 = "update nhan_khau set ghiChu = 'da chet' where maNhanKhau = " + baotu.getMaNguoiChet() ;
		Statement stm = connection.createStatement();
		stm.executeUpdate(query2);

        //get ma ho cua nguoi bi chết để điền vào bảng dinh_chinh
        String maHo = null;
        String getMaHoQuery = "select maHo from nhan_khau where maNhanKhau = " + "'" + baotu.getMaNguoiChet()+"'";
        PreparedStatement stmGetMaHo = connection.prepareStatement(getMaHoQuery);
        ResultSet rs = stmGetMaHo.executeQuery();
        while (rs.next()) {
            maHo = rs.getString("maHo");
        }
        preparedStatement.close();
        //sau khi có được mã hộ của người chết, cập nhật bảng dinh_chinh để lưu lịch sử thay đổi
        Util.getHistoryQuery(maHo, "bao tu", "khong", "da chet", baotu.getMaNguoiChet());
		connection.close();
    }
    //cập nhật thông tin cho bảng nhân khẩu
    public void update(String maNhanKhau, String quanHe, String maHo, String hoTen) throws SQLException {
        //connect to db
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        //query cập  nhật quan hệ
        String query = "UPDATE nhan_khau " + "set quanHeVoiChuHo =" + "N'" + quanHe + "', maHo = " +   "'" + maHo + "',hoTen =  " + "N'"+ hoTen + "' where maNhanKhau =" + "'"+ maNhanKhau +"'";
        Statement stm = connection.createStatement();
        stm.executeUpdate(query);
        connection.close();
    }
    //lấy danh sách dữ liệu là một list object của đối tượng NhanKhau
    public static List<NhanKhau> getListNhanKhau() throws SQLException {
        List<NhanKhau> list = new ArrayList<>();
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        String query = "SELECT * FROM nhan_khau";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
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
