package service;

import dao.entity.Order;
import service.exception.ServiceException;

public interface OrderService {
    /**
     * deletes order from database
     * @param order is order which will be deleted
     * @throws ServiceException is a module exception
     */
    void deleteOrder(Order order)throws ServiceException;

    /**
     * calculates count of unread orders
     * @param email is email of user whose unread orders will be counted
     * @return count of unread orders
     * @throws ServiceException is a module exception
     */
    String getCountOfUnreadOrders(String email)throws ServiceException;

    /**
     * adds order to database
     * @param order is an order which will be added
     * @throws ServiceException is a module exception
     */
    void addOrder(Order order)throws ServiceException;

    /**
     * marks order as read according to name, surname, email, service, mark
     * @param name is a name
     * @param surname is a surname
     * @param email is an email
     * @param service is a service
     * @param mark is a mark
     * @throws ServiceException is a module exception
     */
    void markAsRead(String name, String surname, String email, String service, String mark)throws ServiceException;

}
