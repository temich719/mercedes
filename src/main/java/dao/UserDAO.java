package dao;

import dao.entity.User;
import dao.entity.UserDTO;
import dao.exception.DAOException;

import java.util.ArrayList;

public interface UserDAO {
    /**
     * adds user to database
     *
     * @param user is a user
     * @throws DAOException is a module exception
     */
    void insertUser(User user) throws DAOException;

    /**
     * updates password
     *
     * @param email    is email of user who needs to update password
     * @param password is a new password
     * @throws DAOException is a module exception
     */
    void updatePassword(String email, String password) throws DAOException;

    /**
     * checks if email exists
     *
     * @param email is email
     * @return true if exists and false if not
     * @throws DAOException is a module exception
     */
    boolean isExistingEmail(String email) throws DAOException;

    /**
     * finds avatar by email
     *
     * @param email is email
     * @return avatar path according to email
     * @throws DAOException is a module exception
     */
    String getAvatarPathByEmail(String email) throws DAOException;

    /**
     * finds all registrated users
     *
     * @return list of registrated users
     * @throws DAOException is a module exception
     */
    ArrayList<UserDTO> getListOfUsers() throws DAOException;

    /**
     * deletes user
     *
     * @param userDTO is a registrated user
     * @throws DAOException is a module exception
     */
    void deleteUser(UserDTO userDTO) throws DAOException;

    /**
     * updates avatar into user account
     *
     * @param imagePath is a path of image
     * @param email     is an email of user
     * @throws DAOException is a module exception
     */
    void addAvatar(String imagePath, String email) throws DAOException;

    /**
     * log-in
     *
     * @param email    is the email of user
     * @param password is the user's password
     * @return UserDTO if log-in was successful and null if not
     * @throws DAOException is a module exception
     */
    UserDTO enter(String email, String password) throws DAOException;

    /**
     * upgrades user access type from 'registered' to 'admin'
     *
     * @param userId is the user's id
     * @throws DAOException is a module exception
     */
    void upgradeUserToAdmin(int userId) throws DAOException;

    /**
     * calculates how much pages do we need to illustrate it in UI
     *
     * @return list of strings that has the size equals to amount of pages
     * @throws DAOException is a module exception
     */
    ArrayList<String> getCountOfUserPages() throws DAOException;

    /**
     * finds as much user information as can fit on one page
     *
     * @param pageNumber is the number of page that will be illustrated
     * @return list of objects that contains user information
     * @throws DAOException is a module exception
     */
    ArrayList<UserDTO> getUsersInfoForOnePage(String pageNumber) throws DAOException;

    /**
     * finds user name according to his email
     *
     * @param email is the user's email
     * @return name of user that was registered with given email
     * @throws DAOException is a module exception
     */
    String getUserNameByEmail(String email) throws DAOException;

    /**
     * finds user surname according to his email
     *
     * @param email is the user's email
     * @return surname of user that was registered with given email
     * @throws DAOException is a module exception
     */
    String getUserSurnameByEmail(String email) throws DAOException;

    /**
     * finds access type of user according to his email
     *
     * @param email is the email of user
     * @return access type(registered or admin)
     * @throws DAOException is a module exception
     */
    String getUserAccessTypeByEmail(String email) throws DAOException;
}
