package dao.impl;

import dao.CarDAO;
import dao.ConnectionPool;
import dao.daoFactory.DaoFactory;
import dao.entity.AbstractCar;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import dao.exception.DAOException;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.*;
import java.util.ArrayList;

public class CarDAOImplTest {

    @Test
    public void testGetCars()throws DAOException, SQLException{
        String SELECT_FROM_CARS = "select * from cars";
        DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        ArrayList<Car> cars = Mockito.mock(ArrayList.class);
        JspDAOImpl jspDAO = Mockito.mock(JspDAOImpl.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        Mockito.when(daoFactory.getJSPDao()).thenReturn(jspDAO);
        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_CARS)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn(null);
        Mockito.when(resultSet.getString(3)).thenReturn(null);
        Mockito.when(resultSet.getString(4)).thenReturn(null);
        Mockito.when(resultSet.getString(5)).thenReturn(null);
        Mockito.when(resultSet.getString(6)).thenReturn(null);
        Mockito.when(resultSet.getString(7)).thenReturn(null);
        Mockito.when(resultSet.getString(8)).thenReturn(null);
        Mockito.when(resultSet.getString(9)).thenReturn(null);
        Mockito.when(resultSet.getString(10)).thenReturn(null);
        Mockito.when(resultSet.getString(11)).thenReturn(null);
        Mockito.when(resultSet.getString(12)).thenReturn(null);
        Mockito.when(cars.add(new Car(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
                resultSet.getString(11), resultSet.getString(12)))).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        CarDAOImpl.getCars();

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_CARS);
        Mockito.verify(resultSet, Mockito.times(2)).next();
        Mockito.verify(resultSet, Mockito.times(2)).getString(2);
        Mockito.verify(resultSet, Mockito.times(2)).getString(3);
        Mockito.verify(resultSet, Mockito.times(2)).getString(4);
        Mockito.verify(resultSet, Mockito.times(2)).getString(5);
        Mockito.verify(resultSet, Mockito.times(2)).getString(6);
        Mockito.verify(resultSet, Mockito.times(2)).getString(7);
        Mockito.verify(resultSet, Mockito.times(2)).getString(8);
        Mockito.verify(resultSet, Mockito.times(2)).getString(9);
        Mockito.verify(resultSet, Mockito.times(2)).getString(10);
        Mockito.verify(resultSet, Mockito.times(2)).getString(11);
        Mockito.verify(resultSet, Mockito.times(2)).getString(12);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testGetCars_DAOException()throws DAOException, SQLException{
        String SELECT_FROM_CARS = "select * from cars";
        DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        ArrayList<Car> cars = Mockito.mock(ArrayList.class);
        JspDAOImpl jspDAO = Mockito.mock(JspDAOImpl.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        Mockito.when(daoFactory.getJSPDao()).thenReturn(jspDAO);
        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_CARS)).thenThrow(SQLException.class);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn(null);
        Mockito.when(resultSet.getString(3)).thenReturn(null);
        Mockito.when(resultSet.getString(4)).thenReturn(null);
        Mockito.when(resultSet.getString(5)).thenReturn(null);
        Mockito.when(resultSet.getString(6)).thenReturn(null);
        Mockito.when(resultSet.getString(7)).thenReturn(null);
        Mockito.when(resultSet.getString(8)).thenReturn(null);
        Mockito.when(resultSet.getString(9)).thenReturn(null);
        Mockito.when(resultSet.getString(10)).thenReturn(null);
        Mockito.when(resultSet.getString(11)).thenReturn(null);
        Mockito.when(resultSet.getString(12)).thenReturn(null);
        Mockito.when(cars.add(new Car(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
                resultSet.getString(11), resultSet.getString(12)))).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        CarDAOImpl.getCars();

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_CARS);
        Mockito.verify(resultSet, Mockito.times(2)).next();
        Mockito.verify(resultSet, Mockito.times(2)).getString(2);
        Mockito.verify(resultSet, Mockito.times(2)).getString(3);
        Mockito.verify(resultSet, Mockito.times(2)).getString(4);
        Mockito.verify(resultSet, Mockito.times(2)).getString(5);
        Mockito.verify(resultSet, Mockito.times(2)).getString(6);
        Mockito.verify(resultSet, Mockito.times(2)).getString(7);
        Mockito.verify(resultSet, Mockito.times(2)).getString(8);
        Mockito.verify(resultSet, Mockito.times(2)).getString(9);
        Mockito.verify(resultSet, Mockito.times(2)).getString(10);
        Mockito.verify(resultSet, Mockito.times(2)).getString(11);
        Mockito.verify(resultSet, Mockito.times(2)).getString(12);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testGetMinibuses()throws DAOException, SQLException{
        String SELECT_FROM_MINIBUSES = "select * from minibuses";
        DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        ArrayList<Minibus> minibuses = Mockito.mock(ArrayList.class);
        JspDAOImpl jspDAO = Mockito.mock(JspDAOImpl.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        Mockito.when(daoFactory.getJSPDao()).thenReturn(jspDAO);
        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_MINIBUSES)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn(null);
        Mockito.when(resultSet.getString(3)).thenReturn(null);
        Mockito.when(resultSet.getString(4)).thenReturn(null);
        Mockito.when(resultSet.getString(5)).thenReturn(null);
        Mockito.when(resultSet.getString(6)).thenReturn(null);
        Mockito.when(minibuses.add(new Minibus(resultSet.getString(2), resultSet.getString(3),
                resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)))).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        CarDAOImpl.getMinibuses();

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_MINIBUSES);
        Mockito.verify(resultSet, Mockito.times(2)).next();
        Mockito.verify(resultSet, Mockito.times(2)).getString(2);
        Mockito.verify(resultSet, Mockito.times(2)).getString(3);
        Mockito.verify(resultSet, Mockito.times(2)).getString(4);
        Mockito.verify(resultSet, Mockito.times(2)).getString(5);
        Mockito.verify(resultSet, Mockito.times(2)).getString(6);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testGetMinibuses_DAOException()throws DAOException, SQLException{
        String SELECT_FROM_MINIBUSES = "select * from minibuses";
        DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        ArrayList<Minibus> minibuses = Mockito.mock(ArrayList.class);
        JspDAOImpl jspDAO = Mockito.mock(JspDAOImpl.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        Mockito.when(daoFactory.getJSPDao()).thenReturn(jspDAO);
        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_MINIBUSES)).thenThrow(SQLException.class);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn(null);
        Mockito.when(resultSet.getString(3)).thenReturn(null);
        Mockito.when(resultSet.getString(4)).thenReturn(null);
        Mockito.when(resultSet.getString(5)).thenReturn(null);
        Mockito.when(resultSet.getString(6)).thenReturn(null);
        Mockito.when(minibuses.add(new Minibus(resultSet.getString(2), resultSet.getString(3),
                resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)))).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        CarDAOImpl.getMinibuses();

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_MINIBUSES);
        Mockito.verify(resultSet, Mockito.times(2)).next();
        Mockito.verify(resultSet, Mockito.times(2)).getString(2);
        Mockito.verify(resultSet, Mockito.times(2)).getString(3);
        Mockito.verify(resultSet, Mockito.times(2)).getString(4);
        Mockito.verify(resultSet, Mockito.times(2)).getString(5);
        Mockito.verify(resultSet, Mockito.times(2)).getString(6);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testGetTrucks()throws DAOException, SQLException{
        String SELECT_FROM_TRUCKS = "select * from trucks";
        DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        ArrayList<Truck> trucks = Mockito.mock(ArrayList.class);
        JspDAOImpl jspDAO = Mockito.mock(JspDAOImpl.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        Mockito.when(daoFactory.getJSPDao()).thenReturn(jspDAO);
        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_TRUCKS)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn(null);
        Mockito.when(trucks.add(new Truck(resultSet.getString(2), "73 000$", "img/truck.jpg"))).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        CarDAOImpl.getTrucks();

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_TRUCKS);
        Mockito.verify(resultSet, Mockito.times(2)).next();
        Mockito.verify(resultSet, Mockito.times(2)).getString(2);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testGetTrucks_DAOException()throws DAOException, SQLException{
        String SELECT_FROM_TRUCKS = "select * from trucks";
        DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        ArrayList<Truck> trucks = Mockito.mock(ArrayList.class);
        JspDAOImpl jspDAO = Mockito.mock(JspDAOImpl.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        Mockito.when(daoFactory.getJSPDao()).thenReturn(jspDAO);
        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_TRUCKS)).thenThrow(SQLException.class);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn(null);
        Mockito.when(trucks.add(new Truck(resultSet.getString(2), "73 000$", "img/truck.jpg"))).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        CarDAOImpl.getTrucks();

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_TRUCKS);
        Mockito.verify(resultSet, Mockito.times(2)).next();
        Mockito.verify(resultSet, Mockito.times(2)).getString(2);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testGetAllCars()throws DAOException, SQLException{
        ArrayList<AbstractCar> abstractCars = Mockito.mock(ArrayList.class);
        ArrayList<Car> cars = Mockito.mock(ArrayList.class);
        ArrayList<Minibus> minibuses = Mockito.mock(ArrayList.class);
        ArrayList<Truck> trucks = Mockito.mock(ArrayList.class);
        DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        JspDAOImpl jspDAO = Mockito.mock(JspDAOImpl.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        String SELECT_FROM_TRUCKS = "select * from trucks";
        String SELECT_FROM_MINIBUSES = "select * from minibuses";
        String SELECT_FROM_CARS = "select * from cars";

        Mockito.when(daoFactory.getJSPDao()).thenReturn(jspDAO);
        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(jspDAO.getAllCars()).thenReturn(abstractCars);

        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_CARS)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn(null);
        Mockito.when(resultSet.getString(3)).thenReturn(null);
        Mockito.when(resultSet.getString(4)).thenReturn(null);
        Mockito.when(resultSet.getString(5)).thenReturn(null);
        Mockito.when(resultSet.getString(6)).thenReturn(null);
        Mockito.when(resultSet.getString(7)).thenReturn(null);
        Mockito.when(resultSet.getString(8)).thenReturn(null);
        Mockito.when(resultSet.getString(9)).thenReturn(null);
        Mockito.when(resultSet.getString(10)).thenReturn(null);
        Mockito.when(resultSet.getString(11)).thenReturn(null);
        Mockito.when(resultSet.getString(12)).thenReturn(null);
        Mockito.when(cars.add(new Car(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
                resultSet.getString(11), resultSet.getString(12)))).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.doNothing().when(connectionPool).retrieve(connection);
        Mockito.when(statement.executeQuery(SELECT_FROM_MINIBUSES)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(minibuses.add(new Minibus(resultSet.getString(2), resultSet.getString(3),
                resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)))).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(statement.executeQuery(SELECT_FROM_TRUCKS)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(trucks.add(new Truck(resultSet.getString(2), "73 000$", "img/truck.jpg"))).thenReturn(Boolean.TRUE, Boolean.FALSE);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        CarDAOImpl.getAllCars();

        Mockito.verify(connectionPool, Mockito.times(3)).provide();

        Mockito.verify(connection, Mockito.times(3)).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_CARS);
        Mockito.verify(resultSet, Mockito.times(4)).next();
        Mockito.verify(resultSet, Mockito.times(4)).getString(2);
        Mockito.verify(resultSet, Mockito.times(3)).getString(3);
        Mockito.verify(resultSet, Mockito.times(3)).getString(4);
        Mockito.verify(resultSet, Mockito.times(3)).getString(5);
        Mockito.verify(resultSet, Mockito.times(3)).getString(6);
        Mockito.verify(resultSet, Mockito.times(2)).getString(7);
        Mockito.verify(resultSet, Mockito.times(2)).getString(8);
        Mockito.verify(resultSet, Mockito.times(2)).getString(9);
        Mockito.verify(resultSet, Mockito.times(2)).getString(10);
        Mockito.verify(resultSet, Mockito.times(2)).getString(11);
        Mockito.verify(resultSet, Mockito.times(2)).getString(12);
        Mockito.verify(connectionPool, Mockito.times(3)).retrieve(connection);
        Mockito.verify(statement).executeQuery(SELECT_FROM_TRUCKS);
        Mockito.verify(statement).executeQuery(SELECT_FROM_MINIBUSES);
    }

    @Test
    public void testIsAllowedCarType()throws DAOException, SQLException{
        String type = "sedan";
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        String SELECT_TYPES = "select * from types;";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_TYPES)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn("sedan");
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        carDAO.isAllowedCarType(type);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_TYPES);
        Mockito.verify(resultSet).next();
        Mockito.verify(resultSet).getString(2);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testIsAllowedCarType_DAOException()throws DAOException, SQLException{
        String type = "sedan";
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        String SELECT_TYPES = "select * from types;";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_TYPES)).thenThrow(SQLException.class);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn("sedan");
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        carDAO.isAllowedCarType(type);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_TYPES);
        Mockito.verify(resultSet).next();
        Mockito.verify(resultSet).getString(2);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testUpdateCarInfo_DAOException()throws DAOException, SQLException{
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        String UPDATE_CAR_INFO = "update cars set price = ?, power = ?, acceleration_til_hundred = ?," +
                " consumption = ?, engine_volume = ?, tank_volume = ?, trunk_volume = ?, max_speed = ?, image_path = ?, type = ? where" +
                " name_of_mark = ?;";
        Car car = new Car("C-Class", "100 000$", "420", "10","12", "5", "50", "300", "220", "img/img.png", "sedan");

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(UPDATE_CAR_INFO)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, car.getPrice());
        Mockito.doNothing().when(preparedStatement).setString(2, car.getPower());
        Mockito.doNothing().when(preparedStatement).setString(3, car.getAccelerationTillHundred());
        Mockito.doNothing().when(preparedStatement).setString(4, car.getConsumption());
        Mockito.doNothing().when(preparedStatement).setString(5, car.getEngineVolume());
        Mockito.doNothing().when(preparedStatement).setString(6, car.getTankVolume());
        Mockito.doNothing().when(preparedStatement).setString(7, car.getTrunkVolume());
        Mockito.doNothing().when(preparedStatement).setString(8, car.getMaxSpeed());
        Mockito.doNothing().when(preparedStatement).setString(9, car.getImagePath());
        Mockito.doNothing().when(preparedStatement).setString(10, car.getType());
        Mockito.doNothing().when(preparedStatement).setString(11, car.getNameOfMark());
        Mockito.when(preparedStatement.executeUpdate()).thenThrow(SQLException.class);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        carDAO.updateCarInfo(car);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(UPDATE_CAR_INFO);
        Mockito.verify(preparedStatement).setString(1, car.getPrice());
        Mockito.verify(preparedStatement).setString(2, car.getPower());
        Mockito.verify(preparedStatement).setString(3, car.getAccelerationTillHundred());
        Mockito.verify(preparedStatement).setString(4, car.getConsumption());
        Mockito.verify(preparedStatement).setString(5, car.getEngineVolume());
        Mockito.verify(preparedStatement).setString(6, car.getTankVolume());
        Mockito.verify(preparedStatement).setString(7, car.getTrunkVolume());
        Mockito.verify(preparedStatement).setString(8, car.getMaxSpeed());
        Mockito.verify(preparedStatement).setString(9, car.getImagePath());
        Mockito.verify(preparedStatement).setString(10, car.getType());
        Mockito.verify(preparedStatement).setString(11, car.getNameOfMark());
        Mockito.verify(preparedStatement).executeUpdate();
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testUpdateCarInfo()throws DAOException, SQLException{
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        String UPDATE_CAR_INFO = "update cars set price = ?, power = ?, acceleration_til_hundred = ?," +
                " consumption = ?, engine_volume = ?, tank_volume = ?, trunk_volume = ?, max_speed = ?, image_path = ?, type = ? where" +
                " name_of_mark = ?;";
        Car car = new Car("C-Class", "100 000$", "420", "10","12", "5", "50", "300", "220", "img/img.png", "sedan");

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(UPDATE_CAR_INFO)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, car.getPrice());
        Mockito.doNothing().when(preparedStatement).setString(2, car.getPower());
        Mockito.doNothing().when(preparedStatement).setString(3, car.getAccelerationTillHundred());
        Mockito.doNothing().when(preparedStatement).setString(4, car.getConsumption());
        Mockito.doNothing().when(preparedStatement).setString(5, car.getEngineVolume());
        Mockito.doNothing().when(preparedStatement).setString(6, car.getTankVolume());
        Mockito.doNothing().when(preparedStatement).setString(7, car.getTrunkVolume());
        Mockito.doNothing().when(preparedStatement).setString(8, car.getMaxSpeed());
        Mockito.doNothing().when(preparedStatement).setString(9, car.getImagePath());
        Mockito.doNothing().when(preparedStatement).setString(10, car.getType());
        Mockito.doNothing().when(preparedStatement).setString(11, car.getNameOfMark());
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        carDAO.updateCarInfo(car);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(UPDATE_CAR_INFO);
        Mockito.verify(preparedStatement).setString(1, car.getPrice());
        Mockito.verify(preparedStatement).setString(2, car.getPower());
        Mockito.verify(preparedStatement).setString(3, car.getAccelerationTillHundred());
        Mockito.verify(preparedStatement).setString(4, car.getConsumption());
        Mockito.verify(preparedStatement).setString(5, car.getEngineVolume());
        Mockito.verify(preparedStatement).setString(6, car.getTankVolume());
        Mockito.verify(preparedStatement).setString(7, car.getTrunkVolume());
        Mockito.verify(preparedStatement).setString(8, car.getMaxSpeed());
        Mockito.verify(preparedStatement).setString(9, car.getImagePath());
        Mockito.verify(preparedStatement).setString(10, car.getType());
        Mockito.verify(preparedStatement).setString(11, car.getNameOfMark());
        Mockito.verify(preparedStatement).executeUpdate();
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testGetCarByMark()throws DAOException, SQLException{
        String mark = "C-Class";
        String SELECT_FROM_CARS = "select * from cars";
        DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        ArrayList<Car> cars = Mockito.mock(ArrayList.class);
        JspDAOImpl jspDAO = Mockito.mock(JspDAOImpl.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Car car = new Car("C-Class", "100 000$", "420", "10","12", "5", "50", "300", "220", "img/img.png", "sedan");

        Mockito.when(daoFactory.getJSPDao()).thenReturn(jspDAO);
        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_CARS)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn("C-Class");
        Mockito.when(resultSet.getString(3)).thenReturn("C-Class");
        Mockito.when(resultSet.getString(4)).thenReturn("C-Class");
        Mockito.when(resultSet.getString(5)).thenReturn("C-Class");
        Mockito.when(resultSet.getString(6)).thenReturn("C-Class");
        Mockito.when(resultSet.getString(7)).thenReturn("C-Class");
        Mockito.when(resultSet.getString(8)).thenReturn("C-Class");
        Mockito.when(resultSet.getString(9)).thenReturn("C-Class");
        Mockito.when(resultSet.getString(10)).thenReturn("C-Class");
        Mockito.when(resultSet.getString(11)).thenReturn("C-Class");
        Mockito.when(resultSet.getString(12)).thenReturn("C-Class");
        Mockito.when(cars.add(new Car(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
                resultSet.getString(11), resultSet.getString(12)))).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        carDAO.getCarByMark(mark);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_CARS);
        Mockito.verify(resultSet, Mockito.times(2)).next();
        Mockito.verify(resultSet, Mockito.times(2)).getString(2);
        Mockito.verify(resultSet, Mockito.times(2)).getString(3);
        Mockito.verify(resultSet, Mockito.times(2)).getString(4);
        Mockito.verify(resultSet, Mockito.times(2)).getString(5);
        Mockito.verify(resultSet, Mockito.times(2)).getString(6);
        Mockito.verify(resultSet, Mockito.times(2)).getString(7);
        Mockito.verify(resultSet, Mockito.times(2)).getString(8);
        Mockito.verify(resultSet, Mockito.times(2)).getString(9);
        Mockito.verify(resultSet, Mockito.times(2)).getString(10);
        Mockito.verify(resultSet, Mockito.times(2)).getString(11);
        Mockito.verify(resultSet, Mockito.times(2)).getString(12);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testGetCarByImage()throws DAOException, SQLException{
        String imagePath = "img/img.png";
        String SELECT_FROM_CARS = "select * from cars";
        DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        ArrayList<Car> cars = Mockito.mock(ArrayList.class);
        JspDAOImpl jspDAO = Mockito.mock(JspDAOImpl.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Car car = new Car("C-Class", "100 000$", "420", "10","12", "5", "50", "300", "220", "img/img.png", "sedan");

        Mockito.when(daoFactory.getJSPDao()).thenReturn(jspDAO);
        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_CARS)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(3)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(4)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(5)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(6)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(7)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(8)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(9)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(10)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(11)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(12)).thenReturn("img/img.png");
        Mockito.when(cars.add(new Car(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
                resultSet.getString(11), resultSet.getString(12)))).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        carDAO.getCarByImage(imagePath);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_CARS);
        Mockito.verify(resultSet, Mockito.times(2)).next();
        Mockito.verify(resultSet, Mockito.times(2)).getString(2);
        Mockito.verify(resultSet, Mockito.times(2)).getString(3);
        Mockito.verify(resultSet, Mockito.times(2)).getString(4);
        Mockito.verify(resultSet, Mockito.times(2)).getString(5);
        Mockito.verify(resultSet, Mockito.times(2)).getString(6);
        Mockito.verify(resultSet, Mockito.times(2)).getString(7);
        Mockito.verify(resultSet, Mockito.times(2)).getString(8);
        Mockito.verify(resultSet, Mockito.times(2)).getString(9);
        Mockito.verify(resultSet, Mockito.times(2)).getString(10);
        Mockito.verify(resultSet, Mockito.times(2)).getString(11);
        Mockito.verify(resultSet, Mockito.times(2)).getString(12);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testDeleteMinibus()throws DAOException, SQLException{
        String mark = "Vito";
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        String DELETE_MINIBUS = "delete from minibuses where name_of_mark = ?;";
        String DELETE_CAR_NAME = "delete from allCars where allCars_name = ?;";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(DELETE_MINIBUS)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, mark);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(connection.prepareStatement(DELETE_CAR_NAME)).thenReturn(preparedStatement);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        carDAO.deleteMinibus(mark);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(DELETE_MINIBUS);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(1, mark);
        Mockito.verify(preparedStatement, Mockito.times(2)).executeUpdate();
        Mockito.verify(connection).prepareStatement(DELETE_CAR_NAME);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testDeleteMinibus_DAOException()throws DAOException, SQLException{
        String mark = "Vito";
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        String DELETE_MINIBUS = "delete from minibuses where name_of_mark = ?;";
        String DELETE_CAR_NAME = "delete from allCars where allCars_name = ?;";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(DELETE_MINIBUS)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, mark);
        Mockito.when(preparedStatement.executeUpdate()).thenThrow(SQLException.class);
        Mockito.when(connection.prepareStatement(DELETE_CAR_NAME)).thenReturn(preparedStatement);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        carDAO.deleteMinibus(mark);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(DELETE_MINIBUS);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(1, mark);
        Mockito.verify(preparedStatement, Mockito.times(2)).executeUpdate();
        Mockito.verify(connection).prepareStatement(DELETE_CAR_NAME);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testDeleteCar()throws DAOException, SQLException{
        String mark = "C-Class";
        String DELETE_CAR = "delete from cars where name_of_mark = ?;";
        String DELETE_CAR_NAME = "delete from allCars where allCars_name = ?;";
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(DELETE_CAR)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, mark);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(connection.prepareStatement(DELETE_CAR_NAME)).thenReturn(preparedStatement);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        carDAO.deleteCar(mark);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(DELETE_CAR);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(1, mark);
        Mockito.verify(preparedStatement, Mockito.times(2)).executeUpdate();
        Mockito.verify(connection).prepareStatement(DELETE_CAR_NAME);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testDeleteCar_DAOException()throws DAOException, SQLException{
        String mark = "C-Class";
        String DELETE_CAR = "delete from cars where name_of_mark = ?;";
        String DELETE_CAR_NAME = "delete from allCars where allCars_name = ?;";
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(DELETE_CAR)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, mark);
        Mockito.when(preparedStatement.executeUpdate()).thenThrow(SQLException.class);
        Mockito.when(connection.prepareStatement(DELETE_CAR_NAME)).thenReturn(preparedStatement);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        carDAO.deleteCar(mark);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(DELETE_CAR);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(1, mark);
        Mockito.verify(preparedStatement, Mockito.times(2)).executeUpdate();
        Mockito.verify(connection).prepareStatement(DELETE_CAR_NAME);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testAddMinibus()throws DAOException, SQLException{
        Minibus minibus = new Minibus("Sprinter", "30 000$", "img/img.png", "200", "220");
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        String SELECT_FROM_ALL_CARS = "select * from allCars";
        String INSERT_MINIBUS = "insert into minibuses(name_of_mark, price, image_path, full_load, curb_weight)" +
                "values(?, ?, ?, ?, ?);";
        String INSERT_CAR_NAME = "insert into allCars(allCars_name) values(?);";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(SELECT_FROM_ALL_CARS)).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(1)).thenReturn("Vito");
        Mockito.when(connection.prepareStatement(INSERT_CAR_NAME)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, minibus.getNameOfMark());
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(connection.prepareStatement(INSERT_MINIBUS)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, minibus.getNameOfMark());
        Mockito.doNothing().when(preparedStatement).setString(2, minibus.getPrice());
        Mockito.doNothing().when(preparedStatement).setString(3, minibus.getImagePath());
        Mockito.doNothing().when(preparedStatement).setString(4, minibus.getFullLoad());
        Mockito.doNothing().when(preparedStatement).setString(5, minibus.getCurbWeight());
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        carDAO.addMinibus(minibus);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(SELECT_FROM_ALL_CARS);
        Mockito.verify(preparedStatement).executeQuery();
        Mockito.verify(resultSet, Mockito.times(2)).next();
        Mockito.verify(resultSet).getString(1);
        Mockito.verify(connection).prepareStatement(INSERT_CAR_NAME);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(1, minibus.getNameOfMark());
        Mockito.verify(preparedStatement, Mockito.times(2)).executeUpdate();
        Mockito.verify(connection).prepareStatement(INSERT_MINIBUS);
        Mockito.verify(preparedStatement).setString(2, minibus.getPrice());
        Mockito.verify(preparedStatement).setString(3, minibus.getImagePath());
        Mockito.verify(preparedStatement).setString(4, minibus.getFullLoad());
        Mockito.verify(preparedStatement).setString(5, minibus.getCurbWeight());
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testAddMinibus_DAOException()throws DAOException, SQLException{
        Minibus minibus = new Minibus("Sprinter", "30 000$", "img/img.png", "200", "220");
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        String SELECT_FROM_ALL_CARS = "select * from allCars";
        String INSERT_MINIBUS = "insert into minibuses(name_of_mark, price, image_path, full_load, curb_weight)" +
                "values(?, ?, ?, ?, ?);";
        String INSERT_CAR_NAME = "insert into allCars(allCars_name) values(?);";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(SELECT_FROM_ALL_CARS)).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(1)).thenReturn("Vito");
        Mockito.when(connection.prepareStatement(INSERT_CAR_NAME)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, minibus.getNameOfMark());
        Mockito.when(preparedStatement.executeUpdate()).thenThrow(SQLException.class);
        Mockito.when(connection.prepareStatement(INSERT_MINIBUS)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, minibus.getNameOfMark());
        Mockito.doNothing().when(preparedStatement).setString(2, minibus.getPrice());
        Mockito.doNothing().when(preparedStatement).setString(3, minibus.getImagePath());
        Mockito.doNothing().when(preparedStatement).setString(4, minibus.getFullLoad());
        Mockito.doNothing().when(preparedStatement).setString(5, minibus.getCurbWeight());
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        carDAO.addMinibus(minibus);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(SELECT_FROM_ALL_CARS);
        Mockito.verify(preparedStatement).executeQuery();
        Mockito.verify(resultSet, Mockito.times(2)).next();
        Mockito.verify(resultSet).getString(1);
        Mockito.verify(connection).prepareStatement(INSERT_CAR_NAME);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(1, minibus.getNameOfMark());
        Mockito.verify(preparedStatement, Mockito.times(2)).executeUpdate();
        Mockito.verify(connection).prepareStatement(INSERT_MINIBUS);
        Mockito.verify(preparedStatement).setString(2, minibus.getPrice());
        Mockito.verify(preparedStatement).setString(3, minibus.getImagePath());
        Mockito.verify(preparedStatement).setString(4, minibus.getFullLoad());
        Mockito.verify(preparedStatement).setString(5, minibus.getCurbWeight());
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testAddCar()throws DAOException, SQLException{
        Car car = new Car("Mercedes-Mclaren", "100 000$", "420", "10","12", "5", "50", "300", "220", "img/img.png", "sedan");
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        String INSERT_CAR = "insert into cars(name_of_mark, price, power, acceleration_til_hundred, consumption, " +
                "engine_volume, tank_volume, trunk_volume, max_speed, image_path, type) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        String INSERT_CAR_NAME = "insert into allCars(allCars_name) values(?);";
        String SELECT_FROM_ALL_CARS = "select * from allCars";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(SELECT_FROM_ALL_CARS)).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(1)).thenReturn("G-Class");
        Mockito.when(connection.prepareStatement(INSERT_CAR_NAME)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, car.getNameOfMark());
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(connection.prepareStatement(INSERT_CAR)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, car.getNameOfMark());
        Mockito.doNothing().when(preparedStatement).setString(2, car.getPrice());
        Mockito.doNothing().when(preparedStatement).setString(3, car.getPower());
        Mockito.doNothing().when(preparedStatement).setString(4, car.getAccelerationTillHundred());
        Mockito.doNothing().when(preparedStatement).setString(5, car.getConsumption());
        Mockito.doNothing().when(preparedStatement).setString(6, car.getEngineVolume());
        Mockito.doNothing().when(preparedStatement).setString(7, car.getTankVolume());
        Mockito.doNothing().when(preparedStatement).setString(8, car.getTrunkVolume());
        Mockito.doNothing().when(preparedStatement).setString(9, car.getMaxSpeed());
        Mockito.doNothing().when(preparedStatement).setString(10, car.getImagePath());
        Mockito.doNothing().when(preparedStatement).setString(11, car.getType());
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        carDAO.addCar(car);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(SELECT_FROM_ALL_CARS);
        Mockito.verify(preparedStatement).executeQuery();
        Mockito.verify(resultSet, Mockito.times(2)).next();
        Mockito.verify(resultSet).getString(1);
        Mockito.verify(connection).prepareStatement(INSERT_CAR_NAME);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(1, car.getNameOfMark());
        Mockito.verify(preparedStatement, Mockito.times(2)).executeUpdate();
        Mockito.verify(connection).prepareStatement(INSERT_CAR);
        Mockito.verify(preparedStatement).setString(2, car.getPrice());
        Mockito.verify(preparedStatement).setString(3, car.getPower());
        Mockito.verify(preparedStatement).setString(4, car.getAccelerationTillHundred());
        Mockito.verify(preparedStatement).setString(5, car.getConsumption());
        Mockito.verify(preparedStatement).setString(6, car.getEngineVolume());
        Mockito.verify(preparedStatement).setString(7, car.getTankVolume());
        Mockito.verify(preparedStatement).setString(8, car.getTrunkVolume());
        Mockito.verify(preparedStatement).setString(9, car.getMaxSpeed());
        Mockito.verify(preparedStatement).setString(10, car.getImagePath());
        Mockito.verify(preparedStatement).setString(11, car.getType());
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test(expected = DAOException.class)
    public void testAddCar_DAOException()throws DAOException, SQLException{
        Car car = new Car("Mercedes-Mclaren", "100 000$", "420", "10","12", "5", "50", "300", "220", "img/img.png", "sedan");
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        String INSERT_CAR = "insert into cars(name_of_mark, price, power, acceleration_til_hundred, consumption, " +
                "engine_volume, tank_volume, trunk_volume, max_speed, image_path, type) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        String INSERT_CAR_NAME = "insert into allCars(allCars_name) values(?);";
        String SELECT_FROM_ALL_CARS = "select * from allCars";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(SELECT_FROM_ALL_CARS)).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(1)).thenReturn("G-Class");
        Mockito.when(connection.prepareStatement(INSERT_CAR_NAME)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, car.getNameOfMark());
        Mockito.when(preparedStatement.executeUpdate()).thenThrow(SQLException.class);
        Mockito.when(connection.prepareStatement(INSERT_CAR)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, car.getNameOfMark());
        Mockito.doNothing().when(preparedStatement).setString(2, car.getPrice());
        Mockito.doNothing().when(preparedStatement).setString(3, car.getPower());
        Mockito.doNothing().when(preparedStatement).setString(4, car.getAccelerationTillHundred());
        Mockito.doNothing().when(preparedStatement).setString(5, car.getConsumption());
        Mockito.doNothing().when(preparedStatement).setString(6, car.getEngineVolume());
        Mockito.doNothing().when(preparedStatement).setString(7, car.getTankVolume());
        Mockito.doNothing().when(preparedStatement).setString(8, car.getTrunkVolume());
        Mockito.doNothing().when(preparedStatement).setString(9, car.getMaxSpeed());
        Mockito.doNothing().when(preparedStatement).setString(10, car.getImagePath());
        Mockito.doNothing().when(preparedStatement).setString(11, car.getType());
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        carDAO.addCar(car);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(SELECT_FROM_ALL_CARS);
        Mockito.verify(preparedStatement).executeQuery();
        Mockito.verify(resultSet, Mockito.times(2)).next();
        Mockito.verify(resultSet).getString(1);
        Mockito.verify(connection).prepareStatement(INSERT_CAR_NAME);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(1, car.getNameOfMark());
        Mockito.verify(preparedStatement, Mockito.times(2)).executeUpdate();
        Mockito.verify(connection).prepareStatement(INSERT_CAR);
        Mockito.verify(preparedStatement).setString(2, car.getPrice());
        Mockito.verify(preparedStatement).setString(3, car.getPower());
        Mockito.verify(preparedStatement).setString(4, car.getAccelerationTillHundred());
        Mockito.verify(preparedStatement).setString(5, car.getConsumption());
        Mockito.verify(preparedStatement).setString(6, car.getEngineVolume());
        Mockito.verify(preparedStatement).setString(7, car.getTankVolume());
        Mockito.verify(preparedStatement).setString(8, car.getTrunkVolume());
        Mockito.verify(preparedStatement).setString(9, car.getMaxSpeed());
        Mockito.verify(preparedStatement).setString(10, car.getImagePath());
        Mockito.verify(preparedStatement).setString(11, car.getType());
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testGetCarMarkByImage()throws DAOException, SQLException{
        String imagePath = "img/img.png";
        String SELECT_FROM_CARS = "select * from cars";
        DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        ArrayList<Car> cars = Mockito.mock(ArrayList.class);
        JspDAOImpl jspDAO = Mockito.mock(JspDAOImpl.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Car car = new Car("C-Class", "100 000$", "420", "10","12", "5", "50", "300", "220", "img/img.png", "sedan");

        Mockito.when(daoFactory.getJSPDao()).thenReturn(jspDAO);
        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_CARS)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(3)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(4)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(5)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(6)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(7)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(8)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(9)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(10)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(11)).thenReturn("img/img.png");
        Mockito.when(resultSet.getString(12)).thenReturn("img/img.png");
        Mockito.when(cars.add(new Car(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
                resultSet.getString(11), resultSet.getString(12)))).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        carDAO.getCarMarkByImage(imagePath);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_CARS);
        Mockito.verify(resultSet, Mockito.times(2)).next();
        Mockito.verify(resultSet, Mockito.times(2)).getString(2);
        Mockito.verify(resultSet, Mockito.times(2)).getString(3);
        Mockito.verify(resultSet, Mockito.times(2)).getString(4);
        Mockito.verify(resultSet, Mockito.times(2)).getString(5);
        Mockito.verify(resultSet, Mockito.times(2)).getString(6);
        Mockito.verify(resultSet, Mockito.times(2)).getString(7);
        Mockito.verify(resultSet, Mockito.times(2)).getString(8);
        Mockito.verify(resultSet, Mockito.times(2)).getString(9);
        Mockito.verify(resultSet, Mockito.times(2)).getString(10);
        Mockito.verify(resultSet, Mockito.times(2)).getString(11);
        Mockito.verify(resultSet, Mockito.times(2)).getString(12);
        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void testGetCarListByType()throws DAOException, SQLException{
        String type = "sedan";
        String SELECT_FROM_CARS = "select * from cars";
        DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
        Connection connection = Mockito.mock(Connection.class);
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        ArrayList<Car> cars = Mockito.mock(ArrayList.class);
        JspDAOImpl jspDAO = Mockito.mock(JspDAOImpl.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Car car = new Car("C-Class", "100 000$", "420", "10","12", "5", "50", "300", "220", "img/img.png", "sedan");

        Mockito.when(daoFactory.getJSPDao()).thenReturn(jspDAO);
        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(SELECT_FROM_CARS)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn("sedan");
        Mockito.when(resultSet.getString(3)).thenReturn("sedan");
        Mockito.when(resultSet.getString(4)).thenReturn("sedan");
        Mockito.when(resultSet.getString(5)).thenReturn("sedan");
        Mockito.when(resultSet.getString(6)).thenReturn("sedan");
        Mockito.when(resultSet.getString(7)).thenReturn("sedan");
        Mockito.when(resultSet.getString(8)).thenReturn("sedan");
        Mockito.when(resultSet.getString(9)).thenReturn("sedan");
        Mockito.when(resultSet.getString(10)).thenReturn("sedan");
        Mockito.when(resultSet.getString(11)).thenReturn("sedan");
        Mockito.when(resultSet.getString(12)).thenReturn("sedan");
        Mockito.when(cars.add(new Car(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
                resultSet.getString(11), resultSet.getString(12)))).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        CarDAO carDAO = new CarDAOImpl(connectionPool);
        carDAO.getCarListByType(type);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(SELECT_FROM_CARS);
        Mockito.verify(resultSet, Mockito.times(2)).next();
        Mockito.verify(resultSet, Mockito.times(2)).getString(2);
        Mockito.verify(resultSet, Mockito.times(2)).getString(3);
        Mockito.verify(resultSet, Mockito.times(2)).getString(4);
        Mockito.verify(resultSet, Mockito.times(2)).getString(5);
        Mockito.verify(resultSet, Mockito.times(2)).getString(6);
        Mockito.verify(resultSet, Mockito.times(2)).getString(7);
        Mockito.verify(resultSet, Mockito.times(2)).getString(8);
        Mockito.verify(resultSet, Mockito.times(2)).getString(9);
        Mockito.verify(resultSet, Mockito.times(2)).getString(10);
        Mockito.verify(resultSet, Mockito.times(2)).getString(11);
        Mockito.verify(resultSet, Mockito.times(2)).getString(12);
        Mockito.verify(connectionPool).retrieve(connection);
    }

}
