package controller.command.impl;

import controller.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class TruckServiceOrderCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(TruckServiceOrderCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("We got to TruckServiceOrderCommand");
        final String mark = req.getParameter(TRUCK_MARK);
        req.setAttribute(SELECT, mark);
        return JSP_USER + SERVICE_ORDER_PAGE;
    }
}