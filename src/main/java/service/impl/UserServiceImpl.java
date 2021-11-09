package service.impl;

import dao.UserDAO;
import dao.daoFactory.DaoFactory;
import dao.entity.Pair;
import dao.entity.User;
import dao.exception.DAOException;
import dao.impl.UserDAOImpl;
import service.UserService;
import service.exception.ServiceException;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = DaoFactory.getINSTANCE().getUserDAOTime();

    public UserServiceImpl(){}

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
            UserDAOImpl.addAvatar(avatarPath, email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void registration(User user) throws ServiceException {
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
    public Pair getName(String email) throws ServiceException {
        try {
            return userDAO.getName(email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
