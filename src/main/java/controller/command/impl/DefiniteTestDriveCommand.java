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

public class DefiniteTestDriveCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(DefiniteTestDriveCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to DefiniteTestDriveCommand");
        final String img = req.getParameter(MARK_TO_ORDER);
        try {
            req.setAttribute(DEFINITE_CAR, carService.getCarMarkByImage(img));
            req.setAttribute(DEFINITE_IMAGE, img);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        req.setAttribute(WHETHER_LIST_OF_CARS_OR_DEFINITE_CAR, img);
        return JSP_USER + TEST_DRIVE_ORDER_PAGE;
    }
}
