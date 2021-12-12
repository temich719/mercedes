package service.impl;

import dao.OrderDAO;
import dao.daoFactory.DaoFactory;
import dao.entity.Order;
import dao.entity.User;
import dao.exception.DAOException;
import service.OrderService;
import service.exception.ServiceException;

import java.util.ArrayList;

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
    public ArrayList<String> getCountOfOrdersPages() throws ServiceException {
        try {
            return orderDAO.getCountOfOrdersPages();
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
    public ArrayList<Order> getOrderInfoForOnePage(String pageNumber) throws ServiceException {
        try {
            return orderDAO.getOrderInfoForOnePage(pageNumber);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<Order> getListOfOrders() throws ServiceException {
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

    @Override
    public void deleteOrdersOfDeletedUser(User user) throws ServiceException {
        try {
            orderDAO.deleteOrdersOfDeletedUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
