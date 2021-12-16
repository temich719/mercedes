package service;

import dao.entity.AbstractCar;
import dao.entity.Page;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import service.exception.ServiceException;

import java.util.List;

public interface CarService {

    /**
     * returns cars from dao
     *
     * @return list of cars
     * @throws ServiceException is a module exception
     */
    List<Car> getCars() throws ServiceException;

    /**
     * returns trucks from dao
     *
     * @return list of trucks
     * @throws ServiceException is a module exception
     */
    List<Truck> getTrucks() throws ServiceException;

    /**
     * returns minibuses from dao
     *
     * @return list of minibuses
     * @throws ServiceException is a module exception
     */
    List<Minibus> getMinibuses() throws ServiceException;

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
     * find all cars including cars, minibuses and trucks
     *
     * @return list of all cars
     * @throws ServiceException is module exception
     */
    List<AbstractCar> getAllCars() throws ServiceException;

    /**
     * finds minibus according to id
     *
     * @param id is the minibus id
     * @return minibus object
     * @throws ServiceException is a module exception
     */
    Minibus getMinibusById(int id) throws ServiceException;

    /**
     * finds car according to id
     *
     * @param id is the car id
     * @return car object
     * @throws ServiceException is a module exception
     */
    Car getCarById(int id) throws ServiceException;

    /**
     * finds car not depending on its type(car, minibus or truck)
     *
     * @param imagePath is the path of image
     * @return abstract cat object
     * @throws ServiceException is a module exception
     */
    AbstractCar getAnyCarByImage(String imagePath) throws ServiceException;

    /**
     * finds data of all cars and page that we need to illustrate
     *
     * @param pageNumber is the number of page
     * @return object that contains data about page whose number is pageNumber
     * @throws ServiceException is a module exception
     */
    Page<AbstractCar> getPageOfAllCars(String pageNumber) throws ServiceException;

    /**
     * finds data of cars and page that we need to illustrate
     *
     * @param pageNumber is the number of page
     * @return object that contains data about page whose number is pageNumber
     * @throws ServiceException is a module exception
     */
    Page<AbstractCar> getPageOfCars(String pageNumber) throws ServiceException;

    /**
     * finds data of minibuses and page that we need to illustrate
     *
     * @param pageNumber is the number of page
     * @return object that contains data about page whose number is pageNumber
     * @throws ServiceException is a module exception
     */
    Page<AbstractCar> getPageOfMinibuses(String pageNumber) throws ServiceException;

    /**
     * finds data of cars and page that we need to illustrate according to car type
     *
     * @param pageNumber is the number of page
     * @param carType    is given car type
     * @return object that contains data about page whose number is pageNumber and type is carType
     * @throws ServiceException is a module exception
     */
    Page<AbstractCar> getPageOfCarsAccordingToType(String pageNumber, String carType) throws ServiceException;
}
