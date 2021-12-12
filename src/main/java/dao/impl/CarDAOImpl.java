package dao.impl;

import dao.AbstractDAO;
import dao.CarDAO;
import dao.ConnectionPool;
import dao.entity.AbstractCar;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import dao.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class CarDAOImpl extends AbstractDAO implements CarDAO {

    private static final Logger LOGGER = Logger.getLogger(CarDAOImpl.class);
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
    private static final String SELECT_TYPE = "select * from types where type = ?";
    private static final String SELECT_FROM_MINIBUSES = "select * from minibuses";
    private static final String SELECT_FROM_CARS = "select * from cars";
    private static final String SELECT_FROM_TRUCKS = "select * from trucks";
    private static final String SELECT_COUNT_OF_ALL_CARS = "select count(*) from allCars";
    private static final String SELECT_ALL_CARS_INFO_FOR_ONE_PAGE = "select * from \n" +
            "\t(select car_id, name_of_mark, price, image_path from cars union select minibus_id, name_of_mark,price, image_path from minibuses\n" +
            "\t union select trucks_id, name_of_mark, price, image_path from trucks) as p \n" +
            "limit ? offset ?;";
    private static final String SELECT_CARS_INFO_FOR_ONE_PAGE = "select car_id, name_of_mark, price, image_path from cars limit ? offset ?";
    private static final String SELECT_MINIBUSES_INFO_FOR_ONE_PAGE = "select minibus_id, name_of_mark, price, image_path from minibuses limit ? offset ?";
    private static final String SELECT_COUNT_OF_CARS = "select count(*) from cars";
    private static final String SELECT_COUNT_OF_MINIBUSES = "select count(*) from minibuses";
    private static final String SELECT_INFO_OF_CARS_ACCORDING_TO_TYPE = "select car_id, name_of_mark, price, image_path from cars where type = ? limit ? offset ?;";
    private static final String SELECT_COUNT_OF_CARS_ACCORDING_TO_TYPE = "select count(*) from cars where type = ?;";
    private static final String SELECT_FROM_MINIBUSES_BY_ID = "select * from minibuses where minibus_id = ?;";
    private static final String SELECT_FROM_CARS_BY_ID = "select * from cars where car_id = ?;";
    private static final String SELECT_FROM_CARS_BY_IMAGE = "select * from cars where image_path = ?;";
    private static final String SELECT_FROM_MINIBUSES_BY_IMAGE = "select * from minibuses where image_path = ?;";
    private static final String SELECT_FROM_TRUCKS_BY_IMAGE = "select * from trucks where image_path = ?;";
    private static final String SELECT_CAR_BY_MARK = "select * from cars where name_of_mark = ?;";
    private static final int LIMIT = 6;

    public CarDAOImpl(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    public ArrayList<Car> getCars() throws DAOException {
        LOGGER.info("Go to car page");
        Connection connection = null;
        Statement statement = null;
        ArrayList<Car> cars = new ArrayList<>();
        try {
            connection = connectionPool.provide();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_CARS);
            while (resultSet.next()) {
                Car car = new Car(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                        resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
                        resultSet.getString(11), resultSet.getString(12));
                cars.add(car);
            }
        } catch (SQLException e) {
            throw new DAOException("DAO exception", e);
        } finally {
            close(statement);
            connectionPool.retrieve(connection);
        }
        return cars;
    }

    @Override
    public ArrayList<Minibus> getMinibuses() throws DAOException {
        LOGGER.info("Go to minibus page");
        Connection connection = null;
        Statement statement = null;
        ArrayList<Minibus> minibuses = new ArrayList<>();
        try {
            connection = connectionPool.provide();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_MINIBUSES);
            while (resultSet.next()) {
                minibuses.add(new Minibus(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }
        } catch (SQLException e) {
            throw new DAOException("DAO exception", e);
        } finally {
            close(statement);
            connectionPool.retrieve(connection);
        }
        return minibuses;
    }

    @Override
    public ArrayList<Truck> getTrucks() throws DAOException {
        LOGGER.info("Go to truck page");
        Connection connection = null;
        Statement statement = null;
        ArrayList<Truck> trucks = new ArrayList<>();
        try {
            connection = connectionPool.provide();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_TRUCKS);
            while (resultSet.next()) {
                trucks.add(new Truck(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }
        } catch (SQLException e) {
            throw new DAOException("DAO exception", e);
        } finally {
            close(statement);
            connectionPool.retrieve(connection);
        }
        return trucks;
    }

    @Override
    public ArrayList<AbstractCar> getAllCars() throws DAOException {
        LOGGER.info("Go to all cars page");
        ArrayList<AbstractCar> allCars = new ArrayList<>();
        Connection connection = null;
        PreparedStatement carStatement = null;
        PreparedStatement minibusStatement = null;
        PreparedStatement truckStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.provide();
            connection.setAutoCommit(false);
            carStatement = connection.prepareStatement(SELECT_FROM_CARS);
            resultSet = carStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                        resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
                        resultSet.getString(11), resultSet.getString(12));
                allCars.add(car);
            }
            minibusStatement = connection.prepareStatement(SELECT_FROM_MINIBUSES);
            resultSet = minibusStatement.executeQuery();
            while (resultSet.next()) {
                Minibus minibus = new Minibus(resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
                allCars.add(minibus);
            }
            truckStatement = connection.prepareStatement(SELECT_FROM_TRUCKS);
            resultSet = truckStatement.executeQuery();
            while (resultSet.next()) {
                Truck truck = new Truck(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                allCars.add(truck);
            }
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, truckStatement, minibusStatement, carStatement);
            connectionPool.retrieve(connection);
        }
        return allCars;
    }

    @Override
    public ArrayList<String> getCountOfAllCarPages() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> pageNumbers = new ArrayList<>();
        int count;
        int countOfPages;
        try {
            connection = connectionPool.provide();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_COUNT_OF_ALL_CARS);
            resultSet.next();
            count = resultSet.getInt(1);
            countOfPages = (int) Math.ceil((double) count / LIMIT);
            for (int i = 1; i <= countOfPages; i++) {
                pageNumbers.add(Integer.toString(i));
            }
            if (pageNumbers.size() == 1) {
                pageNumbers.clear();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, statement);
            connectionPool.retrieve(connection);
        }
        return pageNumbers;
    }

    @Override
    public ArrayList<String> getCountOfCarPages() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> pageNumbers = new ArrayList<>();
        int count;
        int countOfPages;
        try {
            connection = connectionPool.provide();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_COUNT_OF_CARS);
            resultSet.next();
            count = resultSet.getInt(1);
            countOfPages = (int) Math.ceil((double) count / LIMIT);
            for (int i = 1; i <= countOfPages; i++) {
                pageNumbers.add(Integer.toString(i));
            }
            if (pageNumbers.size() == 1) {
                pageNumbers.clear();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, statement);
            connectionPool.retrieve(connection);
        }
        return pageNumbers;
    }

    @Override
    public ArrayList<String> getCountOfMinibusPages() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> pageNumbers = new ArrayList<>();
        int count;
        int countOfPages;
        try {
            connection = connectionPool.provide();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_COUNT_OF_MINIBUSES);
            resultSet.next();
            count = resultSet.getInt(1);
            countOfPages = (int) Math.ceil((double) count / LIMIT);
            for (int i = 1; i <= countOfPages; i++) {
                pageNumbers.add(Integer.toString(i));
            }
            if (pageNumbers.size() == 1) {
                pageNumbers.clear();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, statement);
            connectionPool.retrieve(connection);
        }
        return pageNumbers;
    }

    @Override
    public ArrayList<String> getCountOfCarPagesAccordingToType(String carType) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<String> pageNumbers = new ArrayList<>();
        int count;
        int countOfPages;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(SELECT_COUNT_OF_CARS_ACCORDING_TO_TYPE);
            preparedStatement.setString(1, carType);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);
            countOfPages = (int) Math.ceil((double) count / LIMIT);
            for (int i = 1; i <= countOfPages; i++) {
                pageNumbers.add(Integer.toString(i));
            }
            if (pageNumbers.size() == 1) {
                pageNumbers.clear();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, preparedStatement);
            connectionPool.retrieve(connection);
        }
        return pageNumbers;
    }

    @Override
    public ArrayList<AbstractCar> getCarsInfoForOnePageAccordingToType(String pageNumber, String carType) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<AbstractCar> cars = new ArrayList<>();
        int offset = (Integer.parseInt(pageNumber) - 1) * LIMIT;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(SELECT_INFO_OF_CARS_ACCORDING_TO_TYPE);
            preparedStatement.setString(1, carType);
            preparedStatement.setInt(2, LIMIT);
            preparedStatement.setInt(3, offset);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AbstractCar abstractCar = new AbstractCar(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                cars.add(abstractCar);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, preparedStatement);
            connectionPool.retrieve(connection);
        }
        return cars;
    }

    @Override
    public ArrayList<AbstractCar> getCarsInfoForOnePage(String pageNumber) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<AbstractCar> cars = new ArrayList<>();
        int offset = (Integer.parseInt(pageNumber) - 1) * LIMIT;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(SELECT_CARS_INFO_FOR_ONE_PAGE);
            preparedStatement.setInt(1, LIMIT);
            preparedStatement.setInt(2, offset);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AbstractCar abstractCar = new AbstractCar(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                cars.add(abstractCar);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, preparedStatement);
            connectionPool.retrieve(connection);
        }
        return cars;
    }

    @Override
    public ArrayList<AbstractCar> getMinibusesInfoForOnePage(String pageNumber) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<AbstractCar> minibuses = new ArrayList<>();
        int offset = (Integer.parseInt(pageNumber) - 1) * LIMIT;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(SELECT_MINIBUSES_INFO_FOR_ONE_PAGE);
            preparedStatement.setInt(1, LIMIT);
            preparedStatement.setInt(2, offset);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AbstractCar abstractCar = new AbstractCar(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                minibuses.add(abstractCar);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, preparedStatement);
            connectionPool.retrieve(connection);
        }
        return minibuses;
    }

    @Override
    public Minibus getMinibusById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Minibus minibus;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(SELECT_FROM_MINIBUSES_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            minibus = new Minibus(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, preparedStatement);
            connectionPool.retrieve(connection);
        }
        return minibus;
    }

    @Override
    public ArrayList<AbstractCar> getAllCarsInfoForOnePage(String pageNumber) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<AbstractCar> allCars = new ArrayList<>();
        int offset = (Integer.parseInt(pageNumber) - 1) * LIMIT;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(SELECT_ALL_CARS_INFO_FOR_ONE_PAGE);
            preparedStatement.setInt(1, LIMIT);
            preparedStatement.setInt(2, offset);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AbstractCar abstractCar = new AbstractCar(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                allCars.add(abstractCar);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, preparedStatement);
            connectionPool.retrieve(connection);
        }
        return allCars;
    }

    @Override
    public boolean updateCarInfo(Car car) throws DAOException {
        Connection connection = null;
        PreparedStatement updateStatement = null;
        PreparedStatement getCarTypeStatement = null;
        ResultSet resultSet = null;
        boolean isUpdated = true;
        try {
            connection = connectionPool.provide();
            connection.setAutoCommit(false);
            getCarTypeStatement = connection.prepareStatement(SELECT_TYPE);
            getCarTypeStatement.setString(1, car.getType());
            resultSet = getCarTypeStatement.executeQuery();
            if (resultSet.next()) {
                updateStatement = connection.prepareStatement(UPDATE_CAR_INFO);
                updateStatement.setString(1, car.getPrice());
                updateStatement.setString(2, car.getPower());
                updateStatement.setString(3, car.getAccelerationTillHundred());
                updateStatement.setString(4, car.getConsumption());
                updateStatement.setString(5, car.getEngineVolume());
                updateStatement.setString(6, car.getTankVolume());
                updateStatement.setString(7, car.getTrunkVolume());
                updateStatement.setString(8, car.getMaxSpeed());
                updateStatement.setString(9, car.getImagePath());
                updateStatement.setString(10, car.getType());
                updateStatement.setString(11, car.getNameOfMark());
                updateStatement.executeUpdate();
            } else {
                isUpdated = false;
            }
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, getCarTypeStatement, updateStatement);
            connectionPool.retrieve(connection);
        }
        return isUpdated;
    }

    @Override
    public Car getCarByMark(String mark) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Car car;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(SELECT_CAR_BY_MARK);
            preparedStatement.setString(1, mark);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            car = new Car(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getString(12));
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, preparedStatement);
            connectionPool.retrieve(connection);
        }
        return car;
    }

    @Override
    public Car getCarById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Car car;
        try {
            connection = connectionPool.provide();
            preparedStatement = connection.prepareStatement(SELECT_FROM_CARS_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            car = new Car(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getString(12));
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet, preparedStatement);
            connectionPool.retrieve(connection);
        }
        return car;
    }

    @Override
    public AbstractCar getAnyCarByImage(String imagePath) throws DAOException {
        Connection connection = null;
        PreparedStatement carStatement = null;
        PreparedStatement minibusStatement = null;
        PreparedStatement truckStatement = null;
        ResultSet carSet = null;
        ResultSet minibusSet = null;
        ResultSet truckSet = null;
        AbstractCar abstractCar;
        try {
            connection = connectionPool.provide();
            connection.setAutoCommit(false);
            carStatement = connection.prepareStatement(SELECT_FROM_CARS_BY_IMAGE);
            carStatement.setString(1, imagePath);
            carSet = carStatement.executeQuery();
            if (carSet.next()) {
                abstractCar = new AbstractCar(carSet.getInt(1), carSet.getString(2), carSet.getString(3), carSet.getString(4));
            } else {
                minibusStatement = connection.prepareStatement(SELECT_FROM_MINIBUSES_BY_IMAGE);
                minibusStatement.setString(1, imagePath);
                minibusSet = minibusStatement.executeQuery();
                if (minibusSet.next()) {
                    abstractCar = new AbstractCar(minibusSet.getInt(1), minibusSet.getString(2), minibusSet.getString(3), minibusSet.getString(4));
                } else {
                    truckStatement = connection.prepareStatement(SELECT_FROM_TRUCKS_BY_IMAGE);
                    truckStatement.setString(1, imagePath);
                    truckSet = truckStatement.executeQuery();
                    truckSet.next();
                    abstractCar = new AbstractCar(truckSet.getInt(1), truckSet.getString(2), truckSet.getString(3), truckSet.getString(4));
                }
            }
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(truckSet, minibusSet, truckStatement, minibusStatement, carSet, carStatement);
            connectionPool.retrieve(connection);
        }
        return abstractCar;
    }

    @Override
    public void deleteMinibus(String mark) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.provide();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_MINIBUS);
            preparedStatement.setString(1, mark);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(DELETE_CAR_NAME);
            preparedStatement.setString(1, mark);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(preparedStatement);
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public void deleteCar(String mark) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.provide();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_CAR);
            preparedStatement.setString(1, mark);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(DELETE_CAR_NAME);
            preparedStatement.setString(1, mark);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(preparedStatement);
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public void addMinibus(Minibus minibus) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.provide();
            connection.setAutoCommit(false);
            if (!isExistsInCarNames(connection, minibus)) {
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
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(preparedStatement);
            connectionPool.retrieve(connection);
        }
    }

    @Override
    public void addCar(Car car) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.provide();
            connection.setAutoCommit(false);
            if (!isExistsInCarNames(connection, car)) {
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
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(preparedStatement);
            connectionPool.retrieve(connection);
        }
    }

    private boolean isExistsInCarNames(Connection connection, Car car) throws SQLException {
        ResultSet resultSet;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_ALL_CARS);
        resultSet = preparedStatement.executeQuery();
        boolean isExists = false;
        while (resultSet.next()) {
            if (resultSet.getString(2).equals(car.getNameOfMark())) {
                isExists = true;
                break;
            }
        }
        return isExists;
    }

    private boolean isExistsInCarNames(Connection connection, Minibus minibus) throws SQLException {
        ResultSet resultSet;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_ALL_CARS);
        resultSet = preparedStatement.executeQuery();
        boolean isExists = false;
        while (resultSet.next()) {
            if (resultSet.getString(2).equals(minibus.getNameOfMark())) {
                isExists = true;
                break;
            }
        }
        return isExists;
    }

}
