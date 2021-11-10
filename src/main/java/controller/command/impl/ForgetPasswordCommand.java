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
            req.setAttribute("err","Пароль должен содержать латинскую букву верхнего и нижнего регистра, цифру, " +
                    "спецсимвол и иметь длину от 8 до 16 символов");
            return "forgetPassword";
        }
        final String confirmNewPassword = req.getParameter("confirmNewPassword");
        if (!newPassword.equals(confirmNewPassword)){
            req.setAttribute("error","Пароль введен неверно!");
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
