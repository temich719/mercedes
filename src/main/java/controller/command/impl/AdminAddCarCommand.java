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

public class AdminAddCarCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(AdminAddCarCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        logger.info("We got to AdminAddCarCommand");
        final String mark = req.getParameter("mark");
        final String price = req.getParameter("price");
        final String power = req.getParameter("power");
        final String acceleration = req.getParameter("acceleration");
        final String consumption = req.getParameter("consumption");
        final String engineVolume = req.getParameter("engineVolume");
        final String tankVolume = req.getParameter("tankVolume");
        final String trunkVolume = req.getParameter("trunkVolume");
        final String maxSpeed = req.getParameter("maxSpeed");
        final String imagePath = req.getParameter("image");
        final String type = req.getParameter("type");
        try {
            carService.addCar(new Car(mark, price, power, acceleration, consumption, engineVolume, tankVolume, trunkVolume, maxSpeed,imagePath, type));
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return "adminPage";
    }
}
