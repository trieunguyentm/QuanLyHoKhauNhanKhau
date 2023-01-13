//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    public DataBaseConnection() {
    }

    public Connection getConnection(String userDataBase, String passwordDataBase) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException var6) {
            throw new RuntimeException(var6);
        }

        Connection conn;
        try {
            String urlLink = "jdbc:sqlserver://localhost:1433;databaseName=quanlynhankhau;user=" + userDataBase + ";password=" + passwordDataBase + ";encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(urlLink);
        } catch (SQLException var5) {
            throw new RuntimeException(var5);
        }

        if (conn == null) {
            System.out.println("KẾT NỐI VỚI DATABASE THẤT BẠI");
        }

        return conn;
    }
}
