package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import dao.entity.AbstractCar;
import dao.entity.Page;
import dao.entity.car.Truck;
import org.apache.log4j.Logger;
import service.CarService;
import service.ServiceFactory;
import service.cssEditor.CssEditor;
import service.exception.ServiceException;
import service.util.Validator;
import service.util.impl.ValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static controller.ControllerStringsStorage.*;

public class FilterAllCarsCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(FilterAllCarsCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to FilterAllCarsCommand");
        final String carType = req.getParameter(TYPE);
        try {
            validator.validateInputData(carType);
            CssEditor.pressedButton(carType, req);
            Page<AbstractCar> page;
            if (carType.equals(CAR)) {
                page = carService.getPageOfCars(DEFAULT_PAGE_NUMBER);
                req.setAttribute(NUMBERS, page.getCountOfPages());
                req.setAttribute(FILTERED, page.getElements());
                req.setAttribute(TYPE_OF_CARS_ON_THIS_PAGE, CARS);
            } else if (carType.equals(MINIBUS)) {
                page = carService.getPageOfMinibuses(DEFAULT_PAGE_NUMBER);
                req.setAttribute(NUMBERS, page.getCountOfPages());
                req.setAttribute(FILTERED, page.getElements());
                req.setAttribute(TYPE_OF_CARS_ON_THIS_PAGE, MINIBUSES);
            } else {
                List<Truck> trucks = carService.getTrucks();
                req.setAttribute(FILTERED, trucks);
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        req.setAttribute(FLAG, "true");
        return JSP_USER + ALL_CARS_PAGE;
    }
}
