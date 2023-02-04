package services;

import controller.ControllerLogin;
import main.DataBaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KhaiTuService {
    public static boolean daChet(String maNhanKhau) throws SQLException {
        //Kết nối DB
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        //Tạo stament thực thi lệnh
        Statement statement = connection.createStatement();
        String query = "select count(1) from khai_tu where maNguoiChet = '" + maNhanKhau + "'";
        //Thực thi lệnh
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        int answer = resultSet.getInt(1);
        //Ngắt kết nối
        statement.close();
        connection.close();
        //trả về
        return answer>0;
    }
}
