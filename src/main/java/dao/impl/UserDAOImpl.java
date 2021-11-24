package dao.impl;

import dao.AbstractDAO;
import dao.ConnectionPool;
import dao.UserDAO;
import dao.entity.Pair;
import dao.entity.User;
import dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;

public class UserDAOImpl extends AbstractDAO implements UserDAO {

    private static final String SELECT_FROM_NAMES = "select * from names";
    private static final String SELECT_FROM_SURNAMES = "select * from surnames";
    private static final String SELECT_FROM_ACCESS_TYPES = "select * from access_types";
    private static final String SELECT_FROM_USERS = "select * from users";
    private static final String ADD_AVATAR = "update users set avatar = ? where email = ?;";
    private static final String ADD_USER_NAME = "insert into names(user_name) values(?);";
    private static final String ADD_SURNAME = "insert into surnames(user_surname) values(?);";
    private static final String ADD_ACCESS_TYPE = "insert into access_types(access_type) values(?);";
    private static final String ADD_USER = "insert into users(user_name,user_surname,email,password,access_type) values(?, ?, ?, ?, ?);";
    private static final String UPDATE_PASSWORD = "update users set password = ? where email = ?;";
    private static final String GET_NAME_AND_SURNAME = "select * from users where email = ?;";
    private static final String GET_AVATAR_BY_IMAGE = "select avatar from users where email = ?;";
    private static final String DELETE_USER = "delete from users where user_name = ? && user_surname = ? && email = ? && access_type = ?;";

    public UserDAOImpl(ConnectionPool connectionPool){
        super(connectionPool);
    }

    @Override
    public void addAvatar(String avatarPath, String email)throws DAOException{
        Connection connection = null;
        try {
            connection = connectionPool.provide();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(ADD_AVATAR);
            preparedStatement.setString(1, avatarPath);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new DAOException("Error in DAO method", e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public void insertUser(User user) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.provide();
            PreparedStatement preparedStatement;
            final String access = "registered";
            if (!isAlreadyExistsInDatabase(user.getName(), SELECT_FROM_NAMES, connection)){
                preparedStatement = connection.prepareStatement(ADD_USER_NAME);
                preparedStatement.setString(1, user.getName());
                preparedStatement.executeUpdate();
            }
            if (!isAlreadyExistsInDatabase(user.getSurname(), SELECT_FROM_SURNAMES, connection)){
                preparedStatement = connection.prepareStatement(ADD_SURNAME);
                preparedStatement.setString(1, user.getSurname());
                preparedStatement.executeUpdate();
            }
            if (!isAlreadyExistsInDatabase(access, SELECT_FROM_ACCESS_TYPES, connection)){
                preparedStatement = connection.prepareStatement(ADD_ACCESS_TYPE);
                preparedStatement.setString(1, access);
                preparedStatement.executeUpdate();
            }
            preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getAccessType());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new DAOException("Error in DAO method", e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public ResultSet getResultSetOfAllUsers() throws DAOException {
        Connection connection = null;
        ResultSet resultSet;
        try{
            connection = connectionPool.provide();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_FROM_USERS);
        }
        catch (SQLException e){
            throw new DAOException("Error in DAO method", e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
        return resultSet;
    }

    @Override
    public void updatePassword(String email, String password) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.provide();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(UPDATE_PASSWORD);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new DAOException("Error in DAO method", e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public Pair getName(String email) throws DAOException {
        Connection connection = null;
        ResultSet resultSet;
        Pair pair;
        try {
            connection = connectionPool.provide();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(GET_NAME_AND_SURNAME);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            pair = new Pair(resultSet.getString(2), resultSet.getString(3));
        }
        catch (SQLException e){
            throw new DAOException("Error in DAO method", e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
        return pair;
    }

    @Override
    public ArrayList<Pair> selectDataForEnter() throws DAOException {
        ResultSet resultSet;
        ArrayList<Pair> arrayList = new ArrayList<>();
        try {
            resultSet = getResultSetOfAllUsers();
            while (resultSet.next()) {
                Pair pair = new Pair(resultSet.getString(4), resultSet.getString(5));
                arrayList.add(pair);
            }
        }
        catch (SQLException e){
            throw new DAOException("Error in DAO method", e);
        }
        return arrayList;
    }

    @Override
    public boolean isExistingEmail(String email) throws DAOException {
        try {
            ResultSet resultSet = getResultSetOfAllUsers();
            while (resultSet.next()) {
                if (email.equals(resultSet.getString(4))) return true;
            }
            return false;
        }
        catch (SQLException e){
            throw new DAOException("Error in DAO method", e);
        }
    }

    @Override
    public String getAvatarPathByEmail(String email) throws DAOException {
        Connection connection = null;
        ResultSet resultSet;
        String result;
        try {
            connection = connectionPool.provide();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(GET_AVATAR_BY_IMAGE);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getString(1);
        }
        catch (SQLException e){
            throw new DAOException("Error in DAO method", e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
        return result;
    }

    @Override
    public ArrayList<User> getListOfUsers()throws DAOException {
        Connection connection = null;
        ResultSet resultSet;
        ArrayList<User> users = new ArrayList<>();
        try {
            connection = connectionPool.provide();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_FROM_USERS);
            while (resultSet.next()){
                users.add(new User(resultSet.getString(2), resultSet.getString(3),resultSet.getString(6),
                        resultSet.getString(4), ""));
            }
        }
        catch (SQLException e){
            throw new DAOException("Error in DAO method", e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
        return users;
    }

    @Override
    public void deleteUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getAccessType());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
    }
}
