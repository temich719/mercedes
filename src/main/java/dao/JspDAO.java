package dao;

import dao.entity.AbstractCar;
import dao.entity.Order;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import dao.exception.DAOException;

import java.util.ArrayList;

public interface JspDAO {
    /**
     * find minibuses
     * @return list of minibuses
     * @throws DAOException is module exception
     */
    ArrayList<Minibus> getMinibuses() throws DAOException;

    /**
     * find cars
     * @return list of cars
     * @throws DAOException is module exception
     */
    ArrayList<Car> getCars() throws DAOException;

    /**
     * find trucks
     * @return list of trucks
     * @throws DAOException is module exception
     */
    ArrayList<Truck> getTrucks()throws DAOException;

    /**
     * find all cars including cars, minibuses and trucks
     * @return list of all cars
     * @throws DAOException is module exception
     */
    ArrayList<AbstractCar> getAllCars()throws DAOException;

    /**
     * find all orders
     * @return list of orders
     * @throws DAOException is module exception
     */
    ArrayList<Order> getListOfOrders() throws DAOException;
}
