package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class TruckServiceOrderCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(TruckServiceOrderCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to TruckServiceOrderCommand");
        final String mark = req.getParameter(TRUCK_MARK);
        Validator.validateInputData(mark);
        req.setAttribute(SELECT, mark);
        return JSP_USER + SERVICE_ORDER_PAGE;
    }
}