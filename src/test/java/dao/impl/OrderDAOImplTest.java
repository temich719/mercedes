package dao.impl;

import dao.ConnectionPool;
import dao.OrderDAO;
import dao.daoFactory.DaoFactory;
import dao.entity.Order;
import dao.entity.User;
import dao.exception.DAOException;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.*;
import java.util.ArrayList;


public class OrderDAOImplTest {

    @Test
    public void testGetListOfOrders()throws DAOException, SQLException {
        String SELECT_FROM_ORDERS = "select * from orders;";
        DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        CarDAOImpl carDAOImpl = Mockito.mock(CarDAOImpl.class);
        Connection connection = Mockito.mock(Connection.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Statement statement = Mockito.mock(Statement.class);
        ArrayList<Order> orders = Mockito.mock(ArrayList.class);

        Mockito.when(daoFactory.getCarDAO()).thenReturn(carDAOImpl);
        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_ORDERS)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(3)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(4)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(5)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(6)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(7)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(8)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(9)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(10)).thenReturn(Mockito.anyString());
        Mockito.when(orders.add(new Order(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                resultSet.getString(8), resultSet.getString(9), resultSet.getString(10)))).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        OrderDAO orderDAO = new OrderDAOImpl(connectionPool);
        orderDAO.getListOfOrders();

