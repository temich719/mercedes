package service;

import dao.entity.AbstractCar;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import dao.exception.DAOException;
import service.exception.ServiceException;

import java.util.ArrayList;

public interface CarService {
    /**
     * returns car mark according to image path
     *
     * @param imagePath is a path of image
     * @return car mark
     * @throws ServiceException is a module exception
     */
    String getCarMarkByImage(String imagePath) throws ServiceException;

    /**
     * returns cars from dao
     *
     * @return list of cars
     * @throws ServiceException is a module exception
     */
    ArrayList<Car> getCars() throws ServiceException;

    /**
     * returns trucks from dao
     *
     * @return list of trucks
     * @throws ServiceException is a module exception
     */
    ArrayList<Truck> getTrucks() throws ServiceException;

    /**
     * returns minibuses from dao
     *
     * @return list of minibuses
     * @throws ServiceException is a module exception
     */
    ArrayList<Minibus> getMinibuses() throws ServiceException;

    /**
     * returns array with mark and price according to image
     *
     * @param imagePath is path of image
     * @return array of mark and price
     * @throws ServiceException is a module exception
     */
    String[] getMarkAndPriceByImage(String imagePath) throws ServiceException;

    /**
     * finds cars according to type
     *
     * @param type is a type of car
     * @return list of cars by type
     * @throws ServiceException is a module exception
     */
    ArrayList<Car> getCarListByType(String type) throws ServiceException;

    /**
     * adds new car
     *
     * @param car is a car
     * @throws ServiceException is a module exception
     */
    void addCar(Car car) throws ServiceException;

    /**
     * adds new minibus
     *
     * @param minibus is a minibus
     * @throws ServiceException is a module exception
     */
    void addMinibus(Minibus minibus) throws ServiceException;

    /**
     * deletes car from database
     *
     * @param mark is a mark of car
     * @throws ServiceException is a module exception
     */
    void deleteCar(String mark) throws ServiceException;

    /**
     * deletes minibus from database
     *
     * @param mark is a mark of minibus
     * @throws ServiceException is a module exception
     */
    void deleteMinibus(String mark) throws ServiceException;

    /**
     * finds car by image
     *
     * @param imagePath is a path of image
     * @return Car object according to image
     * @throws ServiceException is a module exception
     */
    Car getCarByImage(String imagePath) throws ServiceException;

    /**
     * finds car by mark
     *
     * @param mark is the mark of car
     * @return Car object according to mark
     * @throws ServiceException is a module exception
     */
    Car getCarByMark(String mark) throws ServiceException;

    /**
     * updates information of definite car
     *
     * @param car is a car
     * @return true if car info was successfully updated and false if not
     * @throws ServiceException is a module exception
     */
    boolean updateCarInfo(Car car) throws ServiceException;

    /**
     * checks if type is allowed
     *
     * @param type is the type of car
     * @return true if type is allowed and false if not
     * @throws ServiceException is a module exception
     */
    boolean isAllowedCarType(String type) throws ServiceException;

    /**
     * finds minibus by its image
     *
     * @param imagePath is the path of image
     * @return minibus if it was found and null if not
     * @throws ServiceException is a module exception
     */
    Minibus getMinibusByImage(String imagePath) throws ServiceException;


    /**
     * find all cars including cars, minibuses and trucks
     *
     * @return list of all cars
     * @throws ServiceException is module exception
     */
    ArrayList<AbstractCar> getAllCars() throws ServiceException;
}
