package service.impl;

import dao.CarDAO;
import dao.daoFactory.DaoFactory;
import dao.entity.AbstractCar;
import dao.entity.Page;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import dao.exception.DAOException;
import service.CarService;
import service.exception.ServiceException;

import java.util.List;

public class CarServiceImpl implements CarService {

    private final CarDAO carDAO = DaoFactory.getINSTANCE().getCarDAO();

    public CarServiceImpl() {
    }

    @Override
    public Page<AbstractCar> getPageOfAllCars(String pageNumber) throws ServiceException {
        try {
            return carDAO.getPageOfAllCars(pageNumber);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Page<AbstractCar> getPageOfCars(String pageNumber) throws ServiceException {
        try {
            return carDAO.getPageOfCars(pageNumber);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Page<AbstractCar> getPageOfMinibuses(String pageNumber) throws ServiceException {
        try {
            return carDAO.getPageOfMinibuses(pageNumber);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Minibus getMinibusById(int id) throws ServiceException {
        try {
            return carDAO.getMinibusById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Car> getCars() throws ServiceException {
        try {
            return carDAO.getCars();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Page<AbstractCar> getPageOfCarsAccordingToType(String pageNumber, String carType) throws ServiceException {
        try {
            return carDAO.getPageOfCarsAccordingToType(pageNumber, carType);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Truck> getTrucks() throws ServiceException {
        try {
            return carDAO.getTrucks();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<AbstractCar> getAllCars() throws ServiceException {
        try {
            return carDAO.getAllCars();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Minibus> getMinibuses() throws ServiceException {
        try {
            return carDAO.getMinibuses();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Car getCarById(int id) throws ServiceException {
        try {
            return carDAO.getCarById(id);
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
    public AbstractCar getAnyCarByImage(String imagePath) throws ServiceException {
        try {
            return carDAO.getAnyCarByImage(imagePath);
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
