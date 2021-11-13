package dao.impl;

import dao.AbstractDAO;
import dao.ConnectionPool;
import dao.OrderDAO;
import dao.daoFactory.DaoFactory;
import dao.entity.Order;
import dao.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class OrderDAOImpl extends AbstractDAO implements OrderDAO {

    private static final String SELECT_FROM_NAMES = "select * from names";
    private static final String SELECT_FROM_SURNAMES = "select * from surnames";
    private static final Logger logger = Logger.getLogger(OrderDAOImpl.class);

    public OrderDAOImpl(ConnectionPool connectionPool){
        super(connectionPool);
    }

    public static ArrayList<Order> getListOfOrders()throws DAOException{
        logger.info("List of orders");
        return DaoFactory.getINSTANCE().getDataBase().getListOfOrders();
    }

    public static String getCountOfUnreadOrders(String email)throws DAOException{
        Connection connection = null;
        ResultSet resultSet;
        int size = 0;
        try {
            connection = connectionPool.provide();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select status_in_account from orders where email = '" + email + "';");
            while (resultSet.next()){
                if (resultSet.getString(1).equals("unread"))size++;
            }
        }
        catch (SQLException e){
            throw new DAOException("Error in DAO method", e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
        return size+"";
    }

    public static void markAsRead(String name, String surname, String email, String service, String mark, String date)throws DAOException{
        Connection connection = null;
        try {
            connection = connectionPool.provide();
            Statement statement = connection.createStatement();
            if (!date.equals("")) {
                statement.executeUpdate("update orders set status_in_account = 'read' where user_name='" + name +
                        "' && user_surname='" + surname + "' && email='" + email + "' && service='" + service + "' && car_name='" + mark +
                        "' && date='" + date + "';");
            }
            else {
                statement.executeUpdate("update orders set status_in_account = 'read' where user_name='" + name +
                        "' && user_surname='" + surname + "' && email='" + email + "' && service='" + service + "' && car_name='" + mark + "';");
            }
        }
        catch (SQLException e){
            throw new DAOException("Error in DAO method", e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public void addOrder(Order order) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.provide();
            Statement statement = connection.createStatement();
            if (Objects.isNull(order.getDate())){
                statement.executeUpdate("insert into orders(user_name,user_surname,email,service,car_name,price,phone,status_in_account) values('"+
                        order.getName()+"','" + order.getSurname()+"','" + order.getEmail() + "','"
                        +order.getService() +"','" + order.getMark() + "','" + order.getPrice() + "','" + order.getPhone()
                        +"','" + order.getStatus() + "');");
            }
            else {
                if (!isAlreadyExistsInDatabase(order.getName(), SELECT_FROM_NAMES, connection))
                    statement.executeUpdate("insert into names(user_name) values('" + order.getName() + "');");
                if (!isAlreadyExistsInDatabase(order.getSurname(), SELECT_FROM_SURNAMES, connection))
                    statement.executeUpdate("insert into surnames(user_surname) values('" + order.getSurname() + "');");
                statement.executeUpdate("insert into orders(user_name,user_surname,email,service,car_name,price,phone,date,status_in_account) values('" +
                        order.getName() + "','" + order.getSurname() + "','" + order.getEmail()
                        + "','" + order.getService() + "','" + order.getMark() + "','" + order.getPrice() + "','"
                        + order.getPhone() + "','" + order.getDate() + "','" + order.getStatus() + "');");
            }
        }
        catch (SQLException e){
            throw new DAOException("Error in DAO method", e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public void deleteOrder(Order order) throws DAOException{
        Connection connection = null;
        try {
            connection = connectionPool.provide();
            Statement statement = connection.createStatement();
            String query;
            if (Objects.isNull(order.getDate()) || order.getDate().equals("")){
                query = "delete from orders where user_name='" + order.getName() + "' && user_surname='"
                        + order.getSurname() + "' && email='" + order.getEmail() + "' && service='" +
                        order.getService() + "' && car_name='" + order.getMark() + "' && price='" + order.getPrice()
                        + "' && phone='" + order.getPhone() + "';";
            }
            else {
                query = "delete from orders where user_name='" + order.getName() + "' && user_surname='"
                        + order.getSurname() + "' && email='" + order.getEmail() + "' && service='" +
                        order.getService() + "' && car_name='" + order.getMark() + "' && price='" + order.getPrice() +
                        "' && date='" + order.getDate() + "' && phone='" + order.getPhone() + "';";
            }
            statement.execute(query);
        }
        catch (SQLException e){
            throw new DAOException("Error in DAO method", e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
    }
}
