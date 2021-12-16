package service;

import dao.entity.Order;
import dao.entity.Page;
import service.exception.ServiceException;

import java.util.List;

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
    List<Order> getListOfOrders() throws ServiceException;

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

    /**
     * finds data of order and page that we need to illustrate
     *
     * @param pageNumber is the number of page
     * @return object that contains data about page whose number is pageNumber
     * @throws ServiceException is a module exception
     */
    Page<Order> getPageOfOrders(String pageNumber) throws ServiceException;
}
