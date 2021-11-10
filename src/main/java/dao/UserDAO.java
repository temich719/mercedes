package dao;

import dao.entity.Pair;
import dao.entity.User;
import dao.exception.DAOException;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface UserDAO {
    /**
     * adds user to database
     * @param user is a user
     * @throws DAOException is module exception
     */
    void insertUser(User user) throws DAOException;

    /**
     * finds all users
     * @return set of all registered users
     * @throws DAOException is module exception
     */
    ResultSet getResultSetOfAllUsers() throws DAOException;

    /**
     * updates password
     * @param email is email of user who needs to update password
     * @param password is a new password
     * @throws DAOException is module exception
     */
    void updatePassword(String email, String password) throws DAOException;

    /**
     * finds name and surname of user
     * @param email is email of user
     * @return pair of name and surname
     * @throws DAOException is module exception
     */
    Pair getName(String email) throws DAOException;

    /**
     * finds email and password
     * @return list of emails and passwords
     * @throws DAOException is module exception
     */
    ArrayList<Pair> selectDataForEnter() throws DAOException;

    /**
     * checks if email exists
     * @param email is email
     * @return true if exists and false if not
     * @throws DAOException is module exception
     */
    boolean isExistingEmail(String email) throws DAOException;

    /**
     * finds avatar by email
     * @param email is email
     * @return avatar path according to email
     * @throws DAOException is module exception
     */
    String getAvatarPathByEmail(String email)throws DAOException;
}
