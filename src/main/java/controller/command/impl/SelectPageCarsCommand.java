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

public class SelectPageCarsCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(SelectPageCarsCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to SelectPageCarsCommand");
        final String pageNumber = req.getParameter(NUMBER_OF_PAGE);
        try {
            req.setAttribute(FILTERED, carService.getCarsInfoForOnePage(pageNumber));
            req.setAttribute(NUMBERS, carService.getCountOfCarPages());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        req.setAttribute(FLAG, "true");
        req.setAttribute(TYPE_OF_CARS_ON_THIS_PAGE, CARS);
        return JSP_USER + ALL_CARS_PAGE;
    }
}
