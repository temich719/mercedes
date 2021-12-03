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

public class AdminGoToDeleteCarCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AdminGoToDeleteCarCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to AdminGoToDeleteCarCommand");
        try {
            req.setAttribute(CARS, carService.getCars());
            req.setAttribute(MINIBUSES, carService.getMinibuses());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_ADMIN + ADMIN_DELETE_PRODUCT_PAGE;
    }
}
