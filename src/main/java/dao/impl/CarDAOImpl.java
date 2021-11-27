package dao.impl;

import dao.AbstractDAO;
import dao.CarDAO;
import dao.ConnectionPool;
import dao.daoFactory.DaoFactory;
import dao.entity.AbstractCar;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import dao.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class CarDAOImpl extends AbstractDAO implements CarDAO {

    private static final Logger logger = Logger.getLogger(CarDAOImpl.class);
    private static final String INSERT_CAR = "insert into cars(name_of_mark, price, power, acceleration_til_hundred, consumption, " +
            "engine_volume, tank_volume, trunk_volume, max_speed, image_path, type) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_MINIBUS = "insert into minibuses(name_of_mark, price, image_path, full_load, curb_weight)" +
            "values(?, ?, ?, ?, ?);";
    private static final String INSERT_CAR_NAME = "insert into allCars(allCars_name) values(?);";
    private static final String SELECT_FROM_ALL_CARS = "select * from allCars";
    private static final String DELETE_CAR = "delete from cars where name_of_mark = ?;";
    private static final String DELETE_MINIBUS = "delete from minibuses where name_of_mark = ?;";
    private static final String DELETE_CAR_NAME = "delete from allCars where allCars_name = ?;";
    private static final String UPDATE_CAR_INFO = "update cars set price = ?, power = ?, acceleration_til_hundred = ?," +
            " consumption = ?, engine_volume = ?, tank_volume = ?, trunk_volume = ?, max_speed = ?, image_path = ?, type = ? where" +
            " name_of_mark = ?;";
    private static final String SELECT_TYPES = "select * from types;";

    public CarDAOImpl(ConnectionPool connectionPool){
        super(connectionPool);
    }

    public static ArrayList<Car> getCars() throws DAOException {
        logger.info("Go to car page");
        return DaoFactory.getINSTANCE().getDataBase().getCars();
    }

    public static ArrayList<Minibus> getMinibuses() throws DAOException {
        logger.info("Go to minibus page");
        return DaoFactory.getINSTANCE().getDataBase().getMinibuses();
    }

    public static ArrayList<Truck> getTrucks() throws DAOException {
        logger.info("Go to truck page");
        return DaoFactory.getINSTANCE().getDataBase().getTrucks();
    }

    public static ArrayList<AbstractCar> getAllCars() throws DAOException {
        logger.info("Go to all cars page");
        return DaoFactory.getINSTANCE().getDataBase().getAllCars();
    }

    private boolean isExistsInCarNames(Connection connection, Car car)throws SQLException{
        ResultSet resultSet;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_ALL_CARS);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            if (resultSet.getString(1).equals(car.getNameOfMark()))return true;
        }
        return false;
    }

    private boolean isExistsInCarNames(Connection connection, Minibus minibus)throws SQLException{
        ResultSet resultSet;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_ALL_CARS);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            if (resultSet.getString(1).equals(minibus.getNameOfMark()))return true;
        }
        return false;
    }

    /*private String getMinibusMarkByImage(String imagePath) throws DAOException {
        String mark = null;
        for (Minibus minibus:getMinibuses()){
            if (minibus.getImagePath().equals(imagePath))mark = minibus.getNameOfMark();
        }
        return mark;
    }*/

    @Override
    public boolean isAllowedCarType(String type) throws DAOException {
        Connection connection = null;
        Statement statement;
        try {
            connection = connectionPool.provide();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_TYPES);
            while (resultSet.next()){
                if (resultSet.getString(2).equals(type))return true;
            }
        }
        catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
        return false;
    }

    @Override
    public void updateCarInfo(Car car) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(UPDATE_CAR_INFO);
            preparedStatement.setString(1, car.getPrice());
            preparedStatement.setString(2, car.getPower());
            preparedStatement.setString(3, car.getAccelerationTillHundred());
            preparedStatement.setString(4, car.getConsumption());
            preparedStatement.setString(5, car.getEngineVolume());
            preparedStatement.setString(6, car.getTankVolume());
            preparedStatement.setString(7, car.getTrunkVolume());
            preparedStatement.setString(8, car.getMaxSpeed());
            preparedStatement.setString(9, car.getImagePath());
            preparedStatement.setString(10, car.getType());
            preparedStatement.setString(11, car.getNameOfMark());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public Car getCarByMark(String mark) throws DAOException {
        Car result = null;
        for (Car car:getCars()) {
            if (car.getNameOfMark().equals(mark))result = car;
        }
        return result;
    }

    @Override
    public Car getCarByImage(String imagePath) throws DAOException {
        Car result = null;
        for (Car car:getCars()) {
            if (car.getImagePath().equals(imagePath))result = car;
        }
        return result;
    }

    @Override
    public void deleteMinibus(String mark) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(DELETE_MINIBUS);
            preparedStatement.setString(1, mark);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(DELETE_CAR_NAME);
            preparedStatement.setString(1, mark);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public void deleteCar(String mark) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(DELETE_CAR);
            preparedStatement.setString(1, mark);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(DELETE_CAR_NAME);
            preparedStatement.setString(1, mark);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public void addMinibus(Minibus minibus) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = connectionPool.provide();
            if (!isExistsInCarNames(connection, minibus)){
                preparedStatement = connection.prepareStatement(INSERT_CAR_NAME);
                preparedStatement.setString(1, minibus.getNameOfMark());
                preparedStatement.executeUpdate();
            }
            preparedStatement = connection.prepareStatement(INSERT_MINIBUS);
            preparedStatement.setString(1, minibus.getNameOfMark());
            preparedStatement.setString(2, minibus.getPrice());
            preparedStatement.setString(3, minibus.getImagePath());
            preparedStatement.setString(4, minibus.getFullLoad());
            preparedStatement.setString(5, minibus.getCurbWeight());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public void addCar(Car car)throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = connectionPool.provide();
            if (!isExistsInCarNames(connection, car)){
                preparedStatement = connection.prepareStatement(INSERT_CAR_NAME);
                preparedStatement.setString(1, car.getNameOfMark());
                preparedStatement.executeUpdate();
            }
            preparedStatement = connection.prepareStatement(INSERT_CAR);
            preparedStatement.setString(1, car.getNameOfMark());
            preparedStatement.setString(2, car.getPrice());
            preparedStatement.setString(3, car.getPower());
            preparedStatement.setString(4, car.getAccelerationTillHundred());
            preparedStatement.setString(5, car.getConsumption());
            preparedStatement.setString(6, car.getEngineVolume());
            preparedStatement.setString(7, car.getTankVolume());
            preparedStatement.setString(8, car.getTrunkVolume());
            preparedStatement.setString(9, car.getMaxSpeed());
            preparedStatement.setString(10, car.getImagePath());
            preparedStatement.setString(11, car.getType());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public String getCarMarkByImage(String imagePath) throws DAOException {
        String mark = null;
        for (Car car:getCars()){
            if (car.getImagePath().equals(imagePath))mark = car.getNameOfMark();
        }
        return mark;
    }

    @Override
    public ArrayList<Car> getCarListByType(String type) throws DAOException {
        ArrayList<Car> cars = new ArrayList<>();
        for (Car car:getCars()){
            if (car.getType().equals(type))cars.add(car);
        }
        return cars;
    }

    @Override
    public String[] getMarkAndPriceByImage(String imagePath) throws DAOException {
        String[] resultSet = new String[3];
        Car car = null;
        for (Car i : getCars()) {
            if (i.getImagePath().equals(imagePath)) {
                car = i;
                break;
            }
        }
        if (Objects.nonNull(car)){
            resultSet[0] = car.getNameOfMark();
            resultSet[1] = car.getPrice();
        }
        Minibus minibus = null;
        if (Objects.isNull(car)){
            for (Minibus i: getMinibuses()) {
                if (i.getImagePath().equals(imagePath)){
                    minibus = i;
                    break;
                }
            }
        }
        if (Objects.nonNull(minibus)){
            resultSet[0] = minibus.getNameOfMark();
            resultSet[2] = minibus.getPrice();
        }
        else if (Objects.isNull(car)){
            resultSet[0] = "Actros";
            resultSet[2] = "73 000$";
        }
        return resultSet;
    }

}
