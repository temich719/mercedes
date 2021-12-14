package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;
import service.util.CodeConfirmGenerator;
import service.util.Validator;
import service.email.Mail;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static controller.ControllerStringsStorage.*;

public class RegistrationCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(RegistrationCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to RegistrationCommand");
        String page = JSP_USER + REGISTRATION_PAGE;
        boolean inputDataIsRight;
        final String email = req.getParameter(EMAIL);
        final String password = req.getParameter(PASSWORD);
        Validator.validateInputData(email, password);
        inputDataIsRight = isInputDataIsRight(req, email, password);
        if (inputDataIsRight) {
            try {
                String code = CodeConfirmGenerator.generateCode();
                HttpSession session = req.getSession(true);
                session.setAttribute(CODE, code);
                Mail.sendMessage(email, code, req);
                page = JSP_USER + CONFIRMATION_PAGE;
            } catch (IOException | MessagingException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return page;
    }

    private boolean isInputDataIsRight(HttpServletRequest req, String email, String password) throws ControllerException {
        boolean inputDataIsRight = true;
        try {
            if (userService.isExistingEmail(email.trim())) {
                req.setAttribute(ERROR, EMAIL_ALREADY_EXISTS);
                inputDataIsRight = false;
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        if (Validator.validateEmail(email.trim()) && inputDataIsRight) {
            if (Validator.validatePassword(password)) {
                req.getSession().setAttribute(EMAIL, email);
                req.getSession().setAttribute(PASSWORD, password);
            } else {
                req.setAttribute(ERR, PASSWORD_REQUIREMENTS);
                inputDataIsRight = false;
            }
        } else if (inputDataIsRight) {
            req.setAttribute(ERROR, INVALID_EMAIL);
            inputDataIsRight = false;
        }
        return inputDataIsRight;
    }
}
