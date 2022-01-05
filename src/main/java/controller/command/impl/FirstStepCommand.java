package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.exception.ServiceException;
import service.util.Validator;
import service.util.impl.ValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class FirstStepCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(FirstStepCommand.class);
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to FirstStepCommand");
        final String name = req.getParameter(NAME);
        final String surname = req.getParameter(SURNAME);
        try {
            validator.validateInputData(name, surname);
            req.getSession().setAttribute(NAME, name);
            req.getSession().setAttribute(SURNAME, surname);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_USER + REGISTRATION;
    }
}
