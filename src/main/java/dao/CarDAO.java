package dao;

import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.exception.DAOException;

import java.util.ArrayList;

public interface CarDAO {
    /**
     * finds mark of car by image
     * @param imagePath path of image which connected with mark in database
     * @return mark of car according to imagePath
     * @throws DAOException is a module exception
     */
    String getCarMarkByImage(String imagePath) throws DAOException;

    /**
     * finds all cars by type
     * @param type of car (for example coupe)
     * @return list of cars according to type
     * @throws DAOException is a module exception
     */
    ArrayList<Car> getCarListByType(String type) throws DAOException;

    /**
     * finds mark and price by image
     * @param imagePath path of image which connected with mark in database
     * @return array with mark and price
     * @throws DAOException is a module exception
     */
    String[] getMarkAndPriceByImage(String imagePath) throws DAOException;

    /**
     * adds car to database
     * @param car is a car
     * @throws DAOException is a module exception
     */
    void addCar(Car car)throws DAOException;

    /**
     * adds minibuses to database
     * @param minibus is a minibus
     * @throws DAOException is a module exception
     */
    void addMinibus(Minibus minibus)throws DAOException;

    /**
     * deletes car from database
     * @param mark is a mark of car
     * @throws DAOException is a module exception
     */
    void deleteCar(String mark)throws DAOException;

    /**
     * deletes minibus from database
     * @param mark is a mark of minibus
     * @throws DAOException is a module exception
     */
    void deleteMinibus(String mark)throws DAOException;

    /**
     * finds cars by image
     * @param imagePath is a path of image
     * @return Car object according to image
     * @throws DAOException is a module exception
     */
    Car getCarByImage(String imagePath)throws DAOException;

    /**
     * finds car by mark
     * @param mark is the mark of car
     * @return Car object according to mark
     * @throws DAOException is a module exception
     */
    Car getCarByMark(String mark)throws DAOException;

    /**
     * updates information of definite car
     * @param car is a car
     * @throws DAOException is a module exception
     */
    void updateCarInfo(Car car)throws DAOException;

    /**
     * checks if type is allowed
     * @param type is the type of car
     * @return true if type is allowed and false if not
     * @throws DAOException is a module exception
     */
    boolean isAllowedCarType(String type)throws DAOException;
}
