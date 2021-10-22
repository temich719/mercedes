package controller.command.impl;

import controller.command.ICommand;
import service.CodeConfirmGenerator;
import service.Validator;
import service.email.Mail;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SendEmailCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        final String email = req.getParameter("email");
        if (!Validator.validateEmail(email)){
            req.setAttribute("error", "Неверный email!");
            return "codeConfirmNewPassword";
        }
        try {
            String code = CodeConfirmGenerator.generateCode();
            HttpSession session = req.getSession(true);
            session.setAttribute("codeOfConfirm", code);
            session.setAttribute("emailUpdate", email);
            Mail.sendMessage(email, code);
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
        return "confirmationOfNewPassword";
    }
}