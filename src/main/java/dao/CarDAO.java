package dao;

import dao.entity.car.Car;
import dao.exception.DAOException;

import java.util.ArrayList;

public interface CarDAO {
    String getCarMarkByImage(String imagePath) throws DAOException;
    ArrayList<Car> getCarListByType(String type) throws DAOException;
    String[] getMarkAndPriceByImage(String imagePath) throws DAOException;
}
