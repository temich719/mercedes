package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.exception.ServiceException;
import service.util.Validator;
import service.util.impl.ValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class DefiniteTestDriveCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(DefiniteTestDriveCommand.class);
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to DefiniteTestDriveCommand");
        final String mark = req.getParameter(NAME_OF_MARK);
        try {
            validator.validateInputData(mark);
            req.setAttribute(NAME_OF_MARK, mark);
            req.setAttribute(WHETHER_LIST_OF_CARS_OR_DEFINITE_CAR, "true");
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_USER + TEST_DRIVE_ORDER_PAGE;
    }
}
