package controller.command.impl;

import controller.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstStepCommand implements ICommand {

    private final static Logger logger = Logger.getLogger(FirstStepCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("We got to FirstStepCommand");
        final String name = req.getParameter("name");
        final String surname = req.getParameter("surname");
        req.getSession().setAttribute("name", name);
        req.getSession().setAttribute("surname", surname);
        return "registration";
    }
}
