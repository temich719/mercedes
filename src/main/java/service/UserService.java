package service;

import dao.entity.Pair;
import dao.entity.User;
import dao.entity.UserDTO;
import dao.exception.DAOException;
import service.exception.ServiceException;

import java.util.ArrayList;

public interface UserService {
    /**
     * returns avatar according to email
     * @param email is an email
     * @return avatar
     * @throws ServiceException is a module exception
     */
    String getAvatarPathByEmail(String email)throws ServiceException;

    /**
     * adds avatar according to email
     * @param avatarPath is a path of image of avatar
     * @param email is an email
     * @throws ServiceException is a module exception
     */
    void addAvatar(String avatarPath, String email)throws ServiceException;

    /**
     * register a new user
     * @param user is the user who will be registered
     * @throws ServiceException is a module exception
     */
    void register(User user)throws ServiceException;

    /**
     * finds emails and pairs
     * @return list of pairs of emails and passwords
     * @throws ServiceException is a module exception
     */
    ArrayList<Pair> getEmailAndPassword()throws ServiceException;

    /**
     * updates password according to email
     * @param email is an email
     * @param password is a new password
     * @throws ServiceException is a module exception
     */
    void updatePassword(String email, String password)throws ServiceException;

    /**
     * checks if email exists
     * @param email is an email that will be checked
     * @return true if email exists and false if not
     * @throws ServiceException is a module exception
     */
    boolean isExistingEmail(String email)throws ServiceException;

    /**
     * returns name and surname
     * @param email is an email according to which finds name and surname
     * @return pair of name and surname
     * @throws ServiceException is a module exception
     */
    Pair getNameAndSurname(String email)throws ServiceException;

    /**
     * finds list of registered users
     * @return list of registered users
     * @throws ServiceException is a module exception
     */
    ArrayList<UserDTO> getListOfUsers() throws ServiceException;

    /**
     * deletes user
     * @param user is a registrated user
     * @throws ServiceException is a module exception
     */
    void deleteUser(User user)throws ServiceException;

    /**
     * log-in
     * @param email is the email of user
     * @param password is the user's password
     * @return UserDTO if log-in was successful and null if not
     * @throws ServiceException is a module exception
     */
    UserDTO enter(String email, String password)throws ServiceException;

    void upgradeUserToAdmin(int userId)throws ServiceException;
}
