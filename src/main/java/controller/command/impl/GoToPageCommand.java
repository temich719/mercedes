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

public class GoToPageCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(GoToPageCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to GoToPageCommand");
        final String pageName = req.getParameter(PAGE_NAME);
        try {
            switch (pageName) {
                case JSP_USER + ALL_CARS_PAGE:
                    req.setAttribute(ALL_CARS, carService.getAllCarsInfoForOnePage(DEFAULT_PAGE_NUMBER));
                    req.setAttribute(NUMBERS, carService.getCountOfAllCarPages());
                    break;
                case JSP_USER + TEST_DRIVE_ORDER_PAGE:
                case JSP_USER + SERVICE_ORDER_PAGE:
                    req.setAttribute(ALL_CARS, carService.getAllCars());
                    break;
                case JSP_USER + CARS_PAGE:
                    req.setAttribute(AUTOMOBILES, carService.getCarsInfoForOnePage(DEFAULT_PAGE_NUMBER));
                    req.setAttribute(NUMBERS, carService.getCountOfCarPages());
                    break;
                case JSP_USER + MINIBUS:
                    req.setAttribute(MINI_BUS, carService.getMinibusesInfoForOnePage(DEFAULT_PAGE_NUMBER));
                    req.setAttribute(NUMBERS, carService.getCountOfMinibusPages());
                    break;
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return pageName;
    }
}
