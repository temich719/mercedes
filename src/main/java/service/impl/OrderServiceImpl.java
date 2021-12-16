package service.impl;

import dao.OrderDAO;
import dao.daoFactory.DaoFactory;
import dao.entity.Order;
import dao.entity.Page;
import dao.exception.DAOException;
import service.OrderService;
import service.exception.ServiceException;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO = DaoFactory.getINSTANCE().getOrderDAO();

    public OrderServiceImpl() {
    }

    @Override
    public void deleteOrder(int id) throws ServiceException {
        try {
            orderDAO.deleteOrder(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String getCountOfUnreadOrders(String email) throws ServiceException {
        try {
            return orderDAO.getCountOfUnreadOrders(email);
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
    public Page<Order> getPageOfOrders(String pageNumber) throws ServiceException {
        try {
            return orderDAO.getPageOfOrders(pageNumber);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getListOfOrders() throws ServiceException {
        try {
            return orderDAO.getListOfOrders();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void markAdRead(int id) throws ServiceException {
        try {
            orderDAO.markAsRead(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