        Mockito.verify(resultSet, Mockito.times(2)).getString(2);
        Mockito.verify(resultSet, Mockito.times(2)).getString(3);
        Mockito.verify(resultSet, Mockito.times(2)).getString(4);
        Mockito.verify(resultSet, Mockito.times(2)).getString(5);
        Mockito.verify(resultSet, Mockito.times(2)).getString(6);
        Mockito.verify(resultSet, Mockito.times(2)).getString(7);
        Mockito.verify(resultSet, Mockito.times(2)).getString(8);
        Mockito.verify(resultSet, Mockito.times(2)).getString(9);
        Mockito.verify(resultSet, Mockito.times(2)).getString(10);
    }

    @Test(expected = DAOException.class)
    public void testGetListOfOrders_DAOException()throws DAOException, SQLException{
        String SELECT_FROM_ORDERS = "select * from orders;";
        DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        CarDAOImpl carDAOImpl = Mockito.mock(CarDAOImpl.class);
        Connection connection = Mockito.mock(Connection.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Statement statement = Mockito.mock(Statement.class);
        ArrayList<Order> orders = Mockito.mock(ArrayList.class);

        Mockito.when(daoFactory.getCarDAO()).thenReturn(carDAOImpl);
        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_ORDERS)).thenThrow(SQLException.class);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(3)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(4)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(5)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(6)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(7)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(8)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(9)).thenReturn(Mockito.anyString());
        Mockito.when(resultSet.getString(10)).thenReturn(Mockito.anyString());
        Mockito.when(orders.add(new Order(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                resultSet.getString(8), resultSet.getString(9), resultSet.getString(10)))).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        OrderDAO orderDAO = new OrderDAOImpl(connectionPool);
        orderDAO.getListOfOrders();

        Mockito.verify(resultSet, Mockito.times(1)).getString(2);
        Mockito.verify(resultSet, Mockito.times(1)).getString(3);
        Mockito.verify(resultSet, Mockito.times(1)).getString(4);
        Mockito.verify(resultSet, Mockito.times(1)).getString(5);
        Mockito.verify(resultSet, Mockito.times(1)).getString(6);
        Mockito.verify(resultSet, Mockito.times(1)).getString(7);
        Mockito.verify(resultSet, Mockito.times(1)).getString(8);
        Mockito.verify(resultSet, Mockito.times(1)).getString(9);
        Mockito.verify(resultSet, Mockito.times(1)).getString(10);
    }

    @Test
    public void testGetCountOfUnreadOrders()throws DAOException, SQLException{
        String email = "somebody@mail.ru";
        String SELECT_STATUS_IN_ACCOUNT = "select status_in_account from orders where email = ?;";
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(SELECT_STATUS_IN_ACCOUNT)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, email);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        OrderDAO orderDAO = new OrderDAOImpl(connectionPool);
        orderDAO.getCountOfUnreadOrders(email);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(SELECT_STATUS_IN_ACCOUNT);
        Mockito.verify(preparedStatement).setString(1, email);
        Mockito.verify(preparedStatement).executeQuery();
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testGetCountOfUnreadOrders_DAOException()throws DAOException, SQLException{
        String email = "somebody@mail.ru";
        String SELECT_STATUS_IN_ACCOUNT = "select status_in_account from orders where email = ?;";
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(SELECT_STATUS_IN_ACCOUNT)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, email);
        Mockito.when(preparedStatement.executeQuery()).thenThrow(SQLException.class);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        OrderDAO orderDAO = new OrderDAOImpl(connectionPool);
        orderDAO.getCountOfUnreadOrders(email);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(SELECT_STATUS_IN_ACCOUNT);
        Mockito.verify(preparedStatement).setString(1, email);
        Mockito.verify(preparedStatement).executeQuery();
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testAddOrder_DateNonNull()throws DAOException, SQLException{
        String SELECT_FROM_NAMES = "select * from names;";
        String SELECT_FROM_SURNAMES = "select * from surnames;";
        Order order = new Order("Ivan", "Ivanov", "gas@gmail.com", "buying_a_car", "G-Class", "100 000$", "+375257307894", "2021-06-06", "read");
        String INSERT_NAME = "insert into names(user_name) values(?);";
        String INSERT_SURNAME = "insert into surnames(user_surname) values(?);";
        String INSERT_INTO_ORDERS_WHEN_DATE_IS_NULL = "insert into orders" +
                "(user_name,user_surname,email,service,car_name,price,phone,status_in_account) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?);";
        String INSERT_INTO_ORDERS = "insert into orders" +
                "(user_name,user_surname,email,service,car_name,price,phone,date,status_in_account) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        Mockito.when(connectionPool.provide()).thenReturn(connection);

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_NAMES)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn(Mockito.anyString());

        Mockito.when(connection.prepareStatement(INSERT_NAME)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, order.getName());
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_SURNAMES)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn(Mockito.anyString());

        Mockito.when(connection.prepareStatement(INSERT_SURNAME)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, order.getSurname());

        Mockito.when(connection.prepareStatement(INSERT_INTO_ORDERS_WHEN_DATE_IS_NULL)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, order.getName());
        Mockito.doNothing().when(preparedStatement).setString(2, order.getSurname());
        Mockito.doNothing().when(preparedStatement).setString(3, order.getEmail());
        Mockito.doNothing().when(preparedStatement).setString(4, order.getService());
        Mockito.doNothing().when(preparedStatement).setString(5, order.getMark());
        Mockito.doNothing().when(preparedStatement).setString(6, order.getPrice());
        Mockito.doNothing().when(preparedStatement).setString(7, order.getPhone());
        Mockito.doNothing().when(preparedStatement).setString(8, order.getStatus());

        Mockito.when(connection.prepareStatement(INSERT_INTO_ORDERS)).thenReturn(preparedStatement);

        Mockito.doNothing().when(connectionPool).retrieve(connection);

        OrderDAO orderDAO = new OrderDAOImpl(connectionPool);
        orderDAO.addOrder(order);

        Mockito.verify(connection, Mockito.times(2)).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_NAMES);
        Mockito.verify(resultSet, Mockito.times(3)).next();
        Mockito.verify(resultSet).getString(2);

        Mockito.verify(connection).prepareStatement(INSERT_NAME);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(1, order.getName());
        Mockito.verify(preparedStatement, Mockito.times(3)).executeUpdate();

        Mockito.verify(statement).executeQuery(SELECT_FROM_SURNAMES);
        Mockito.verify(resultSet).getString(2);

        Mockito.verify(connection).prepareStatement(INSERT_SURNAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, order.getSurname());

        Mockito.verify(preparedStatement, Mockito.times(2)).setString(1, order.getName());
        Mockito.verify(preparedStatement).setString(2, order.getSurname());
        Mockito.verify(preparedStatement).setString(3, order.getEmail());
        Mockito.verify(preparedStatement).setString(4, order.getService());
        Mockito.verify(preparedStatement).setString(5, order.getMark());
        Mockito.verify(preparedStatement).setString(6, order.getPrice());
        Mockito.verify(preparedStatement).setString(7, order.getPhone());
        Mockito.verify(connection).prepareStatement(INSERT_INTO_ORDERS);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testAddOrder_DateIsNull()throws DAOException, SQLException{
        String SELECT_FROM_NAMES = "select * from names;";
        String SELECT_FROM_SURNAMES = "select * from surnames;";
        Order order = new Order("Ivan", "Ivanov", "gas@gmail.com", "buying_a_car", "G-Class", "100 000$", "+375257307894", null, "read");
        String INSERT_NAME = "insert into names(user_name) values(?);";
        String INSERT_SURNAME = "insert into surnames(user_surname) values(?);";
        String INSERT_INTO_ORDERS_WHEN_DATE_IS_NULL = "insert into orders" +
                "(user_name,user_surname,email,service,car_name,price,phone,status_in_account) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?);";
        String INSERT_INTO_ORDERS = "insert into orders" +
                "(user_name,user_surname,email,service,car_name,price,phone,date,status_in_account) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        Mockito.when(connectionPool.provide()).thenReturn(connection);

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_NAMES)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn(Mockito.anyString());

        Mockito.when(connection.prepareStatement(INSERT_NAME)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, order.getName());
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_SURNAMES)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn(Mockito.anyString());

        Mockito.when(connection.prepareStatement(INSERT_SURNAME)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, order.getSurname());

        Mockito.when(connection.prepareStatement(INSERT_INTO_ORDERS_WHEN_DATE_IS_NULL)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, order.getName());
        Mockito.doNothing().when(preparedStatement).setString(2, order.getSurname());
        Mockito.doNothing().when(preparedStatement).setString(3, order.getEmail());
        Mockito.doNothing().when(preparedStatement).setString(4, order.getService());
        Mockito.doNothing().when(preparedStatement).setString(5, order.getMark());
        Mockito.doNothing().when(preparedStatement).setString(6, order.getPrice());
        Mockito.doNothing().when(preparedStatement).setString(7, order.getPhone());
        Mockito.doNothing().when(preparedStatement).setString(8, order.getStatus());

        Mockito.when(connection.prepareStatement(INSERT_INTO_ORDERS)).thenReturn(preparedStatement);

        Mockito.doNothing().when(connectionPool).retrieve(connection);

        OrderDAO orderDAO = new OrderDAOImpl(connectionPool);
        orderDAO.addOrder(order);

        Mockito.verify(connection, Mockito.times(2)).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_NAMES);
        Mockito.verify(resultSet, Mockito.times(3)).next();
        Mockito.verify(resultSet).getString(2);

        Mockito.verify(connection).prepareStatement(INSERT_NAME);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(1, order.getName());
        Mockito.verify(preparedStatement, Mockito.times(3)).executeUpdate();

        Mockito.verify(statement).executeQuery(SELECT_FROM_SURNAMES);
        Mockito.verify(resultSet).getString(2);

        Mockito.verify(connection).prepareStatement(INSERT_SURNAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, order.getSurname());

        Mockito.verify(preparedStatement, Mockito.times(2)).setString(1, order.getName());
        Mockito.verify(preparedStatement).setString(2, order.getSurname());
        Mockito.verify(preparedStatement).setString(3, order.getEmail());
        Mockito.verify(preparedStatement).setString(4, order.getService());
        Mockito.verify(preparedStatement).setString(5, order.getMark());
        Mockito.verify(preparedStatement).setString(6, order.getPrice());
        Mockito.verify(preparedStatement).setString(7, order.getPhone());
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testAddOrder_DAOException()throws DAOException, SQLException{
        String SELECT_FROM_NAMES = "select * from names;";
        String SELECT_FROM_SURNAMES = "select * from surnames;";
        Order order = new Order("Ivan", "Ivanov", "gas@gmail.com", "buying_a_car", "G-Class", "100 000$", "+375257307894", null, "read");
        String INSERT_NAME = "insert into names(user_name) values(?);";
        String INSERT_SURNAME = "insert into surnames(user_surname) values(?);";
        String INSERT_INTO_ORDERS_WHEN_DATE_IS_NULL = "insert into orders" +
                "(user_name,user_surname,email,service,car_name,price,phone,status_in_account) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?);";
        String INSERT_INTO_ORDERS = "insert into orders" +
                "(user_name,user_surname,email,service,car_name,price,phone,date,status_in_account) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        Mockito.when(connectionPool.provide()).thenReturn(connection);

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_NAMES)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn(Mockito.anyString());

        Mockito.when(connection.prepareStatement(INSERT_NAME)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, order.getName());
        Mockito.when(preparedStatement.executeUpdate()).thenThrow(SQLException.class);

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_SURNAMES)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn(Mockito.anyString());

        Mockito.when(connection.prepareStatement(INSERT_SURNAME)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, order.getSurname());

        Mockito.when(connection.prepareStatement(INSERT_INTO_ORDERS_WHEN_DATE_IS_NULL)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, order.getName());
        Mockito.doNothing().when(preparedStatement).setString(2, order.getSurname());
        Mockito.doNothing().when(preparedStatement).setString(3, order.getEmail());
        Mockito.doNothing().when(preparedStatement).setString(4, order.getService());
        Mockito.doNothing().when(preparedStatement).setString(5, order.getMark());
        Mockito.doNothing().when(preparedStatement).setString(6, order.getPrice());
        Mockito.doNothing().when(preparedStatement).setString(7, order.getPhone());
        Mockito.doNothing().when(preparedStatement).setString(8, order.getStatus());

        Mockito.when(connection.prepareStatement(INSERT_INTO_ORDERS)).thenReturn(preparedStatement);

        Mockito.doNothing().when(connectionPool).retrieve(connection);

        OrderDAO orderDAO = new OrderDAOImpl(connectionPool);
        orderDAO.addOrder(order);

        Mockito.verify(connection, Mockito.times(2)).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_NAMES);
        Mockito.verify(resultSet, Mockito.times(3)).next();
        Mockito.verify(resultSet).getString(2);

        Mockito.verify(connection).prepareStatement(INSERT_NAME);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(1, order.getName());
        Mockito.verify(preparedStatement, Mockito.times(3)).executeUpdate();

        Mockito.verify(statement).executeQuery(SELECT_FROM_SURNAMES);
        Mockito.verify(resultSet).getString(2);

        Mockito.verify(connection).prepareStatement(INSERT_SURNAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, order.getSurname());

        Mockito.verify(preparedStatement, Mockito.times(2)).setString(1, order.getName());
        Mockito.verify(preparedStatement).setString(2, order.getSurname());
        Mockito.verify(preparedStatement).setString(3, order.getEmail());
        Mockito.verify(preparedStatement).setString(4, order.getService());
        Mockito.verify(preparedStatement).setString(5, order.getMark());
        Mockito.verify(preparedStatement).setString(6, order.getPrice());
        Mockito.verify(preparedStatement).setString(7, order.getPhone());
        Mockito.verify(connectionPool).retrieve(connection);
    }

}
