package dao;

import dao.entity.Pair;
import dao.entity.User;
import dao.exception.DAOException;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface UserDAO {
    void insertUser(User user) throws DAOException;
    ResultSet getResultSetOfAllUsers() throws DAOException;
    void updatePassword(String email, String password) throws DAOException;
    Pair getName(String email) throws DAOException;
    ArrayList<Pair> selectDataForEnter() throws DAOException;
    boolean isExistingEmail(String email) throws DAOException;
    String getAvatarPathByEmail(String email)throws DAOException;
}
