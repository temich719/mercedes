package dao;

import dao.entity.AbstractCar;
import dao.entity.Pair;
import dao.entity.User;
import dao.entity.UserDTO;
import dao.exception.DAOException;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface UserDAO {
    /**
     * adds user to database
     * @param user is a user
     * @throws DAOException is a module exception
     */
    void insertUser(User user) throws DAOException;

    /**
     * finds all users
     * @return set of all registered users
     * @throws DAOException is a module exception
     */
    ResultSet getResultSetOfAllUsers() throws DAOException;

    /**
     * updates password
     * @param email is email of user who needs to update password
     * @param password is a new password
     * @throws DAOException is a module exception
     */
    void updatePassword(String email, String password) throws DAOException;

    /**
     * finds name and surname of user
     * @param email is email of user
     * @return pair of name and surname
     * @throws DAOException is a module exception
     */
    Pair getNameAndSurname(String email) throws DAOException;

    /**
     * finds email and password
     * @return list of emails and passwords
     * @throws DAOException is a module exception
     */
    ArrayList<Pair> selectDataForEnter() throws DAOException;

    /**
     * checks if email exists
     * @param email is email
     * @return true if exists and false if not
     * @throws DAOException is a module exception
     */
    boolean isExistingEmail(String email) throws DAOException;

    /**
     * finds avatar by email
     * @param email is email
     * @return avatar path according to email
     * @throws DAOException is a module exception
     */
    String getAvatarPathByEmail(String email)throws DAOException;

    /**
     * finds all registrated users
     * @return list of registrated users
     * @throws DAOException is a module exception
     */
    ArrayList<UserDTO> getListOfUsers()throws DAOException;

    /**
     * deletes user
     * @param user is a registrated user
     * @throws DAOException is a module exception
     */
    void deleteUser(User user)throws DAOException;

    /**
     * updates avatar into user account
     * @param imagePath is a path of image
     * @param email is an email of user
     * @throws DAOException is a module exception
     */
    void addAvatar(String imagePath, String email)throws DAOException;

    /**
     * log-in
     * @param email is the email of user
     * @param password is the user's password
     * @return UserDTO if log-in was successful and null if not
     * @throws DAOException is a module exception
     */
    UserDTO enter(String email, String password)throws DAOException;

    void upgradeUserToAdmin(int userId)throws DAOException;

    ArrayList<String> getCountOfUserPages() throws DAOException;

    ArrayList<UserDTO> getUsersInfoForOnePage(String pageNumber) throws DAOException;
}
