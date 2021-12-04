package service.impl;

import dao.CarDAO;
import dao.daoFactory.DaoFactory;
import dao.entity.AbstractCar;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import dao.exception.DAOException;
import service.CarService;
import service.exception.ServiceException;

import java.util.ArrayList;

public class CarServiceImpl implements CarService {

    private final CarDAO carDAO = DaoFactory.getINSTANCE().getCarDAO();

    public CarServiceImpl() {
    }

    @Override
    public String getCarMarkByImage(String imagePath) throws ServiceException {
        try {
            return carDAO.getCarMarkByImage(imagePath);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<Car> getCars() throws ServiceException {
        try {
            return carDAO.getCars();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<Truck> getTrucks() throws ServiceException {
        try {
            return carDAO.getTrucks();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<AbstractCar> getAllCars() throws ServiceException {
        try {
            return carDAO.getAllCars();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Minibus getMinibusByImage(String imagePath) throws ServiceException {
        try {
            return carDAO.getMinibusByImage(imagePath);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<Minibus> getMinibuses() throws ServiceException {
        try {
            return carDAO.getMinibuses();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String[] getMarkAndPriceByImage(String imagePath) throws ServiceException {
        try {
            return carDAO.getMarkAndPriceByImage(imagePath);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<Car> getCarListByType(String type) throws ServiceException {
        try {
            return carDAO.getCarListByType(type);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Car getCarByMark(String mark) throws ServiceException {
        try {
            return carDAO.getCarByMark(mark);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateCarInfo(Car car) throws ServiceException {
        try {
            return carDAO.updateCarInfo(car);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isAllowedCarType(String type) throws ServiceException {
        try {
            return carDAO.isAllowedCarType(type);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Car getCarByImage(String imagePath) throws ServiceException {
        try {
            return carDAO.getCarByImage(imagePath);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteMinibus(String mark) throws ServiceException {
        try {
            carDAO.deleteMinibus(mark);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteCar(String mark) throws ServiceException {
        try {
            carDAO.deleteCar(mark);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addMinibus(Minibus minibus) throws ServiceException {
        try {
            carDAO.addMinibus(minibus);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addCar(Car car) throws ServiceException {
        try {
            carDAO.addCar(car);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
