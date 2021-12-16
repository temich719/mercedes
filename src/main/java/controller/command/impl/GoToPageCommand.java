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

public class GoToPageCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(GoToPageCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to GoToPageCommand");
        final String pageName = req.getParameter(PAGE_NAME);
        Validator.validateInputData(pageName);
        Page<AbstractCar> page;
        try {
            switch (pageName) {
                case JSP_USER + ALL_CARS_PAGE:
                    page = carService.getPageOfAllCars(DEFAULT_PAGE_NUMBER);
                    req.setAttribute(ALL_CARS, page.getElements());
                    req.setAttribute(NUMBERS, page.getCountOfPages());
                    break;
                case JSP_USER + TEST_DRIVE_ORDER_PAGE:
                case JSP_USER + SERVICE_ORDER_PAGE:
                    req.setAttribute(ALL_CARS, carService.getAllCars());
                    break;
                case JSP_USER + CARS_PAGE:
                    page = carService.getPageOfCars(DEFAULT_PAGE_NUMBER);
                    req.setAttribute(AUTOMOBILES, page.getElements());
                    req.setAttribute(NUMBERS, page.getCountOfPages());
                    break;
                case JSP_USER + MINIBUS:
                    page = carService.getPageOfMinibuses(DEFAULT_PAGE_NUMBER);
                    req.setAttribute(MINI_BUS, page.getElements());
                    req.setAttribute(NUMBERS, page.getCountOfPages());
                    break;
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return pageName;
    }
}
