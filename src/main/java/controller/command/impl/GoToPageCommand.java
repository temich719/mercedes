package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class GoToPageCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(GoToPageCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to GoToPageCommand");
        return req.getParameter(PAGE_NAME);
    }
}
