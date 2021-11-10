package dao;

import dao.entity.car.Car;
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
}
