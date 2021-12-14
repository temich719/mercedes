package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.car.Car;
import org.apache.log4j.Logger;
import service.CarService;
import service.ServiceFactory;
import service.exception.ServiceException;
import service.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class ViewCarCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(ViewCarCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to ViewCarCommand");
        final String id = req.getParameter(ID);
        Validator.validateInputData(id);
        Car car;
        try {
            car = carService.getCarById(Integer.parseInt(id));
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        req.setAttribute(NAME_OF_MARK, car.getNameOfMark());
        req.setAttribute(PICTURE, car.getImagePath());
        req.setAttribute(PRICE, car.getPrice());
        req.setAttribute(POWER, car.getPower());
        req.setAttribute(ACCELERATION, car.getAccelerationTillHundred());
        req.setAttribute(CONSUMPTION, car.getConsumption());
        req.setAttribute(ENGINE_VOLUME, car.getEngineVolume());
        req.setAttribute(TANK_VOLUME, car.getTankVolume());
        req.setAttribute(TRUNK_VOLUME, car.getTrunkVolume());
        req.setAttribute(MAX_SPEED, car.getMaxSpeed());
        req.setAttribute(TYPE, car.getType());
        req.setAttribute(ID, car.getId());
        return JSP_USER + CAR_INFO_PAGE;
    }
}