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

public class SendEmailCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(SendEmailCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to SendEmailCommand");
        String page = JSP_USER + CONFIRMATION_OF_NEW_PASSWORD;
        final String email = req.getParameter(EMAIL);
        try {
            if (!userService.isExistingEmail(email)) {
                req.setAttribute(ERROR, NOT_EXISTING_EMAIL);
                page = JSP_USER + CODE_CONFIRM_NEW_PASSWORD;
            } else if (!Validator.validateEmail(email)) {
                req.setAttribute(ERROR, WRONG_EMAIL);
                page = JSP_USER + CODE_CONFIRM_NEW_PASSWORD;
            } else {
                try {
                    String code = CodeConfirmGenerator.generateCode();
                    HttpSession session = req.getSession(true);
                    session.setAttribute(CODE_OF_CONFIRM, code);
                    session.setAttribute(EMAIL_UPDATE, email);
                    Mail.sendMessage(email, code, req);
                } catch (IOException | MessagingException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return page;
    }
}