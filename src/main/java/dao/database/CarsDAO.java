package dao.database;

import dao.entity.AbstractCar;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CarsDAO {
    ArrayList<Minibus> getMinibuses() throws SQLException;
    ArrayList<Car> getCars() throws SQLException;
    ArrayList<Truck> getTrucks()throws SQLException;
    ArrayList<AbstractCar> getAllCars()throws SQLException;
}
