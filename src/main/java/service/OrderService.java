package service;

import dao.entity.Order;
import dao.entity.User;
import service.exception.ServiceException;

import java.util.ArrayList;

public interface OrderService {
    /**
     * deletes order from database
     *
     * @param order is order which will be deleted
     * @throws ServiceException is a module exception
     */
    void deleteOrder(Order order) throws ServiceException;

    /**
     * calculates count of unread orders
     *
     * @param email is email of user whose unread orders will be counted
     * @return count of unread orders
     * @throws ServiceException is a module exception
     */
    String getCountOfUnreadOrders(String email) throws ServiceException;

    /**
     * adds order to database
     *
     * @param order is an order which will be added
     * @throws ServiceException is a module exception
     */
    void addOrder(Order order) throws ServiceException;

    /**
     * marks order as read according to name, surname, email, service, mark
     *
     * @param name    is a name
     * @param surname is a surname
     * @param email   is an email
     * @param service is a service
     * @param mark    is a mark
     * @throws ServiceException is a module exception
     */
    void markAsRead(String name, String surname, String email, String service, String mark, String date) throws ServiceException;

    /**
     * finds all orders
     *
     * @return list of orders
     * @throws ServiceException is a module exception
     */
    ArrayList<Order> getListOfOrders() throws ServiceException;

    /**
     * deletes orders of user who was deleted
     *
     * @param user is the deleted user
     * @throws ServiceException is a module exception
     */
    void deleteOrdersOfDeletedUser(User user) throws ServiceException;

    /**
     * finds as much order information as can fit on one page
     *
     * @param pageNumber is the number of page that will be illustrated
     * @return list of objects that contains order information
     * @throws ServiceException is a module exception
     */
    ArrayList<Order> getOrderInfoForOnePage(String pageNumber) throws ServiceException;

    /**
     * calculates how much pages do we need to illustrate it in UI
     *
     * @return list of strings that has the size equals to amount of pages
     * @throws ServiceException is a module exception
     */
    ArrayList<String> getCountOfOrdersPages() throws ServiceException;
}
