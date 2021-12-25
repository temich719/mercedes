package dao.impl;

import dao.AbstractDAO;
import dao.ConnectionPool;
import dao.UserDAO;
import dao.entity.*;
import dao.exception.DAOException;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import static dao.DAOFinalsStorage.*;

public class UserDAOImpl extends AbstractDAO implements UserDAO {

    private static final String DELETE_ORDER_OF_DELETED_USER = "delete from orders where user_name = ? && user_surname = ? && email = ?;";
    private static final String SELECT_FROM_NAMES = "select * from names;";
    private static final String SELECT_FROM_SURNAMES = "select * from surnames;";
    private static final String SELECT_FROM_ACCESS_TYPES = "select * from access_types;";
    private static final String SELECT_FROM_USERS = "select id, user_name, user_surname, access_type, email from users;";
    private static final String SELECT_USER_EMAILS = "select email from users";
    private static final String LOGIN_QUERY = "select user_name, user_surname, email, access_type from users where email = ? && password = ?;";
    private static final String ADD_AVATAR = "update users set avatar = ? where email = ?;";
    private static final String ADD_USER_NAME = "insert into names(user_name) values(?);";
    private static final String ADD_SURNAME = "insert into surnames(user_surname) values(?);";
    private static final String ADD_ACCESS_TYPE = "insert into access_types(access_type) values(?);";
    private static final String ADD_USER = "insert into users(user_name,user_surname,email,password,access_type) values(?, ?, ?, ?, ?);";
    private static final String UPDATE_PASSWORD = "update users set password = ? where email = ?;";
    private static final String GET_NAME_BY_EMAIL = "select user_name from users where email = ?";
    private static final String GET_SURNAME_BY_EMAIL = "select user_surname from users where email = ?";
    private static final String GET_AVATAR_BY_IMAGE = "select avatar from users where email = ?;";
    private static final String DELETE_USER = "delete from users where user_name = ? && user_surname = ? && email = ? && access_type = ?;";
    private static final String UPDATE_ACCESS_TYPE = "update users set access_type = ? where id = ?;";
    private static final String SELECT_COUNT_OF_USERS = "select count(*) from users;";
    private static final String SELECT_USER_INFO_FOR_ONE_PAGE = "select id, user_name, user_surname, email, access_type from users order by id limit ? offset ?;";
    private static final String SELECT_ACCESS_TYPE_BY_EMAIL = "select access_type from users where email = ?;";
    private static final int LIMIT = 10;

