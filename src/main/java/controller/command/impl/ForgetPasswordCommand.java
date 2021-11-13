package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.Pair;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;
import service.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ForgetPasswordCommand implements ICommand {

    private final static Logger logger = Logger.getLogger(ForgetPasswordCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        logger.info("We got to ForgetPasswordCommand");
        final String newPassword = req.getParameter("newPassword");
        final String email = req.getParameter("emailUpdate");
        if (!Validator.validatePassword(newPassword)){
            if (req.getSession().getAttribute("locale").equals("ru"))
            req.setAttribute("err","Пароль должен содержать латинскую букву верхнего и нижнего регистра, цифру, " +
                    "спецсимвол и иметь длину от 8 до 16 символов");
            else if (req.getSession().getAttribute("locale").equals("ch"))
                req.setAttribute("err","密碼必須包含大小寫拉丁字母、數字、特殊字符且長度為 8 到 16 個字符");
            else req.setAttribute("err", "The password must contain an upper and lower case Latin letter, " +
                        "a number, a special character and have a length of 8 to 16 characters ");
            return "forgetPassword";
        }
        final String confirmNewPassword = req.getParameter("confirmNewPassword");
        if (!newPassword.equals(confirmNewPassword)){
            if (req.getSession().getAttribute("locale").equals("ru")) req.setAttribute("error","Пароль введен неверно!");
            else if (req.getSession().getAttribute("locale").equals("ch"))req.setAttribute("error","密碼輸入錯誤！");
            else req.setAttribute("error","The password was entered incorrectly! ");
            return "forgetPassword";
        }
        try {
            userService.updatePassword(email, newPassword);
            Pair pair = userService.getName(email);
            HttpSession session = req.getSession(true);
            session.setAttribute("nameAccount", pair.getFirst() + " " + pair.getSecond());
            session.setAttribute("emailAccount", email);
        }
        catch (ServiceException e){
            throw new ControllerException(e);
        }
        return "registratedIndex";
    }
}
