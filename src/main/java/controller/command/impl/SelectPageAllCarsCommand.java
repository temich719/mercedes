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
import service.util.impl.ValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class SelectPageAllCarsCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SelectPageAllCarsCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to SelectPageAllCarsCommand");
        final String pageNumber = req.getParameter(NUMBER_OF_PAGE);
        try {
            validator.validateInputData(pageNumber);
            Page<AbstractCar> page = carService.getPageOfAllCars(pageNumber);
            req.setAttribute(ALL_CARS, page.getElements());
            req.setAttribute(NUMBERS, page.getCountOfPages());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_USER + ALL_CARS_PAGE;
    }
}
