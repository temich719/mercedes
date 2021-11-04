package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;
import service.util.CodeConfirmGenerator;
import service.util.Validator;
import service.email.Mail;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class RegistrationCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String email = req.getParameter("email");
        //make in service
        if (new DataBaseImpl().isExistingEmail(email.trim())){
            req.setAttribute("error", "Данная электронная почта уже зарегистрирована!");
            return "registration";
        }
        final String password = req.getParameter("password");//why we need char[]???
        if (Validator.validateEmail(email.trim())){
            if (Validator.validatePassword(password)){
                req.getSession().setAttribute("email", email);
                req.getSession().setAttribute("password", password);
            }
            else {
                req.setAttribute("err", "Пароль должен содержать латинскую букву верхнего и нижнего регистра, цифру," +
                        "спецсимвол и иметь длину от 8 до 16 символов");
                return "registration";
            }
        }
        else {
            req.setAttribute("error", "Неверный почтовый адрес!");
            return "registration";
        }
        try {
            String code = CodeConfirmGenerator.generateCode();
            HttpSession session = req.getSession(true);
            session.setAttribute("code", code);
            Mail.sendMessage(email, code);
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
        return "confirmation";
    }
}
