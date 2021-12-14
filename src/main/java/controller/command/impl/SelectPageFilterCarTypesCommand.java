package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.CarService;
import service.ServiceFactory;
import service.exception.ServiceException;
import service.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class SelectPageFilterCarTypesCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(SelectPageFilterCarTypesCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to SelectPageFilterCarTypesCommand");
        final String pageNumber = req.getParameter(NUMBER_OF_PAGE);
        final String carType = req.getParameter(CAR_TYPE);
        Validator.validateInputData(pageNumber, carType);
        try {
            req.setAttribute(CARS, carService.getCarsInfoForOnePageAccordingToType(pageNumber, carType));
            req.setAttribute(NUMBERS, carService.getCountOfCarPagesAccordingToType(carType));
            req.setAttribute(INDICATOR, "true");
            req.setAttribute(CHOICE, carType);
            req.setAttribute(FLAG, "true");
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_USER + CARS_PAGE;
    }
}
