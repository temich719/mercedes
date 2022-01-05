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

public class SelectPageFilterCarTypesCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SelectPageFilterCarTypesCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to SelectPageFilterCarTypesCommand");
        final String pageNumber = req.getParameter(NUMBER_OF_PAGE);
        final String carType = req.getParameter(CAR_TYPE);
        try {
            validator.validateInputData(pageNumber, carType);
            Page<AbstractCar> page = carService.getPageOfCarsAccordingToType(pageNumber, carType);
            req.setAttribute(CARS, page.getElements());
            req.setAttribute(NUMBERS, page.getCountOfPages());
            req.setAttribute(INDICATOR, "true");
            req.setAttribute(CHOICE, carType);
            req.setAttribute(FLAG, "true");
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_USER + CARS_PAGE;
    }
}
