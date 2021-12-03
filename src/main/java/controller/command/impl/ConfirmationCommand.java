package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.User;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class ConfirmationCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(ConfirmationCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to ConfirmationCommand");
        String page = JSP_USER + REGISTRATED_INDEX_PAGE;
        final String code = req.getParameter(CODE);
        final String userInput = req.getParameter(CONFIRMATION);
        if (!userInput.equals(code)) {
            req.setAttribute(SCRIPT, "true");
            page = JSP_USER + REGISTRATION;
        } else {
            final String email = req.getParameter(EMAIL);
            final String password = req.getParameter(PASSWORD);
            final String name = req.getParameter(NAME);
            final String surname = req.getParameter(SURNAME);
            try {
                User user = new User(name, surname, REGISTERED, email, password);
                userService.register(user);
            } catch (ServiceException e) {
                throw new ControllerException(e);
            }
            req.getSession().setAttribute(NAME_ACCOUNT, name + " " + surname);
            req.getSession().setAttribute(EMAIL_ACCOUNT, email);
        }
        return page;
    }
}
