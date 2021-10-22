package dao.database;

import dao.entity.Pair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO {
    void insertUser(String email, String password, String name, String surname) throws SQLException;
    ResultSet getResultSetOfAllUsers() throws SQLException;
    void updatePassword(String email, String password) throws SQLException;
    Pair getName(String email) throws SQLException;
    ArrayList<Pair> selectDataForEnter() throws SQLException;
    boolean isExistingEmail(String email) throws SQLException;
}