package services;

import controller.ControllerLogin;
import main.DataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
    //không cần quan tâm, dùng để tạo sẵn câu lệnh để điền vào bảng dinh_chinh
    public static void getHistoryQuery(String maHo, String typeOfChange, String from, String to, String nguoiThayDoi ) throws SQLException {
        //connection to db
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(ControllerLogin.userDataBase, ControllerLogin.passworDataBase);
        //get date
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);
        //query
        String historyQuery = "insert into dinh_chinh values('" + maHo + "','" + typeOfChange + "','" + from + "','" + to + "','" + date + "'," + "'" + nguoiThayDoi +"')";
        Statement stm = connection.createStatement();
        stm.executeUpdate(historyQuery);
        connection.close();
    }
}
