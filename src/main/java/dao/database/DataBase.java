package dao.database;

import java.sql.SQLException;

public interface DataBase {
    void insert(String email, String password, String name, String surname) throws SQLException;
}