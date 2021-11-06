package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
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

public class RegistrationCommand implements ICommand {

    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        final String email = req.getParameter("email");
        try {
            if (userService.isExistingEmail(email.trim())) {
                req.setAttribute("error", "Данная электронная почта уже зарегистрирована!");
                return "registration";
            }
        }
        catch (ServiceException e){
            throw new ControllerException(e);
        }
        // TODO: 06.11.2021 password in char[]
        final String password = req.getParameter("password");
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
