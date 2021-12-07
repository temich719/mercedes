package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.CarService;
import service.ServiceFactory;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class SelectPageAllCarsCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(SelectPageAllCarsCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to SelectPageAllCarsCommand");
        final String pageNumber = req.getParameter(NUMBER_OF_PAGE);
        try {
            req.setAttribute(ALL_CARS, carService.getAllCarsInfoForOnePage(pageNumber));
            req.setAttribute(NUMBERS, carService.getCountOfAllCarPages());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_USER + ALL_CARS_PAGE;
    }
}
