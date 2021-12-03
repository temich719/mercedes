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

public class AdminAddCarCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AdminAddCarCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to AdminAddCarCommand");
        final String mark = req.getParameter(MARK);
        final String price = req.getParameter(PRICE);
        final String power = req.getParameter(POWER);
        final String acceleration = req.getParameter(ACCELERATION);
        final String consumption = req.getParameter(CONSUMPTION);
        final String engineVolume = req.getParameter(ENGINE_VOLUME);
        final String tankVolume = req.getParameter(TANK_VOLUME);
        final String trunkVolume = req.getParameter(TRUNK_VOLUME);
        final String maxSpeed = req.getParameter(MAX_SPEED);
        final String imagePath = req.getParameter(IMAGE_PATH);
        final String type = req.getParameter(TYPE);
        try {
            Car car = new Car(mark, price, power, acceleration, consumption, engineVolume, tankVolume, trunkVolume, maxSpeed,imagePath, type);
            carService.addCar(car);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_ADMIN + ADMIN_PAGE;
    }
}
