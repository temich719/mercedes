package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import org.apache.log4j.Logger;
import service.CarService;
import service.ServiceFactory;
import service.cssEditor.CssEditor;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static controller.ControllerStringsStorage.*;

public class FilterAllCarsCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(FilterAllCarsCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to FilterAllCarsCommand");
        final String carType = req.getParameter(TYPE);
        CssEditor.pressedButton(carType, req);
        try {
            if (carType.equals(CAR)) {
                ArrayList<Car> cars = carService.getCars();
                req.setAttribute(FILTERED, cars);
            } else if (carType.equals(MINIBUS)) {
                ArrayList<Minibus> minibuses = carService.getMinibuses();
                req.setAttribute(FILTERED, minibuses);
            } else {
                ArrayList<Truck> trucks = carService.getTrucks();
                req.setAttribute(FILTERED, trucks);
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        req.setAttribute(FLAG, "true");
        return JSP_USER + ALL_CARS_PAGE;
    }
}
