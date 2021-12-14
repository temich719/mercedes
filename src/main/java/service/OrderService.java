package service;

import dao.entity.Order;
import dao.entity.User;
import dao.entity.UserDTO;
import service.exception.ServiceException;

import java.util.ArrayList;

public interface OrderService {

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
     * finds all orders
     *
     * @return list of orders
     * @throws ServiceException is a module exception
     */
    ArrayList<Order> getListOfOrders() throws ServiceException;

    /**
     * deletes orders of user who was deleted
     *
     * @param userDTO is the data of deleted user
     * @throws ServiceException is a module exception
     */
    void deleteOrdersOfDeletedUser(UserDTO userDTO) throws ServiceException;

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

    /**
     * deletes order from database
     *
     * @param id is the order id
     * @throws ServiceException is a module exception
     */
    void deleteOrder(int id) throws ServiceException;

    /**
     * changes status of order from 'unread' to 'read'
     *
     * @param id is the order id
     * @throws ServiceException is a module exception
     */
    void markAdRead(int id) throws ServiceException;
}
