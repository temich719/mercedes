package controller.command.impl;

import controller.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class FirstStepCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(FirstStepCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("We got to FirstStepCommand");
        final String name = req.getParameter(NAME);
        final String surname = req.getParameter(SURNAME);
        req.getSession().setAttribute(NAME, name);
        req.getSession().setAttribute(SURNAME, surname);
        return JSP_USER + REGISTRATION;
    }
}
