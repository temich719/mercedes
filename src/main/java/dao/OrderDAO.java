package dao;

import dao.entity.Order;
import dao.entity.User;
import dao.entity.UserDTO;
import dao.exception.DAOException;

import java.util.ArrayList;

public interface OrderDAO {
    /**
     * adds new order in database
     *
     * @param order is a order
     * @throws DAOException is module exception
     */
    void addOrder(Order order) throws DAOException;

    /**
     * deletes orders of user who was deleted
     *
     * @param userDTO is the data of deleted user
     * @throws DAOException is a module exception
     */
    void deleteOrdersOfDeletedUser(UserDTO userDTO) throws DAOException;

    /**
     * finds all orders
     *
     * @return list of orders
     * @throws DAOException is a module exception
     */
    ArrayList<Order> getListOfOrders() throws DAOException;

    /**
     * calculates count of unread orders
     *
     * @param email is email of user whose unread orders will be counted
     * @return count of unread orders
     * @throws DAOException is a module exception
     */
    String getCountOfUnreadOrders(String email) throws DAOException;

    /**
     * finds as much order information as can fit on one page
     *
     * @param pageNumber is the number of page that will be illustrated
     * @return list of objects that contains order information
     * @throws DAOException is a module exception
     */
    ArrayList<Order> getOrderInfoForOnePage(String pageNumber) throws DAOException;

    /**
     * calculates how much pages do we need to illustrate it in UI
     *
     * @return list of strings that has the size equals to amount of pages
     * @throws DAOException is a module exception
     */
    ArrayList<String> getCountOfOrdersPages() throws DAOException;

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
}
