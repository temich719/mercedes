package dao;

import dao.entity.Order;
import dao.entity.User;
import dao.exception.DAOException;
import service.exception.ServiceException;

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
     * deletes definite order
     *
     * @param order is a order
     * @throws DAOException is module exception
     */
    void deleteOrder(Order order) throws DAOException;

    /**
     * deletes orders of user who was deleted
     *
     * @param user is the deleted user
     * @throws DAOException is a module exception
     */
    void deleteOrdersOfDeletedUser(User user) throws DAOException;

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
     * marks order as read according to name, surname, email, service, mark
     *
     * @param name    is a name
     * @param surname is a surname
     * @param email   is an email
     * @param service is a service
     * @param mark    is a mark
     * @throws DAOException is a module exception
     */
    void markAsRead(String name, String surname, String email, String service, String mark, String date) throws DAOException;
}
