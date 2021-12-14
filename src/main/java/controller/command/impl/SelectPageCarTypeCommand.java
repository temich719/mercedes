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

public class SelectPageCarTypeCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(SelectPageCarTypeCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to SelectPageCarTypeCommand");
        final String numberOfPage = req.getParameter(NUMBER_OF_PAGE);
        Validator.validateInputData(numberOfPage);
        try {
            req.setAttribute(AUTOMOBILES, carService.getCarsInfoForOnePage(numberOfPage));
            req.setAttribute(NUMBERS, carService.getCountOfCarPages());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_USER + CARS_PAGE;
    }
}