    public UserDAOImpl(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    public void addAvatar(String avatarPath, String email) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(ADD_AVATAR);
            preparedStatement.setString(1, avatarPath);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(preparedStatement);
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public UserDTO enter(String email, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserDTO userDTO = null;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(LOGIN_QUERY);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userDTO = new UserDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, preparedStatement);
            connectionPool.retrieve(connection);
        }
        return userDTO;
    }

    @Override
    public void insertUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement nameStatement = null;
        PreparedStatement surnameStatement = null;
        PreparedStatement accessStatement = null;
        PreparedStatement addStatement = null;
        try {
            connection = connectionPool.provide();
            connection.setAutoCommit(false);
            final String access = REGISTERED;
            if (!isAlreadyExistsInDatabase(user.getName(), SELECT_FROM_NAMES, connection)) {
                nameStatement = connection.prepareStatement(ADD_USER_NAME);
                nameStatement.setString(1, user.getName());
                nameStatement.executeUpdate();
            }
            if (!isAlreadyExistsInDatabase(user.getSurname(), SELECT_FROM_SURNAMES, connection)) {
                surnameStatement = connection.prepareStatement(ADD_SURNAME);
                surnameStatement.setString(1, user.getSurname());
                surnameStatement.executeUpdate();
            }
            if (!isAlreadyExistsInDatabase(access, SELECT_FROM_ACCESS_TYPES, connection)) {
                accessStatement = connection.prepareStatement(ADD_ACCESS_TYPE);
                accessStatement.setString(1, access);
                accessStatement.executeUpdate();
            }
            addStatement = connection.prepareStatement(ADD_USER);
            addStatement.setString(1, user.getName());
            addStatement.setString(2, user.getSurname());
            addStatement.setString(3, user.getEmail());
            addStatement.setString(4, user.getPassword());
            addStatement.setString(5, user.getAccessType());
            addStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(addStatement, accessStatement, surnameStatement, nameStatement);
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public void updatePassword(String email, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(UPDATE_PASSWORD);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(preparedStatement);
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public String getUserNameByEmail(String email) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String name;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(GET_NAME_BY_EMAIL);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            name = resultSet.getString(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, preparedStatement);
            connectionPool.retrieve(connection);
        }
        return name;
    }

    @Override
    public String getUserSurnameByEmail(String email) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String surname;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(GET_SURNAME_BY_EMAIL);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            surname = resultSet.getString(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, preparedStatement);
            connectionPool.retrieve(connection);
        }
        return surname;
    }

    @Override
    public Page<UserDTO> getPageOfUsers(String pageNumber) throws DAOException {
        Connection connection = null;
        PreparedStatement countStatement = null;
        ResultSet countSet = null;
        List<String> pageNumbers = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<UserDTO> users = new ArrayList<>();
        Page<UserDTO> page = new Page<>();
        int offset = (Integer.parseInt(pageNumber) - 1) * LIMIT;
        int count;
        int countOfPages;
        try {
            connection = connectionPool.provide();
            connection.setAutoCommit(false);
            countStatement = connection.prepareStatement(SELECT_COUNT_OF_USERS);
            countSet = countStatement.executeQuery();
            countSet.next();
            count = countSet.getInt(1);
            countOfPages = (int) Math.ceil((double) count / LIMIT);
            for (int i = 1; i <= countOfPages; i++) {
                pageNumbers.add(Integer.toString(i));
            }
            if (pageNumbers.size() == 1) {
                pageNumbers.clear();
            }
            preparedStatement = connection.prepareStatement(SELECT_USER_INFO_FOR_ONE_PAGE);
            preparedStatement.setInt(1, LIMIT);
            preparedStatement.setInt(2, offset);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserDTO userDTO = new UserDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(5), resultSet.getString(4));
                users.add(userDTO);
            }
            connection.commit();
            page.setElements(users);
            page.setCountOfPages(pageNumbers);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, preparedStatement, countSet, countStatement);
            connectionPool.retrieve(connection);
        }
        return page;
    }

    @Override
    public boolean isExistingEmail(String email) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean isExist = false;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(SELECT_USER_EMAILS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (email.equals(resultSet.getString(1))) {
                    isExist = true;
                    break;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            connectionPool.retrieve(connection);
        }
        return isExist;
    }

    @Override
    public String getAvatarPathByEmail(String email) throws DAOException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String result;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(GET_AVATAR_BY_IMAGE);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getString(1);
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            connectionPool.retrieve(connection);
        }
        return result;
    }

    @Override
    public List<UserDTO> getListOfUsers() throws DAOException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        List<UserDTO> users = new ArrayList<>();
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(SELECT_FROM_USERS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserDTO userDTO = new UserDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5));
                users.add(userDTO);
            }
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            connectionPool.retrieve(connection);
        }
        return users;
    }

    @Override
    public String getUserAccessTypeByEmail(String email) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String accessType;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(SELECT_ACCESS_TYPE_BY_EMAIL);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            accessType = resultSet.getString(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, preparedStatement);
            connectionPool.retrieve(connection);
        }
        return accessType;
    }

    @Override
    public void upgradeUserToAdmin(int userId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(UPDATE_ACCESS_TYPE);
            preparedStatement.setString(1, ADMIN);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(preparedStatement);
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public void deleteUser(UserDTO userDTO) throws DAOException {
        Connection connection = null;
        PreparedStatement userStatement = null;
        PreparedStatement orderStatement = null;
        try {
            connection = connectionPool.provide();
            connection.setAutoCommit(false);
            userStatement = connection.prepareStatement(DELETE_USER);
            userStatement.setString(1, userDTO.getName());
            userStatement.setString(2, userDTO.getSurname());
            userStatement.setString(3, userDTO.getEmail());
            userStatement.setString(4, userDTO.getAccessType());
            userStatement.executeUpdate();
            orderStatement = connection.prepareStatement(DELETE_ORDER_OF_DELETED_USER);
            orderStatement.setString(1, userDTO.getName());
            orderStatement.setString(2, userDTO.getSurname());
            orderStatement.setString(3, userDTO.getEmail());
            orderStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(userStatement, orderStatement);
            connectionPool.retrieve(connection);
        }
    }
}
