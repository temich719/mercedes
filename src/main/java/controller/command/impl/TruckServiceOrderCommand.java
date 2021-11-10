package controller.command.impl;

import controller.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TruckServiceOrderCommand implements ICommand {

    private final static Logger logger = Logger.getLogger(TruckServiceOrderCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("We got to TruckServiceOrderCommand");
        final String mark = req.getParameter("truckMark");
        req.setAttribute("select", mark);
        return "serviceOrder";
    }
}