package dao;

import dao.entity.AbstractCar;
import dao.entity.Order;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import dao.exception.DAOException;

import java.util.ArrayList;

public interface JspDAO {
    ArrayList<Minibus> getMinibuses() throws DAOException;
    ArrayList<Car> getCars() throws DAOException;
    ArrayList<Truck> getTrucks()throws DAOException;
    ArrayList<AbstractCar> getAllCars()throws DAOException;
    ArrayList<Order> getListOfOrders() throws DAOException;
}
