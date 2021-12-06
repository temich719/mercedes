package service.impl;

import dao.UserDAO;
import dao.daoFactory.DaoFactory;
import dao.entity.Pair;
import dao.entity.User;
import dao.entity.UserDTO;
import dao.exception.DAOException;
import service.UserService;
import service.exception.ServiceException;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = DaoFactory.getINSTANCE().getUserDAO();

    public UserServiceImpl() {
    }

    @Override
    public String getAvatarPathByEmail(String email) throws ServiceException {
        try {
            return userDAO.getAvatarPathByEmail(email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addAvatar(String avatarPath, String email) throws ServiceException {
        try {
            userDAO.addAvatar(avatarPath, email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserDTO enter(String email, String password) throws ServiceException {
        try {
            return userDAO.enter(email, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void register(User user) throws ServiceException {
        try {
            userDAO.insertUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<Pair> getEmailAndPassword() throws ServiceException {
        try {
            return userDAO.selectDataForEnter();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updatePassword(String email, String password) throws ServiceException {
        try {
            userDAO.updatePassword(email, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isExistingEmail(String email) throws ServiceException {
        try {
            return userDAO.isExistingEmail(email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Pair getNameAndSurname(String email) throws ServiceException {
        try {
            return userDAO.getNameAndSurname(email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<UserDTO> getListOfUsers() throws ServiceException {
        try {
            return userDAO.getListOfUsers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void upgradeUserToAdmin(int userId) throws ServiceException {
        try {
            userDAO.upgradeUserToAdmin(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteUser(User user) throws ServiceException {
        try {
            userDAO.deleteUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
