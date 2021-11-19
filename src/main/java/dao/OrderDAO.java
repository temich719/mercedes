package dao;

import dao.entity.Order;
import dao.entity.User;
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

    /**
     * deletes orders of user who was deleted
     * @param user is the deleted user
     * @throws DAOException is a module exception
     */
    void deleteOrdersOfDeletedUser(User user)throws DAOException;
}
