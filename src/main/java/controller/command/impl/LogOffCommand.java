package controller.command.impl;

import controller.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class LogOffCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(LogOffCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("We got to LogOffCommand");
        req.getSession().setAttribute(NAME_ACCOUNT,null);
        req.getSession().setAttribute(EMAIL_ACCOUNT, null);
        req.getSession().setAttribute(ACCOUNT_NAME, null);
        req.getSession().setAttribute(ACCOUNT_SURNAME, null);
        req.getSession().setAttribute(IS_ADMIN, null);
        return INDEX_PAGE;
    }
}

