package dao;

import dao.entity.AbstractCar;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import dao.exception.DAOException;

import java.util.ArrayList;

public interface CarDAO {

    /**
     * adds car to database
     *
     * @param car is a car
     * @throws DAOException is a module exception
     */
    void addCar(Car car) throws DAOException;

    /**
     * adds minibuses to database
     *
     * @param minibus is a minibus
     * @throws DAOException is a module exception
     */
    void addMinibus(Minibus minibus) throws DAOException;

    /**
     * deletes car from database
     *
     * @param mark is a mark of car
     * @throws DAOException is a module exception
     */
    void deleteCar(String mark) throws DAOException;

    /**
     * deletes minibus from database
     *
     * @param mark is a mark of minibus
     * @throws DAOException is a module exception
     */
    void deleteMinibus(String mark) throws DAOException;

    /**
     * finds car by mark
     *
     * @param mark is the mark of car
     * @return Car object according to mark
     * @throws DAOException is a module exception
     */
    Car getCarByMark(String mark) throws DAOException;

    /**
     * updates information of definite car
     *
     * @param car is a car
     * @throws DAOException is a module exception
     */
    boolean updateCarInfo(Car car) throws DAOException;

    /**
     * find cars
     *
     * @return list of cars
     * @throws DAOException is module exception
     */
    ArrayList<Car> getCars() throws DAOException;

    /**
     * find minibuses
     *
     * @return list of minibuses
     * @throws DAOException is module exception
     */
    ArrayList<Minibus> getMinibuses() throws DAOException;

    /**
     * find trucks
     *
     * @return list of trucks
     * @throws DAOException is module exception
     */
    ArrayList<Truck> getTrucks() throws DAOException;

    /**
     * find all cars including cars, minibuses and trucks
     *
     * @return list of all cars
     * @throws DAOException is module exception
     */
    ArrayList<AbstractCar> getAllCars() throws DAOException;

    /**
     * calculates how much pages do we need to illustrate content including all car types on UI
     *
     * @return list of strings that has the size equals to amount of pages
     * @throws DAOException is a module exception
     */
    ArrayList<String> getCountOfAllCarPages() throws DAOException;

    /**
     * finds as much car information(including all car types) as can fit on one page
     *
     * @param pageNumber is the number of page that will be illustrated
     * @return list of objects that contains car information(including all car types)
     * @throws DAOException is a module exception
     */
    ArrayList<AbstractCar> getAllCarsInfoForOnePage(String pageNumber) throws DAOException;

    /**
     * calculates how much pages do we need to illustrate car content on UI
     *
     * @return list of strings that has the size equals to amount of pages
     * @throws DAOException is a module exception
     */
    ArrayList<String> getCountOfCarPages() throws DAOException;

    /**
     * calculates how much pages do we need to illustrate minibus content on UI
     *
     * @return list of strings that has the size equals to amount of pages
     * @throws DAOException is a module exception
     */
    ArrayList<String> getCountOfMinibusPages() throws DAOException;

    /**
     * finds as much car information as can fit on one page
     *
     * @param pageNumber is the number of page that will be illustrated
     * @return list of objects that contains car information
     * @throws DAOException is a module exception
     */
    ArrayList<AbstractCar> getCarsInfoForOnePage(String pageNumber) throws DAOException;

    /**
     * finds as much minibus information as can fit on one page
     *
     * @param pageNumber is the number of page that will be illustrated
     * @return list of objects that contains minibus information
     * @throws DAOException is a module exception
     */
    ArrayList<AbstractCar> getMinibusesInfoForOnePage(String pageNumber) throws DAOException;

    /**
     * calculates how much pages do we need to illustrate car content on UI according to given car type
     *
     * @param carType is given car type
     * @return list of strings that has the size equals to amount of pages
     * @throws DAOException is a module exception
     */
    ArrayList<String> getCountOfCarPagesAccordingToType(String carType) throws DAOException;

    /**
     * finds as much car information as can fit on one page according to given car type
     *
     * @param pageNumber is the number of page that will be illustrated
     * @param carType    is given car type
     * @return list of objects that contains car information according to given car type
     * @throws DAOException is a module exception
     */
    ArrayList<AbstractCar> getCarsInfoForOnePageAccordingToType(String pageNumber, String carType) throws DAOException;

    /**
     * finds minibus according to id
     *
     * @param id is the minibus id
     * @return minibus object
     * @throws DAOException is a module exception
     */
    Minibus getMinibusById(int id) throws DAOException;

    /**
     * finds car according to id
     *
     * @param id is the car id
     * @return car object
     * @throws DAOException is a module exception
     */
    Car getCarById(int id) throws DAOException;

    /**
     * finds car not depending on its type(car, minibus or truck)
     *
     * @param imagePath is the path of image
     * @return abstract cat object
     * @throws DAOException is a module exception
     */
    AbstractCar getAnyCarByImage(String imagePath) throws DAOException;

}
