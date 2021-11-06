package service;

import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import service.exception.ServiceException;

import java.util.ArrayList;

public interface CarService {
    String getCarMarkByImage(String imagePath) throws ServiceException;
    ArrayList<Car> getCars() throws ServiceException;
    ArrayList<Truck> getTrucks() throws ServiceException;
    ArrayList<Minibus> getMinibuses() throws ServiceException;
    String[] getMarkAndPriceByImage(String imagePath) throws ServiceException;
    ArrayList<Car> getCarListByType(String type) throws ServiceException;
}
