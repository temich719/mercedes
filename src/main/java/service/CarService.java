package service;

import dao.entity.AbstractCar;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
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

    /**
     * calculates how much pages do we need to illustrate content including all car types on UI
     *
     * @return list of strings that has the size equals to amount of pages
     * @throws ServiceException is a module exception
     */
    ArrayList<String> getCountOfAllCarPages() throws ServiceException;

    /**
     * finds as much car information(including all car types) as can fit on one page
     *
     * @param pageNumber is the number of page that will be illustrated
     * @return list of objects that contains car information(including all car types)
     * @throws ServiceException is a module exception
     */
    ArrayList<AbstractCar> getAllCarsInfoForOnePage(String pageNumber) throws ServiceException;

    /**
     * calculates how much pages do we need to illustrate car content on UI
     *
     * @return list of strings that has the size equals to amount of pages
     * @throws ServiceException is a module exception
     */
    ArrayList<String> getCountOfCarPages() throws ServiceException;

    /**
     * calculates how much pages do we need to illustrate minibus content on UI
     *
     * @return list of strings that has the size equals to amount of pages
     * @throws ServiceException is a module exception
     */
    ArrayList<String> getCountOfMinibusPages() throws ServiceException;

    /**
     * finds as much car information as can fit on one page
     *
     * @param pageNumber is the number of page that will be illustrated
     * @return list of objects that contains car information
     * @throws ServiceException is a module exception
     */
    ArrayList<AbstractCar> getCarsInfoForOnePage(String pageNumber) throws ServiceException;

    /**
     * finds as much minibus information as can fit on one page
     *
     * @param pageNumber is the number of page that will be illustrated
     * @return list of objects that contains minibus information
     * @throws ServiceException is a module exception
     */
    ArrayList<AbstractCar> getMinibusesInfoForOnePage(String pageNumber) throws ServiceException;

    /**
     * calculates how much pages do we need to illustrate car content on UI according to given car type
     *
     * @param carType is given car type
     * @return list of strings that has the size equals to amount of pages
     * @throws ServiceException is a module exception
     */
    ArrayList<String> getCountOfCarPagesAccordingToType(String carType) throws ServiceException;

    /**
     * finds as much car information as can fit on one page according to given car type
     *
     * @param pageNumber is the number of page that will be illustrated
     * @param carType is given car type
     * @return list of objects that contains car information according to given car type
     * @throws ServiceException is a module exception
     */
    ArrayList<AbstractCar> getCarsInfoForOnePageAccordingToType(String pageNumber, String carType) throws ServiceException;
}
