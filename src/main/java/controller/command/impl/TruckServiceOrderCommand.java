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

public class TruckServiceOrderCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(TruckServiceOrderCommand.class);
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to TruckServiceOrderCommand");
        final String mark = req.getParameter(TRUCK_MARK);
        try {
            validator.validateInputData(mark);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        req.setAttribute(SELECT, mark);
        return JSP_USER + SERVICE_ORDER_PAGE;
    }
}