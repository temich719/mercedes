package dao;

import dao.entity.Order;
import dao.exception.DAOException;

public interface OrderDAO {
    void addOrder(Order order)throws DAOException;
    void deleteOrder(Order order)throws DAOException;
}
