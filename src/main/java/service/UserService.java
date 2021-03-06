package service;

import dao.entity.Page;
import dao.entity.User;
import dao.entity.UserDTO;
import service.exception.ServiceException;

import java.util.List;

public interface UserService {
    /**
     * returns avatar according to email
     *
     * @param email is an email
     * @return avatar
     * @throws ServiceException is a module exception
     */
    String getAvatarPathByEmail(String email) throws ServiceException;

    /**
     * adds avatar according to email
     *
     * @param avatarPath is a path of image of avatar
     * @param email      is an email
     * @throws ServiceException is a module exception
     */
    void addAvatar(String avatarPath, String email) throws ServiceException;

    /**
     * register a new user
     *
     * @param user is the user who will be registered
     * @throws ServiceException is a module exception
     */
    void register(User user) throws ServiceException;

    /**
     * updates password according to email
     *
     * @param email    is an email
     * @param password is a new password
     * @throws ServiceException is a module exception
     */
    void updatePassword(String email, String password) throws ServiceException;

    /**
     * checks if email exists
     *
     * @param email is an email that will be checked
     * @return true if email exists and false if not
     * @throws ServiceException is a module exception
     */
    boolean isExistingEmail(String email) throws ServiceException;

    /**
     * finds list of registered users
     *
     * @return list of registered users
     * @throws ServiceException is a module exception
     */
    List<UserDTO> getListOfUsers() throws ServiceException;

    /**
     * deletes user
     *
     * @param userDTO is a registrated user
     * @throws ServiceException is a module exception
     */
    void deleteUser(UserDTO userDTO) throws ServiceException;

    /**
     * log-in
     *
     * @param email    is the email of user
     * @param password is the user's password
     * @return UserDTO if log-in was successful and null if not
     * @throws ServiceException is a module exception
     */
    UserDTO enter(String email, String password) throws ServiceException;

    /**
     * upgrades user access type from 'registered' to 'admin'
     *
     * @param userId is the user's id
     * @throws ServiceException is a module exception
     */
    void upgradeUserToAdmin(int userId) throws ServiceException;

    /**
     * finds user name according to his email
     *
     * @param email is the user's email
     * @return name of user that was registered with given email
     * @throws ServiceException is a module exception
     */
    String getUserNameByEmail(String email) throws ServiceException;

    /**
     * finds user surname according to his email
     *
     * @param email is the user's email
     * @return surname of user that was registered with given email
     * @throws ServiceException is a module exception
     */
    String getUserSurnameByEmail(String email) throws ServiceException;

    /**
     * finds access type of user according to his email
     *
     * @param email is the email of user
     * @return access type(registered or admin)
     * @throws ServiceException is a module exception
     */
    String getUserAccessTypeByEmail(String email) throws ServiceException;

    /**
     * finds data of user and page that we need to illustrate
     *
     * @param pageNumber is the number of page
     * @return object that contains data about page whose number is pageNumber
     * @throws ServiceException is a module exception
     */
    Page<UserDTO> getPageOfUsers(String pageNumber) throws ServiceException;
}
