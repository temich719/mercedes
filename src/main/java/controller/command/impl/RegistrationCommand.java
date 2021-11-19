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

public class RegistrationCommand implements ICommand {

    private final static Logger logger = Logger.getLogger(RegistrationCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        logger.info("We got to RegistrationCommand");
        final String email = req.getParameter("email");
        try {
            if (userService.isExistingEmail(email.trim())) {
                if (req.getSession().getAttribute("locale").equals("ru")) req.setAttribute("error", "Данная электронная почта уже зарегистрирована!");
                else if (req.getSession().getAttribute("locale").equals("ch"))req.setAttribute("error", "這個電子郵件已被註冊！ ");
                else req.setAttribute("error", "This email is already registered! ");
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
                if (req.getSession().getAttribute("locale").equals("ru"))
                req.setAttribute("err", "Пароль должен содержать латинскую букву верхнего и нижнего регистра, цифру," +
                        "спецсимвол и иметь длину от 8 до 16 символов");
                else if (req.getSession().getAttribute("locale").equals("ch"))
                    req.setAttribute("err", "密碼必須包含大小寫拉丁字母、數字、特殊字符且長度為 8 到 16 個字符 ");
                else req.setAttribute("err", "The password must contain an upper and lower case Latin letter, a number, " +
                            "a special character and have a length of 8 to 16 characters ");
                return "registration";
            }
        }
        else {
            if (req.getSession().getAttribute("locale").equals("ru")) req.setAttribute("error", "Неверный почтовый адрес!");
            else if (req.getSession().getAttribute("locale").equals("ch"))req.setAttribute("error", "無效的郵寄地址！");
            else req.setAttribute("error", "Invalid mailing address! ");
            return "registration";
        }
        try {
            String code = CodeConfirmGenerator.generateCode();
            HttpSession session = req.getSession(true);
            session.setAttribute("code", code);
            Mail.sendMessage(email, code, req);
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
        return "confirmation";
    }
}
