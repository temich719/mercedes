package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.JSP_ERRORS;

public class ErrorPageCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(ErrorPageCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to ErrorPageCommand");
        return JSP_ERRORS;
    }
}
