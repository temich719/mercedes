package service;

import dao.entity.Order;
import service.exception.ServiceException;

public interface OrderService {
    void deleteOrder(Order order)throws ServiceException;
    String getCountOfUnreadOrders(String email)throws ServiceException;
    void addOrder(Order order)throws ServiceException;
    void markAsRead(String name, String surname, String email, String service, String mark)throws ServiceException;

}
