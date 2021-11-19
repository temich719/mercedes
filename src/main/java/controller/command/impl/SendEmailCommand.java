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

public class SendEmailCommand implements ICommand {

    private final static Logger logger = Logger.getLogger(SendEmailCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("We got to SendEmailCommand");
        final String email = req.getParameter("email");
        if (!Validator.validateEmail(email)){
            if (req.getSession().getAttribute("locale").equals("ru"))req.setAttribute("error", "Неверный email!");
            else if (req.getSession().getAttribute("locale").equals("ch")) req.setAttribute("error", "不合規電郵！");
            else req.setAttribute("error", "Wrong email");
            return "codeConfirmNewPassword";
        }
        try {
            String code = CodeConfirmGenerator.generateCode();
            HttpSession session = req.getSession(true);
            session.setAttribute("codeOfConfirm", code);
            session.setAttribute("emailUpdate", email);
            Mail.sendMessage(email, code, req);
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
        return "confirmationOfNewPassword";
    }
}