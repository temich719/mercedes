package controller.command.impl;

import controller.command.ICommand;
import org.apache.log4j.Logger;
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

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("We got to SendEmailCommand");
        String page = JSP_USER + CONFIRMATION_OF_NEW_PASSWORD;
        final String email = req.getParameter(EMAIL);
        if (!Validator.validateEmail(email)) {
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
        return page;
    }
}