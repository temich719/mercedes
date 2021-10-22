package dao.database.impl;

import dao.database.DataBase;
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

public class DataBaseImpl implements DataBase {

    private static final String SELECT = "select * from users";
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

    public static ArrayList<Minibus> getMinibuses() throws SQLException {
        ArrayList<Minibus> minibuses = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("select * from minibuses");
        while (resultSet.next()){
            minibuses.add(new Minibus(resultSet.getString(2),resultSet.getString(3),
                    resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)));
        }
        return minibuses;
    }

    public static ArrayList<Car> getCars() throws SQLException {
        ArrayList<Car> cars = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("select * from cars");
        while (resultSet.next()){
            cars.add(new Car(resultSet.getString(2),resultSet.getString(3), resultSet.getString(4),
                    resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),
                    resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),
                    resultSet.getString(11),resultSet.getString(12)));
        }
        return cars;
    }

    public static ArrayList<Truck> getTrucks()throws SQLException{
        ArrayList<Truck> trucks = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("select * from trucks");
        while (resultSet.next()){
            trucks.add(new Truck(resultSet.getString(2),"73 000$","img/truck.jpg"));
        }
        return trucks;
    }

    public static ArrayList<AbstractCar> getAllCars()throws SQLException{
        ArrayList<AbstractCar> allCars = new ArrayList<>();
        ResultSet cars = statement.executeQuery("select * from cars");
        while (cars.next()){
            allCars.add(new Car(cars.getString(2),cars.getString(3), cars.getString(4),
                    cars.getString(5),cars.getString(6),cars.getString(7),
                    cars.getString(8),cars.getString(9),cars.getString(10),
                    cars.getString(11),cars.getString(12)));
        }
        ResultSet minibuses = statement.executeQuery("select * from minibuses");
        while (minibuses.next()){
            allCars.add(new Minibus(minibuses.getString(2),minibuses.getString(3),
                    minibuses.getString(4),minibuses.getString(5),minibuses.getString(6)));
        }
        ResultSet trucks = statement.executeQuery("select * from trucks");
        trucks.next();
        allCars.add(new Truck(trucks.getString(2),"73 000$", "img/truck.jpg"));
        return allCars;
    }

    private static ResultSet getResultSetOfAll() throws SQLException{
        return statement.executeQuery(SELECT);
    }

    public static void updatePassword(String email, String password) throws SQLException{
        statement.executeUpdate("update users set password='" + password + "' where email='" + email + "';");
    }

    public static Pair getName(String email) throws SQLException{
        String query = "select * from users where email='" + email + "';";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        return new Pair(resultSet.getString(2),resultSet.getString(3));
    }

    public static ArrayList<Pair> select() throws SQLException{
        ResultSet resultSet = getResultSetOfAll();
        ArrayList<Pair> arrayList = new ArrayList<>();
        while (resultSet.next()){
            Pair pair = new Pair(resultSet.getString(4), resultSet.getString(5));
            arrayList.add(pair);
        }
        return arrayList;
    }

    public static boolean isExistingEmail(String email) throws SQLException {
        ResultSet resultSet = getResultSetOfAll();
        while (resultSet.next()){
            if (email.equals(resultSet.getString(4)))return true;
        }
        return false;
    }


    //вынести в отдельные методы эти вайлы
    @Override
    public void insert(String email, String password, String name, String surname) throws SQLException {
        final String access = "registered";
        boolean flagName = false;
        boolean flagSurname = false;
        boolean flagAccess = false;
        ResultSet userName = statement.executeQuery("select * from names");
        while (userName.next()){
            if (userName.getString(2).equals(name)){
                flagName = true;
            }
        }
        ResultSet userSurname = statement.executeQuery("select * from surnames");
        while (userSurname.next()){
            if (userSurname.getString(2).equals(surname)){
                flagSurname = true;
            }
        }
        ResultSet accessType = statement.executeQuery("select * from access_types");
        while (accessType.next()){
            if (accessType.getString(2).equals(access)){
                flagAccess = true;
            }
        }

        if (!flagName)statement.executeUpdate("insert into names(user_name) values('"+name+"');");
        if (!flagSurname)statement.executeUpdate("insert into surnames(user_surname) values('"+surname+"');");
        if (!flagAccess)statement.executeUpdate("insert into access_types(access_type) values('registered');");

        statement.executeUpdate("insert into users(user_name,user_surname,email,password,access_type) " +
                "values('"+name+"','"+surname+"','"+email+"','"+password+"','registered');");
    }

    public void addOrder(String name, String surname, String email, String service, String carName, String price, String phone, String date)throws  SQLException{
        boolean flagName = false;
        boolean flagSurname = false;
        ResultSet userName = statement.executeQuery("select * from names");
        while (userName.next()){
            if (userName.getString(2).equals(name)){
                flagName = true;
            }
        }
        ResultSet userSurname = statement.executeQuery("select * from surnames");
        while (userSurname.next()){
            if (userSurname.getString(2).equals(surname)){
                flagSurname = true;
            }
        }
        if (!flagName)statement.executeUpdate("insert into names(user_name) values('"+name+"');");
        if (!flagSurname)statement.executeUpdate("insert into surnames(user_surname) values('"+surname+"');");
        statement.executeUpdate("insert into orders(user_name,user_surname,email,service,car_name,price,phone,date) values('"+
                name+"','"+surname+"','"+email+"','"+service+"','"+carName+"','"+price+"','"+phone+"','"+date+"');");
    }
}