package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.car.Car;
import org.apache.log4j.Logger;
import service.CarService;
import service.ServiceFactory;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class AdminGoDefiniteCarInfoCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AdminGoDefiniteCarInfoCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to AdminGoDefiniteCarInfoCommand");
        try {
            final Car car = carService.getCarByMark(req.getParameter(SELECT_NAME));
            req.setAttribute(MARK, car.getNameOfMark());
            req.setAttribute(PRICE, car.getPrice().replaceAll("\\s", ""));
            req.setAttribute(POWER, car.getPower());
            req.setAttribute(ACCELERATION, car.getAccelerationTillHundred());
            req.setAttribute(CONSUMPTION, car.getConsumption());
            req.setAttribute(ENGINE_VOLUME, car.getEngineVolume());
            req.setAttribute(TANK_VOLUME, car.getTankVolume());
            req.setAttribute(TRUNK_VOLUME, car.getTrunkVolume());
            req.setAttribute(MAX_SPEED, car.getMaxSpeed());
            req.setAttribute(IMAGE_PATH, car.getImagePath());
            req.setAttribute(TYPE, car.getType());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_ADMIN + ADMIN_CAR_DESCRIPTION_PAGE;
    }
}
