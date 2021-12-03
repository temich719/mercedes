package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.ControllerStringsStorage.*;

public class ChangeLanguageCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(ChangeLanguageCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to ChangeLanguageCommand");
        String locale = req.getParameter(LOCALE);
        req.getSession().setAttribute(LOCALE, locale);
        try {
            resp.sendRedirect(INDEX_PAGE + JSP);
        } catch (IOException e) {
            throw new ControllerException(e);
        }
        return null;
    }
}
