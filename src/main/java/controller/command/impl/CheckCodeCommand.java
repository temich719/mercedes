package controller.command.impl;

import controller.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class CheckCodeCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(CheckCodeCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("We got to CheckCodeCommand");
        String page = JSP_USER + FORGET_PASSWORD_PAGE;
        final String code = req.getParameter(CODE_OF_CONFIRM);
        final String input = req.getParameter(CONFIRMATION);
        if (!code.equals(input)) {
            req.setAttribute(SCRIPT, "true");
            page = JSP_USER + ENTER_PAGE;
        }
        return page;
    }
}