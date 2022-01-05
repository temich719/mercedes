package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.exception.ServiceException;
import service.util.Validator;
import service.util.impl.ValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.ControllerStringsStorage.*;

public class ChangeLanguageCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ChangeLanguageCommand.class);
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to ChangeLanguageCommand");
        String locale = req.getParameter(LOCALE);
        try {
            validator.validateInputData(locale);
            req.getSession().setAttribute(LOCALE, locale);
            resp.sendRedirect(INDEX_PAGE + JSP);
        } catch (IOException | ServiceException e) {
            throw new ControllerException(e);
        }
        return null;
    }
}
