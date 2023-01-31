package services;

import main.DataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
    //không cần quan tâm, dùng để tạo sẵn câu lệnh để điền vào bảng dinh_chinh
    public static void getHistoryQuery(String maHo, String typeOfChange, String from, String to, String nguoiThayDoi) throws SQLException {
        //connection to db
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        //get date
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);
        //query
        String historyQuery = "insert into dinh_chinh values('" + maHo + "','" + typeOfChange + "','" + from + "','" + to + "','" + date + "'," + "'" + nguoiThayDoi + "')";
        Statement stm = connection.createStatement();
        stm.executeUpdate(historyQuery);
        connection.close();
    }

    //check if str2 contains elements of str1
    public static boolean checkTwoStringButNotLcs(String str1, String str2) {
        int indexOfPrevChar = 0;//previous element's posistion of str1 that we found in str2
        outerlopp:
        for (int i = 0; i < str1.length(); i++) {
            for (int j = indexOfPrevChar; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    indexOfPrevChar = j;
                    if (i == str1.length() - 1) return true;
                    break;
                } else if (j == str2.length() - 1) return false;
            }
        }
        return false;
    }
}
