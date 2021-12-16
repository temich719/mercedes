package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import dao.entity.AbstractCar;
import dao.entity.Page;
import org.apache.log4j.Logger;
import service.CarService;
import service.ServiceFactory;
import service.exception.ServiceException;
import service.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class SelectPageCarsCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SelectPageCarsCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to SelectPageCarsCommand");
        final String pageNumber = req.getParameter(NUMBER_OF_PAGE);
        Validator.validateInputData(pageNumber);
        try {
            Page<AbstractCar> page = carService.getPageOfCars(pageNumber);
            req.setAttribute(FILTERED, page.getElements());
            req.setAttribute(NUMBERS, page.getCountOfPages());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        req.setAttribute(FLAG, "true");
        req.setAttribute(TYPE_OF_CARS_ON_THIS_PAGE, CARS);
        return JSP_USER + ALL_CARS_PAGE;
    }
}
