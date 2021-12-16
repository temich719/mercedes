package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.CarService;
import service.ServiceFactory;
import service.exception.ServiceException;
import service.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class AdminDeleteCarCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(AdminDeleteCarCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to AdminDeleteCarCommand");
        final String mark = req.getParameter(SELECT_NAME);
        Validator.validateInputData(mark);
        try {
            carService.deleteCar(mark);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_ADMIN + ADMIN_PAGE;
    }
}
