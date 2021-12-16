package dao;

import dao.entity.Order;
import dao.entity.Page;
import dao.exception.DAOException;

import java.util.List;

public interface OrderDAO {
    /**
     * adds new order in database
     *
     * @param order is a order
     * @throws DAOException is module exception
     */
    void addOrder(Order order) throws DAOException;

    /**
     * finds all orders
     *
     * @return list of orders
     * @throws DAOException is a module exception
     */
    List<Order> getListOfOrders() throws DAOException;

    /**
     * calculates count of unread orders
     *
     * @param email is email of user whose unread orders will be counted
     * @return count of unread orders
     * @throws DAOException is a module exception
     */
    String getCountOfUnreadOrders(String email) throws DAOException;

    /**
     * deletes order from database
     *
     * @param id is the order id
     * @throws DAOException is a module exception
     */
    void deleteOrder(int id) throws DAOException;

    /**
     * changes status of order from 'unread' to 'read'
     *
     * @param id is the order id
     * @throws DAOException is a module exception
     */
    void markAsRead(int id) throws DAOException;

    /**
     * finds data of order and page that we need to illustrate
     *
     * @param pageNumber is the number of page
     * @return object that contains data about page whose number is pageNumber
     * @throws DAOException is a module exception
     */
    Page<Order> getPageOfOrders(String pageNumber) throws DAOException;
}
