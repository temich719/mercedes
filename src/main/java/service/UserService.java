package service;

import dao.entity.Pair;
import dao.entity.User;
import service.exception.ServiceException;

import java.util.ArrayList;

public interface UserService {
    String getAvatarPathByEmail(String email)throws ServiceException;
    void addAvatar(String avatarPath, String email)throws ServiceException;
    void registration(User user)throws ServiceException;
    ArrayList<Pair> getEmailAndPassword()throws ServiceException;
    void updatePassword(String email, String password)throws ServiceException;
    boolean isExistingEmail(String email)throws ServiceException;
    Pair getName(String email)throws ServiceException;
}
