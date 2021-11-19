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

public class AdminGoDefiniteCarInfoCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(AdminGoDefiniteCarInfoCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        logger.info("We got to AdminGoDefiniteCarInfoCommand");
        try {
            final Car car = carService.getCarByMark(req.getParameter("selectName"));
            req.setAttribute("mark", car.getNameOfMark());
            req.setAttribute("price", car.getPrice().replaceAll("\\s",""));
            req.setAttribute("power", car.getPower());
            req.setAttribute("acceleration", car.getAccelerationTillHundred());
            req.setAttribute("consumption", car.getConsumption());
            req.setAttribute("engine", car.getEngineVolume());
            req.setAttribute("tank", car.getTankVolume());
            req.setAttribute("trunk", car.getTrunkVolume());
            req.setAttribute("maxSpeed", car.getMaxSpeed());
            req.setAttribute("image", car.getImagePath());
            req.setAttribute("type", car.getType());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return "adminCarDescription";
    }
}
