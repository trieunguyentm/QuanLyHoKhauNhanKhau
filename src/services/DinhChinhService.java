package services;

import controller.ControllerLogin;
import main.DataBaseConnection;
import model.DinhChinh;
import model.HoKhau;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DinhChinhService {
    public List<DinhChinh> getListDinhChinh(String maHo) throws SQLException {
        List<DinhChinh> list = new ArrayList<>();
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        String query = "select * from dinh_chinh where maHo= '"+maHo+"'";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            DinhChinh dinhChinh = new DinhChinh(
                    rs.getString("maHo"),
                    rs.getString("thongTinThayDoi"),
                    rs.getString("thayDoiTu"),
                    rs.getString("thayDoiThanh"),
                    rs.getString("ngayThayDoi"),
                    rs.getString("nguoiThayDoi"));
            list.add(dinhChinh);
        }
        preparedStatement.close();
        connection.close();
        return list;
    }
}
