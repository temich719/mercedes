package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;
import service.util.CodeConfirmGenerator;
import service.util.Validator;
import service.util.impl.ValidatorImpl;
import service.email.Mail;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static controller.ControllerStringsStorage.*;

public class RegistrationCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(RegistrationCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to RegistrationCommand");
        String page = JSP_USER + REGISTRATION_PAGE;
        boolean inputDataIsCorrect;
        final String email = req.getParameter(EMAIL);
        final String password = req.getParameter(PASSWORD);
        try {
            validator.validateInputData(email, password);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        inputDataIsCorrect = isInputDataIsCorrect(req, email, password);
        if (inputDataIsCorrect) {
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

    private boolean isInputDataIsCorrect(HttpServletRequest req, String email, String password) throws ControllerException {
        boolean inputDataIsCorrect = true;
        try {
            if (userService.isExistingEmail(email.trim())) {
                req.setAttribute(ERROR, EMAIL_ALREADY_EXISTS);
                inputDataIsCorrect = false;
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        if (validator.validateEmail(email.trim()) && inputDataIsCorrect) {
            if (validator.validatePassword(password)) {
                req.getSession().setAttribute(EMAIL, email);
                req.getSession().setAttribute(PASSWORD, password);
            } else {
                req.setAttribute(ERR, PASSWORD_REQUIREMENTS);
                inputDataIsCorrect = false;
            }
        } else if (inputDataIsCorrect) {
            req.setAttribute(ERROR, INVALID_EMAIL);
            inputDataIsCorrect = false;
        }
        return inputDataIsCorrect;
    }
}
