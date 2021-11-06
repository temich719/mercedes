package dao.impl;

import dao.AbstractDAO;
import dao.ConnectionPool;
import dao.UserDAOTime;
import dao.entity.Pair;
import dao.entity.User;
import dao.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAOImpl extends AbstractDAO implements UserDAOTime {

    private static final String SELECT_FROM_NAMES = "select * from names";
    private static final String SELECT_FROM_SURNAMES = "select * from surnames";
    private static final String SELECT_FROM_ACCESS_TYPES = "select * from access_types";
    private static final String SELECT_FROM_USERS = "select * from users";

    public UserDAOImpl(ConnectionPool connectionPool){
        super(connectionPool);
    }

    public static void addAvatar(String avatarPath, String email)throws DAOException{
        Connection connection = null;
        try {
            connection = connectionPool.provide();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update users set avatar = '" + avatarPath + "' where email = '" + email + "';");
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
            Statement statement = connection.createStatement();
            final String access = "registered";
            if (!isAlreadyExistsInDatabase(user.getName(), SELECT_FROM_NAMES, connection))
                statement.executeUpdate("insert into names(user_name) values('"+user.getName()+"');");
            if (!isAlreadyExistsInDatabase(user.getSurname(), SELECT_FROM_SURNAMES, connection))
                statement.executeUpdate("insert into surnames(user_surname) values('"+user.getSurname()+"');");
            if (!isAlreadyExistsInDatabase(access, SELECT_FROM_ACCESS_TYPES, connection))
                statement.executeUpdate("insert into access_types(access_type) values('registered');");
            statement.executeUpdate("insert into users(user_name,user_surname,email,password,access_type) " +
                    "values('"+user.getName()+"','"+user.getSurname()+"','"+user.getEmail()+"','"+user.getPassword()
                    +"','registered');");
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
            Statement statement = connection.createStatement();
            statement.executeUpdate("update users set password='" + password + "' where email='" + email + "';");
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
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from users where email='" + email + "';");
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
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select avatar from users where email='" + email + "';");
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
}
