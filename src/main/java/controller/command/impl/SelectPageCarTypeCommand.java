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

public class SelectPageCarTypeCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SelectPageCarTypeCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to SelectPageCarTypeCommand");
        final String numberOfPage = req.getParameter(NUMBER_OF_PAGE);
        try {
            validator.validateInputData(numberOfPage);
            Page<AbstractCar> page = carService.getPageOfCars(numberOfPage);
            req.setAttribute(AUTOMOBILES, page.getElements());
            req.setAttribute(NUMBERS, page.getCountOfPages());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_USER + CARS_PAGE;
    }
}
