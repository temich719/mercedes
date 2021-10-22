package dao.database.impl;

import dao.database.CarsDAO;
import dao.database.OrderDAO;
import dao.database.UserDAO;
import dao.entity.AbstractCar;
import dao.entity.Pair;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DataBaseImpl implements UserDAO, CarsDAO, OrderDAO {

    private static final String SELECT_FROM_USERS = "select * from users";
    private static final String SELECT_FROM_MINIBUSES = "select * from minibuses";
    private static final String SELECT_FROM_CARS = "select * from cars";
    private static final String SELECT_FROM_TRUCKS = "select * from trucks";
    private static final String SELECT_FROM_NAMES = "select * from names";
    private static final String SELECT_FROM_SURNAMES = "select * from surnames";
    private static final String SELECT_FROM_ACCESS_TYPES = "select * from access_types";
    private static Statement statement;

    static {
        Properties props = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("C:\\Projects\\Servlet\\src\\main\\resources\\databaseConfig.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            props.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String driver = props.getProperty("jdbc.driver");
        if (driver != null) {
            try {
                Class.forName(driver) ;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public ArrayList<Minibus> getMinibuses() throws SQLException {
        ArrayList<Minibus> minibuses = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(SELECT_FROM_MINIBUSES);
        while (resultSet.next()){
            minibuses.add(new Minibus(resultSet.getString(2),resultSet.getString(3),
                    resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)));
        }
        return minibuses;
    }

    @Override
    public ArrayList<Car> getCars() throws SQLException {
        ArrayList<Car> cars = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(SELECT_FROM_CARS);
        while (resultSet.next()){
            cars.add(new Car(resultSet.getString(2),resultSet.getString(3), resultSet.getString(4),
                    resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),
                    resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),
                    resultSet.getString(11),resultSet.getString(12)));
        }
        return cars;
    }

    @Override
    public ArrayList<Truck> getTrucks()throws SQLException{
        ArrayList<Truck> trucks = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(SELECT_FROM_TRUCKS);
        while (resultSet.next()){
            trucks.add(new Truck(resultSet.getString(2),"73 000$","img/truck.jpg"));
        }
        return trucks;
    }

    @Override
    public ArrayList<AbstractCar> getAllCars()throws SQLException{
        ArrayList<AbstractCar> allCars = new ArrayList<>();
        allCars.addAll(getCars());
        allCars.addAll(getMinibuses());
        allCars.addAll(getTrucks());
        return allCars;
    }

    @Override
    public ResultSet getResultSetOfAllUsers() throws SQLException{
        return statement.executeQuery(SELECT_FROM_USERS);
    }

    @Override
    public void updatePassword(String email, String password) throws SQLException{
        statement.executeUpdate("update users set password='" + password + "' where email='" + email + "';");
    }

    @Override
    public Pair getName(String email) throws SQLException{
        String query = "select * from users where email='" + email + "';";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        return new Pair(resultSet.getString(2),resultSet.getString(3));
    }

    @Override
    public ArrayList<Pair> selectDataForEnter() throws SQLException{
        ResultSet resultSet = getResultSetOfAllUsers();
        ArrayList<Pair> arrayList = new ArrayList<>();
        while (resultSet.next()){
            Pair pair = new Pair(resultSet.getString(4), resultSet.getString(5));
            arrayList.add(pair);
        }
        return arrayList;
    }

    @Override
    public boolean isExistingEmail(String email) throws SQLException {
        ResultSet resultSet = getResultSetOfAllUsers();
        while (resultSet.next()){
            if (email.equals(resultSet.getString(4)))return true;
        }
        return false;
    }

    private boolean isAlreadyExistsInDatabase(String parameter, String query)throws SQLException{
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            if (resultSet.getString(2).equals(parameter))
                return true;
        }
        return false;
    }

    @Override
    public void insertUser(String email, String password, String name, String surname) throws SQLException {
        final String access = "registered";
        if (!isAlreadyExistsInDatabase(name, SELECT_FROM_NAMES))
            statement.executeUpdate("insert into names(user_name) values('"+name+"');");
        if (!isAlreadyExistsInDatabase(surname, SELECT_FROM_SURNAMES))
            statement.executeUpdate("insert into surnames(user_surname) values('"+surname+"');");
        if (!isAlreadyExistsInDatabase(access, SELECT_FROM_ACCESS_TYPES))
            statement.executeUpdate("insert into access_types(access_type) values('registered');");
        statement.executeUpdate("insert into users(user_name,user_surname,email,password,access_type) " +
                "values('"+name+"','"+surname+"','"+email+"','"+password+"','registered');");
    }

    @Override
    public void addOrder(String name, String surname, String email, String service, String carName, String price, String phone, String date)throws  SQLException{
        if (!isAlreadyExistsInDatabase(name, SELECT_FROM_NAMES))
            statement.executeUpdate("insert into names(user_name) values('"+name+"');");
        if (!isAlreadyExistsInDatabase(surname, SELECT_FROM_SURNAMES))
            statement.executeUpdate("insert into surnames(user_surname) values('"+surname+"');");
        statement.executeUpdate("insert into orders(user_name,user_surname,email,service,car_name,price,phone,date) values('"+
                name+"','"+surname+"','"+email+"','"+service+"','"+carName+"','"+price+"','"+phone+"','"+date+"');");
    }
}