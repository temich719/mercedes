package dao.impl;

import dao.ConnectionPool;
import dao.UserDAO;
import dao.entity.User;
import dao.exception.DAOException;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.*;
import java.util.ArrayList;

public class UserDAOImplTest {

    @Test
    public void testAddAvatar() throws DAOException, SQLException {
        String avatarPath = "img/image.jpg";
        String email = "dog14@mail.ru";
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        String query = "update users set avatar = ? where email = ?;";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(query)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, avatarPath);
        Mockito.doNothing().when(preparedStatement).setString(2, email);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.addAvatar(avatarPath, email);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(query);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, avatarPath);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, email);
        Mockito.verify(preparedStatement).executeUpdate();

        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testAddAvatar_DAOException()throws DAOException, SQLException{
        String avatarPath = "img/image.jpg";
        String email = "dog14@mail.ru";
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        String query = "update users set avatar = ? where email = ?;";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(query)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, avatarPath);
        Mockito.doNothing().when(preparedStatement).setString(2, email);
        Mockito.when(preparedStatement.executeUpdate()).thenThrow(SQLException.class);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.addAvatar(avatarPath, email);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(query);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, avatarPath);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, email);
        Mockito.verify(preparedStatement).executeUpdate();

        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testInsertUser() throws DAOException, SQLException {
       User user = new User("Ivan", "Ivanov", "registered", "vanya12@gmail.com", "1213#gh");
       String SELECT_FROM_NAMES = "select * from names;";
       String ADD_USER = "insert into users(user_name,user_surname,email,password,access_type) values(?, ?, ?, ?, ?);";
       String SELECT_FROM_SURNAMES = "select * from surnames;";
       String SELECT_FROM_ACCESS_TYPES = "select * from access_types;";
       String ADD_USER_NAME = "insert into names(user_name) values(?);";
       String ADD_SURNAME = "insert into surnames(user_surname) values(?);";
       String ADD_ACCESS_TYPE = "insert into access_types(access_type) values(?);";
       Connection connection = Mockito.mock(Connection.class);
       ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
       Statement statement = Mockito.mock(Statement.class);
       ResultSet resultSet = Mockito.mock(ResultSet.class);
       PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);

       Mockito.when(connectionPool.provide()).thenReturn(connection);

       Mockito.when(connection.createStatement()).thenReturn(statement);
       Mockito.when(statement.executeQuery(SELECT_FROM_NAMES)).thenReturn(resultSet);
       Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
       Mockito.when(resultSet.getString(2)).thenReturn(Mockito.anyString());

       Mockito.when(connection.prepareStatement(ADD_USER_NAME)).thenReturn(preparedStatement);
       Mockito.doNothing().when(preparedStatement).setString(1, user.getName());
       Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);

       Mockito.when(connection.createStatement()).thenReturn(statement);
       Mockito.when(statement.executeQuery(SELECT_FROM_SURNAMES)).thenReturn(resultSet);
       Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
       Mockito.when(resultSet.getString(2)).thenReturn(Mockito.anyString());

       Mockito.when(connection.prepareStatement(ADD_SURNAME)).thenReturn(preparedStatement);
       Mockito.doNothing().when(preparedStatement).setString(1, user.getSurname());
       Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);

       Mockito.when(connection.createStatement()).thenReturn(statement);
       Mockito.when(statement.executeQuery(SELECT_FROM_ACCESS_TYPES)).thenReturn(resultSet);
       Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
       Mockito.when(resultSet.getString(2)).thenReturn(Mockito.anyString());

       Mockito.when(connection.prepareStatement(ADD_ACCESS_TYPE)).thenReturn(preparedStatement);
       Mockito.doNothing().when(preparedStatement).setString(1, user.getAccessType());
       Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);

       Mockito.when(connection.prepareStatement(ADD_USER)).thenReturn(preparedStatement);
       Mockito.doNothing().when(preparedStatement).setString(1, user.getName());
       Mockito.doNothing().when(preparedStatement).setString(2, user.getSurname());
       Mockito.doNothing().when(preparedStatement).setString(3, user.getEmail());
       Mockito.doNothing().when(preparedStatement).setString(4, user.getPassword());
       Mockito.doNothing().when(preparedStatement).setString(5, user.getAccessType());
       Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);

       Mockito.doNothing().when(connectionPool).retrieve(connection);

       UserDAO userDAO = new UserDAOImpl(connectionPool);
       userDAO.insertUser(user);

       Mockito.verify(connectionPool).provide();

       Mockito.verify(connection, Mockito.times(3)).createStatement();
       Mockito.verify(statement).executeQuery(SELECT_FROM_NAMES);
       Mockito.verify(resultSet, Mockito.times(4)).next();
       Mockito.verify(resultSet).getString(2);

       Mockito.verify(connection).prepareStatement(ADD_USER_NAME);
       Mockito.verify(preparedStatement, Mockito.times(2)).setString(1, user.getName());
       Mockito.verify(preparedStatement, Mockito.times(4)).executeUpdate();

       Mockito.verify(statement).executeQuery(SELECT_FROM_SURNAMES);
       Mockito.verify(resultSet).getString(2);

       Mockito.verify(connection).prepareStatement(ADD_SURNAME);
       Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, user.getSurname());

       Mockito.verify(statement).executeQuery(SELECT_FROM_ACCESS_TYPES);
       Mockito.verify(resultSet).getString(2);

       Mockito.verify(connection).prepareStatement(ADD_ACCESS_TYPE);
       Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, user.getAccessType());

       Mockito.verify(connection).prepareStatement(ADD_USER);
       Mockito.verify(preparedStatement, Mockito.times(2)).setString(1, user.getName());
       Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, user.getSurname());
       Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, user.getEmail());
       Mockito.verify(preparedStatement, Mockito.times(1)).setString(4, user.getPassword());
       Mockito.verify(preparedStatement, Mockito.times(1)).setString(5, user.getAccessType());
       Mockito.verify(preparedStatement, Mockito.times(4)).executeUpdate();

       Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testInsertUser_DAOException() throws DAOException, SQLException{
        User user = new User("Ivan", "Ivanov", "registered", "vanya12@gmail.com", "1213#gh");
        String SELECT_FROM_NAMES = "select * from names;";
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Statement statement = Mockito.mock(Statement.class);

        Mockito.when(connectionPool.provide()).thenReturn(connection);

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_NAMES)).thenThrow(SQLException.class);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.insertUser(user);

        Mockito.verify(connection, Mockito.times(3)).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_NAMES);
    }

    @Test
    public void testGetResultSetOfAllUsers() throws DAOException, SQLException {
        Connection connection = Mockito.mock(Connection.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Statement statement = Mockito.mock(Statement.class);
        String SELECT_FROM_USERS = "select * from users;";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_USERS)).thenReturn(resultSet);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.getResultSetOfAllUsers();

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_USERS);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testGetResultSetOfAllUsers_DAOException() throws DAOException, SQLException{
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Statement statement = Mockito.mock(Statement.class);
        String SELECT_FROM_USERS = "select * from users;";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_USERS)).thenThrow(SQLException.class);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.getResultSetOfAllUsers();

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_USERS);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testUpdatePassword() throws DAOException, SQLException {
        String UPDATE_PASSWORD = "update users set password = ? where email = ?;";
        String email = "god@mail.ru";
        String password = "123$ABc";
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(UPDATE_PASSWORD)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, password);
        Mockito.doNothing().when(preparedStatement).setString(2, email);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.updatePassword(email, password);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(UPDATE_PASSWORD);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, password);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, email);
        Mockito.verify(preparedStatement).executeUpdate();
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testUpdatePassword_DAOException() throws DAOException, SQLException{
        String UPDATE_PASSWORD = "update users set password = ? where email = ?;";
        String email = "god@mail.ru";
        String password = "123$ABc";
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(UPDATE_PASSWORD)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, password);
        Mockito.doNothing().when(preparedStatement).setString(2, email);
        Mockito.when(preparedStatement.executeUpdate()).thenThrow(SQLException.class);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.updatePassword(email, password);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(UPDATE_PASSWORD);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, password);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, email);
        Mockito.verify(preparedStatement).executeUpdate();
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testGetName() throws DAOException, SQLException{
        String GET_NAME_AND_SURNAME = "select * from users where email = ?;";
        String email = "cat@mail.ru";
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(GET_NAME_AND_SURNAME)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, email);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.getString(2)).thenReturn(null);
        Mockito.when(resultSet.getString(3)).thenReturn(null);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.getNameAndSurname(email);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(GET_NAME_AND_SURNAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, email);
        Mockito.verify(preparedStatement).executeQuery();
        Mockito.verify(resultSet).getString(2);
        Mockito.verify(resultSet).getString(3);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testGetName_DAOException() throws DAOException, SQLException{
        String GET_NAME_AND_SURNAME = "select * from users where email = ?;";
        String email = "cat@mail.ru";
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(GET_NAME_AND_SURNAME)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, email);
        Mockito.when(preparedStatement.executeQuery()).thenThrow(SQLException.class);
        Mockito.when(resultSet.getString(2)).thenReturn(null);
        Mockito.when(resultSet.getString(3)).thenReturn(null);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.getNameAndSurname(email);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(GET_NAME_AND_SURNAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, email);
        Mockito.verify(preparedStatement).executeQuery();
        Mockito.verify(resultSet).getString(2);
        Mockito.verify(resultSet).getString(3);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testSelectDataForEnter()throws DAOException, SQLException{
        ResultSet resultSet1 = Mockito.mock(ResultSet.class);
        ResultSet resultSet2 = Mockito.mock(ResultSet.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);
        String SELECT_FROM_USERS = "select * from users;";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_USERS)).thenReturn(resultSet2);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        Mockito.when(resultSet1.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.selectDataForEnter();

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_USERS);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testSelectDataForEnter_DAOException()throws DAOException, SQLException{
        ResultSet resultSet1 = Mockito.mock(ResultSet.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);
        String SELECT_FROM_USERS = "select * from users;";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_USERS)).thenThrow(SQLException.class);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        Mockito.when(resultSet1.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.selectDataForEnter();

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_USERS);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testIsExistingEmail()throws DAOException, SQLException{
        ResultSet resultSet1 = Mockito.mock(ResultSet.class);
        Connection connection = Mockito.mock(Connection.class);
        ResultSet resultSet2 = Mockito.mock(ResultSet.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Statement statement = Mockito.mock(Statement.class);
        String SELECT_FROM_USERS = "select * from users;";
        String email = "pain@mail.ru";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_USERS)).thenReturn(resultSet2);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        Mockito.when(resultSet1.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.isExistingEmail(email);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_USERS);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testIsExistingEmail_DAOException()throws DAOException, SQLException{
        ResultSet resultSet1 = Mockito.mock(ResultSet.class);
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Statement statement = Mockito.mock(Statement.class);
        String SELECT_FROM_USERS = "select * from users;";
        String email = "pain@mail.ru";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_USERS)).thenThrow(SQLException.class);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        Mockito.when(resultSet1.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.isExistingEmail(email);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_USERS);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testGetAvatarPathByEmail()throws DAOException, SQLException{
        String email = "hate@mail.ru";
        String GET_AVATAR_BY_IMAGE = "select avatar from users where email = ?;";
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(GET_AVATAR_BY_IMAGE)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, email);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.getAvatarPathByEmail(email);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(GET_AVATAR_BY_IMAGE);
        Mockito.verify(preparedStatement).setString(1, email);
        Mockito.verify(preparedStatement).executeQuery();
        Mockito.verify(resultSet, Mockito.times(1)).next();
        Mockito.verify(resultSet).getString(1);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testGetAvatarPathByEmail_DAOException()throws DAOException, SQLException{
        String email = "hate@mail.ru";
        String GET_AVATAR_BY_IMAGE = "select avatar from users where email = ?;";
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(GET_AVATAR_BY_IMAGE)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, email);
        Mockito.when(preparedStatement.executeQuery()).thenThrow(SQLException.class);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.getAvatarPathByEmail(email);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(GET_AVATAR_BY_IMAGE);
        Mockito.verify(preparedStatement).setString(1, email);
        Mockito.verify(preparedStatement).executeQuery();
        Mockito.verify(resultSet, Mockito.times(1)).next();
        Mockito.verify(resultSet).getString(1);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testGetListOfUsers()throws DAOException, SQLException{
        String SELECT_FROM_USERS = "select * from users;";
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        ArrayList<User> arrayList = Mockito.mock(ArrayList.class);
        Statement statement = Mockito.mock(Statement.class);

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_USERS)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(3)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(6)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(4)).thenReturn(Mockito.anyString());
        Mockito.when(arrayList.add(new User(resultSet.getString(2), resultSet.getString(3),resultSet.getString(6),
                resultSet.getString(4), ""))).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.getListOfUsers();

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_USERS);
        Mockito.verify(resultSet, Mockito.times(2)).next();
        Mockito.verify(resultSet, Mockito.times(2)).getString(2);
        Mockito.verify(resultSet, Mockito.times(2)).getString(3);
        Mockito.verify(resultSet, Mockito.times(2)).getString(6);
        Mockito.verify(resultSet, Mockito.times(2)).getString(4);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testGetListOfUsers_DAOException()throws DAOException, SQLException{
        String SELECT_FROM_USERS = "select * from users;";
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        ArrayList<User> arrayList = Mockito.mock(ArrayList.class);
        Statement statement = Mockito.mock(Statement.class);

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_USERS)).thenThrow(SQLException.class);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(3)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(6)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(4)).thenReturn(Mockito.anyString());
        Mockito.when(arrayList.add(new User(resultSet.getString(2), resultSet.getString(3),resultSet.getString(6),
                resultSet.getString(4), ""))).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.getListOfUsers();

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_USERS);
        Mockito.verify(resultSet, Mockito.times(2)).next();
        Mockito.verify(resultSet, Mockito.times(2)).getString(2);
        Mockito.verify(resultSet, Mockito.times(2)).getString(3);
        Mockito.verify(resultSet, Mockito.times(2)).getString(6);
        Mockito.verify(resultSet, Mockito.times(2)).getString(4);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testDeleteUser()throws DAOException, SQLException{
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        String DELETE_USER = "delete from users where user_name = ? && user_surname = ? && email = ? && access_type = ?;";
        User user = new User("Alex", "Jones", "registered", "mess@mail.ru", "17812hfjN$%");

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(DELETE_USER)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, user.getName());
        Mockito.doNothing().when(preparedStatement).setString(2, user.getSurname());
        Mockito.doNothing().when(preparedStatement).setString(3, user.getEmail());
        Mockito.doNothing().when(preparedStatement).setString(4, user.getAccessType());
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.deleteUser(user);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(DELETE_USER);
        Mockito.verify(preparedStatement).setString(1, user.getName());
        Mockito.verify(preparedStatement).setString(2, user.getSurname());
        Mockito.verify(preparedStatement).setString(3, user.getEmail());
        Mockito.verify(preparedStatement).setString(4, user.getAccessType());
        Mockito.verify(preparedStatement).executeUpdate();
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testDeleteUser_DAOException()throws DAOException, SQLException{
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        String DELETE_USER = "delete from users where user_name = ? && user_surname = ? && email = ? && access_type = ?;";
        User user = new User("Alex", "Jones", "registered", "mess@mail.ru", "17812hfjN$%");

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(DELETE_USER)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, user.getName());
        Mockito.doNothing().when(preparedStatement).setString(2, user.getSurname());
        Mockito.doNothing().when(preparedStatement).setString(3, user.getEmail());
        Mockito.doNothing().when(preparedStatement).setString(4, user.getAccessType());
        Mockito.when(preparedStatement.executeUpdate()).thenThrow(SQLException.class);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.deleteUser(user);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(DELETE_USER);
        Mockito.verify(preparedStatement).setString(1, user.getName());
        Mockito.verify(preparedStatement).setString(2, user.getSurname());
        Mockito.verify(preparedStatement).setString(3, user.getEmail());
        Mockito.verify(preparedStatement).setString(4, user.getAccessType());
        Mockito.verify(preparedStatement).executeUpdate();
        Mockito.verify(connectionPool).retrieve(connection);
    }
}
