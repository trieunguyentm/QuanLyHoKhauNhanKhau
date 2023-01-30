package services;

import main.DataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class CRUD {
    //phục vụ refactor code, hiện chưa cần quan tâm
    public boolean add(Object obj) throws SQLException {
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        return false;
    }
    public boolean delete(Object obj) throws SQLException {
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        return false;
    }
    public boolean update(Object obj){
        DataBaseConnection connectionToDB = new DataBaseConnection();
        Connection connection = connectionToDB.getConnection(null, null);
        return false;
    }

}
