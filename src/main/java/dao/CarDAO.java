package dao;

import dao.entity.AbstractCar;
import dao.entity.Page;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import dao.exception.DAOException;

import java.util.List;

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
    List<Car> getCars() throws DAOException;

    /**
     * find minibuses
     *
     * @return list of minibuses
     * @throws DAOException is module exception
     */
    List<Minibus> getMinibuses() throws DAOException;

    /**
     * find trucks
     *
     * @return list of trucks
     * @throws DAOException is module exception
     */
    List<Truck> getTrucks() throws DAOException;

    /**
     * find all cars including cars, minibuses and trucks
     *
     * @return list of all cars
     * @throws DAOException is module exception
     */
    List<AbstractCar> getAllCars() throws DAOException;

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

    /**
     * finds data of all cars and page that we need to illustrate
     *
     * @param pageNumber is the number of page
     * @return object that contains data about page whose number is pageNumber
     * @throws DAOException is a module exception
     */
    Page<AbstractCar> getPageOfAllCars(String pageNumber) throws DAOException;

    /**
     * finds data of cars and page that we need to illustrate
     *
     * @param pageNumber is the number of page
     * @return object that contains data about page whose number is pageNumber
     * @throws DAOException is a module exception
     */
    Page<AbstractCar> getPageOfCars(String pageNumber) throws DAOException;

    /**
     * finds data of minibuses and page that we need to illustrate
     *
     * @param pageNumber is the number of page
     * @return object that contains data about page whose number is pageNumber
     * @throws DAOException is a module exception
     */
    Page<AbstractCar> getPageOfMinibuses(String pageNumber) throws DAOException;

    /**
     * finds data of cars and page that we need to illustrate according to car type
     *
     * @param pageNumber is the number of page
     * @param carType    is given car type
     * @return object that contains data about page whose number is pageNumber and type is carType
     * @throws DAOException is a module exception
     */
    Page<AbstractCar> getPageOfCarsAccordingToType(String pageNumber, String carType) throws DAOException;

}
