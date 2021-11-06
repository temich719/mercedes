package service.impl;

import dao.OrderDAO;
import dao.daoFactory.DaoFactory;
import dao.entity.Order;
import dao.exception.DAOException;
import dao.impl.OrderDAOImpl;
import service.OrderService;
import service.exception.ServiceException;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO = DaoFactory.getINSTANCE().getOrderDAO();

    public OrderServiceImpl(){}

    @Override
    public void deleteOrder(Order order) throws ServiceException {
        try {
            orderDAO.deleteOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String getCountOfUnreadOrders(String email) throws ServiceException {
        try {
            return OrderDAOImpl.getCountOfUnreadOrders(email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addOrder(Order order) throws ServiceException {
        try {
            orderDAO.addOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void markAsRead(String name, String surname, String email, String service, String mark) throws ServiceException {
        try {
            OrderDAOImpl.markAsRead(name, surname, email, service, mark);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
