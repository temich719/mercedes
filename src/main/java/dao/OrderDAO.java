package dao;

import dao.entity.Order;
import dao.exception.DAOException;

public interface OrderDAO {
    /**
     * adds new order in database
     * @param order is a order
     * @throws DAOException is module exception
     */
    void addOrder(Order order)throws DAOException;

    /**
     * deletes definite order
     * @param order is a order
     * @throws DAOException is module exception
     */
    void deleteOrder(Order order)throws DAOException;
}
