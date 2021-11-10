package service;

import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import service.exception.ServiceException;

import java.util.ArrayList;

public interface CarService {
    /**
     * returns car mark according to image path
     * @param imagePath is a path of image
     * @return car mark
     * @throws ServiceException is a module exception
     */
    String getCarMarkByImage(String imagePath) throws ServiceException;

    /**
     * returns cars from dao
     * @return list of cars
     * @throws ServiceException is a module exception
     */
    ArrayList<Car> getCars() throws ServiceException;

    /**
     * returns trucks from dao
     * @return list of trucks
     * @throws ServiceException is a module exception
     */
    ArrayList<Truck> getTrucks() throws ServiceException;

    /**
     * returns minibuses from dao
     * @return list of minibuses
     * @throws ServiceException is a module exception
     */
    ArrayList<Minibus> getMinibuses() throws ServiceException;

    /**
     * returns array with mark and price according to image
     * @param imagePath is path of image
     * @return array of mark and price
     * @throws ServiceException is a module exception
     */
    String[] getMarkAndPriceByImage(String imagePath) throws ServiceException;

    /**
     * finds cars according to type
     * @param type is a type of car
     * @return list of cars by type
     * @throws ServiceException
     */
    ArrayList<Car> getCarListByType(String type) throws ServiceException;
}
