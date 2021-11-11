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

import java.util.ArrayList;
import java.util.Objects;

public class CarDAOImpl extends AbstractDAO implements CarDAO {

    private final static Logger logger = Logger.getLogger(CarDAOImpl.class);

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
