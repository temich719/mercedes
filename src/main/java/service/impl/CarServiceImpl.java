package service.impl;

import dao.CarDAO;
import dao.daoFactory.DaoFactory;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import dao.exception.DAOException;
import dao.impl.CarDAOImpl;
import service.CarService;
import service.exception.ServiceException;

import java.util.ArrayList;

public class CarServiceImpl implements CarService {

    private final CarDAO carDAO = DaoFactory.getINSTANCE().getCarDAO();

    public CarServiceImpl(){}

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
            return CarDAOImpl.getCars();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<Truck> getTrucks() throws ServiceException {
        try {
            return CarDAOImpl.getTrucks();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<Minibus> getMinibuses() throws ServiceException {
        try {
            return CarDAOImpl.getMinibuses();
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
}
