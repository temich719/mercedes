package dao.impl;

import dao.AbstractDAO;
import dao.ConnectionPool;
import dao.OrderDAO;
import dao.entity.Order;
import dao.entity.User;
import dao.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class OrderDAOImpl extends AbstractDAO implements OrderDAO {

    private static final String SELECT_FROM_NAMES = "select * from names;";
    private static final String SELECT_FROM_SURNAMES = "select * from surnames;";
    private static final String SELECT_STATUS_IN_ACCOUNT = "select status_in_account from orders where email = ?;";
    private static final String MAKE_ORDER_STATUS_READ_WHERE_DATE_NON_NULL = "update orders set status_in_account = 'read' where user_name= ? && " +
            "user_surname = ? && email = ? && service = ? && car_name = ? && date = ?;";
    private static final String MAKE_ORDER_STATUS_READ = "update orders set status_in_account = 'read' where user_name = ? &&" +
            "user_surname = ? && email = ? && service = ? && car_name = ?;";
    private static final String INSERT_NAME = "insert into names(user_name) values(?);";
    private static final String INSERT_SURNAME = "insert into surnames(user_surname) values(?);";
    private static final String SELECT_FROM_ORDERS = "select * from orders;";
    private static final String INSERT_INTO_ORDERS_WHEN_DATE_IS_NULL = "insert into orders" +
            "(user_name,user_surname,email,service,car_name,price,phone,status_in_account) " +
            "values(?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_INTO_ORDERS = "insert into orders" +
            "(user_name,user_surname,email,service,car_name,price,phone,date,status_in_account) " +
            "values(?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String DELETE_FROM_ORDERS_WHEN_DATE_IS_NULL = "delete from orders where user_name = ? &&" +
            "user_surname = ? && email = ? && service = ? && car_name = ? && price = ? && phone = ?;";
    private static final String DELETE_FROM_ORDERS = "delete from orders where user_name = ? && user_surname = ? &&" +
            "email = ? && service = ? && car_name = ? && price = ? && date = ? && phone = ?;";
    private static final String DELETE_ORDER_OF_DELETED_USER = "delete from orders where user_name = ? && user_surname = ? && email = ?;";
    private static final String SELECT_INFO_FOR_ONE_ORDER_PAGE = "SELECT * FROM orders ORDER BY orders_id LIMIT ? OFFSET ?;";
    private static final String SELECT_COUNT_OF_ORDERS = "select count(*) from orders";
    private static final int LIMIT = 10;

    private static final Logger LOGGER = Logger.getLogger(OrderDAOImpl.class);

    public OrderDAOImpl(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    public ArrayList<Order> getListOfOrders() throws DAOException {
        LOGGER.info("List of orders");
        Connection connection = null;
        ResultSet resultSet;
        ArrayList<Order> orders = new ArrayList<>();
        try {
            connection = connectionPool.provide();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_FROM_ORDERS);
            while (resultSet.next()) {
                orders.add(new Order(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                        resultSet.getString(8), resultSet.getString(9), resultSet.getString(10)));
            }
        } catch (SQLException e) {
            throw new DAOException("DAO exception", e);
        } finally {
            connectionPool.retrieve(connection);
        }
        Collections.reverse(orders);
        return orders;
    }

    @Override
    public ArrayList<String> getCountOfOrdersPages() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> pageNumbers = new ArrayList<>();
        int count;
        int countOfPages;
        try {
            connection = connectionPool.provide();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_COUNT_OF_ORDERS);
            resultSet.next();
            count = resultSet.getInt(1);
            countOfPages = (int)Math.ceil((double) count/LIMIT);
            for (int i = 1;i <= countOfPages;i++){
                pageNumbers.add(Integer.toString(i));
            }
            if (pageNumbers.size() == 1){
                pageNumbers.clear();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, statement);
            connectionPool.retrieve(connection);
        }
        return pageNumbers;
    }

    @Override
    public ArrayList<Order> getOrderInfoForOnePage(String pageNumber) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Order> orders = new ArrayList<>();
        int offset = (Integer.parseInt(pageNumber) - 1) * LIMIT;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(SELECT_INFO_FOR_ONE_ORDER_PAGE);
            preparedStatement.setInt(1, LIMIT);
            preparedStatement.setInt(2, offset);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order(resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getString(9),
                        resultSet.getString(10));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, preparedStatement);
            connectionPool.retrieve(connection);
        }
        return orders;
    }

    @Override
    public String getCountOfUnreadOrders(String email) throws DAOException {
        Connection connection = null;
        ResultSet resultSet;
        int size = 0;
        try {
            connection = connectionPool.provide();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(SELECT_STATUS_IN_ACCOUNT);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString(1).equals("unread")) size++;
            }
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            connectionPool.retrieve(connection);
        }
        return size + "";
    }

    @Override
    public void markAsRead(String name, String surname, String email, String service, String mark, String date) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.provide();
            PreparedStatement preparedStatement;
            if (!date.equals("")) {
                preparedStatement = connection.prepareStatement(MAKE_ORDER_STATUS_READ_WHERE_DATE_NON_NULL);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, surname);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, service);
                preparedStatement.setString(5, mark);
                preparedStatement.setString(6, date);
            } else {
                preparedStatement = connection.prepareStatement(MAKE_ORDER_STATUS_READ);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, surname);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, service);
                preparedStatement.setString(5, mark);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public void addOrder(Order order) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.provide();
            PreparedStatement preparedStatement;
            if (!isAlreadyExistsInDatabase(order.getName(), SELECT_FROM_NAMES, connection)) {
                preparedStatement = connection.prepareStatement(INSERT_NAME);
                preparedStatement.setString(1, order.getName());
                preparedStatement.executeUpdate();
            }
            if (!isAlreadyExistsInDatabase(order.getSurname(), SELECT_FROM_SURNAMES, connection)) {
                preparedStatement = connection.prepareStatement(INSERT_SURNAME);
                preparedStatement.setString(1, order.getSurname());
                preparedStatement.executeUpdate();
            }
            if (Objects.isNull(order.getDate())) {
                preparedStatement = connection.prepareStatement(INSERT_INTO_ORDERS_WHEN_DATE_IS_NULL);
                preparedStatement.setString(1, order.getName());
                preparedStatement.setString(2, order.getSurname());
                preparedStatement.setString(3, order.getEmail());
                preparedStatement.setString(4, order.getService());
                preparedStatement.setString(5, order.getMark());
                preparedStatement.setString(6, order.getPrice());
                preparedStatement.setString(7, order.getPhone());
                preparedStatement.setString(8, order.getStatus());
            } else {
                preparedStatement = connection.prepareStatement(INSERT_INTO_ORDERS);
                preparedStatement.setString(1, order.getName());
                preparedStatement.setString(2, order.getSurname());
                preparedStatement.setString(3, order.getEmail());
                preparedStatement.setString(4, order.getService());
                preparedStatement.setString(5, order.getMark());
                preparedStatement.setString(6, order.getPrice());
                preparedStatement.setString(7, order.getPhone());
                preparedStatement.setString(8, order.getDate());
                preparedStatement.setString(9, order.getStatus());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public void deleteOrder(Order order) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.provide();
            PreparedStatement preparedStatement;
            if (Objects.isNull(order.getDate()) || order.getDate().equals("")) {
                preparedStatement = connection.prepareStatement(DELETE_FROM_ORDERS_WHEN_DATE_IS_NULL);
                preparedStatement.setString(1, order.getName());
                preparedStatement.setString(2, order.getSurname());
                preparedStatement.setString(3, order.getEmail());
                preparedStatement.setString(4, order.getService());
                preparedStatement.setString(5, order.getMark());
                preparedStatement.setString(6, order.getPrice());
                preparedStatement.setString(7, order.getPhone());
            } else {
                preparedStatement = connection.prepareStatement(DELETE_FROM_ORDERS);
                preparedStatement.setString(1, order.getName());
                preparedStatement.setString(2, order.getSurname());
                preparedStatement.setString(3, order.getEmail());
                preparedStatement.setString(4, order.getService());
                preparedStatement.setString(5, order.getMark());
                preparedStatement.setString(6, order.getPrice());
                preparedStatement.setString(7, order.getDate());
                preparedStatement.setString(8, order.getPhone());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public void deleteOrdersOfDeletedUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(DELETE_ORDER_OF_DELETED_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.retrieve(connection);
        }
    }
}
