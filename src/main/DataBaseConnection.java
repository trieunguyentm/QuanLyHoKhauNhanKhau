package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    public DataBaseConnection() {
        //Phuơng thức khởi tạo
    }
    public Connection getConnection(String userDataBase, String passwordDataBase)
    {
        //Try Class.forName
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //Tạo đối tượng Connection sẽ trả về
        Connection conn;
        //Kết nối với database qua user và password
        try{
            String urlLink = "jdbc:sqlserver://localhost:1433;databaseName=quanlynhankhau;user="+ userDataBase +";password="+ passwordDataBase +";encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(urlLink);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Kiểm tra kết nối, nếu conn = null thì kết nối thất bại
        if(conn == null)
        {
            System.out.println("KẾT NỐI VỚI DATABASE THẤT BẠI");
        }
        return conn;
    }
}
