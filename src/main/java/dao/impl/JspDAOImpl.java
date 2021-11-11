package dao.impl;

import dao.*;
import dao.entity.AbstractCar;
import dao.entity.Order;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class JspDAOImpl extends AbstractDAO implements JspDAO {

    private static final String SELECT_FROM_MINIBUSES = "select * from minibuses";
    private static final String SELECT_FROM_CARS = "select * from cars";
    private static final String SELECT_FROM_TRUCKS = "select * from trucks";
    private static final String SELECT_FROM_ORDERS = "select * from orders;";

    public JspDAOImpl(ConnectionPool connectionPool){
        super(connectionPool);
    }

    @Override
    public ArrayList<Minibus> getMinibuses() throws DAOException {
        Connection connection = null;
        ArrayList<Minibus> minibuses = new ArrayList<>();
        try {
            connection = connectionPool.provide();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_MINIBUSES);
            while (resultSet.next()) {
                minibuses.add(new Minibus(resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }
        }
        catch (SQLException e){
            throw new DAOException("DAO exception", e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
        return minibuses;
    }

    @Override
    public ArrayList<Car> getCars() throws DAOException {
        Connection connection = null;
        ArrayList<Car> cars = new ArrayList<>();
        try {
            connection = connectionPool.provide();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_CARS);
            while (resultSet.next()) {
                cars.add(new Car(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                        resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
                        resultSet.getString(11), resultSet.getString(12)));
            }
        }
        catch (SQLException e){
            throw new DAOException("DAO exception", e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
        return cars;
    }

    @Override
    public ArrayList<Truck> getTrucks()throws DAOException{
        Connection connection = null;
        ArrayList<Truck> trucks = new ArrayList<>();
        try {
            connection = connectionPool.provide();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_TRUCKS);
            while (resultSet.next()) {
                trucks.add(new Truck(resultSet.getString(2), "73 000$", "img/truck.jpg"));
            }
        }
        catch (SQLException e){
            throw new DAOException("DAO exception", e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
        return trucks;
    }

    @Override
    public ArrayList<AbstractCar> getAllCars()throws DAOException{
        ArrayList<AbstractCar> allCars = new ArrayList<>();
        allCars.addAll(getCars());
        allCars.addAll(getMinibuses());
        allCars.addAll(getTrucks());
        return allCars;
    }

    @Override
    public ArrayList<Order> getListOfOrders() throws DAOException{
        Connection connection = null;
        ResultSet resultSet;
        ArrayList<Order> orders = new ArrayList<>();
        try {
            connection = connectionPool.provide();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_FROM_ORDERS);
            while (resultSet.next()) {
                orders.add(new Order(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                        resultSet.getString(8), resultSet.getString(9), resultSet.getString(10)));
            }
        }
        catch (SQLException e){
            throw new DAOException("DAO exception", e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
        Collections.reverse(orders);
        return orders;
    }

}