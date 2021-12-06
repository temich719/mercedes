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

import static controller.ControllerStringsStorage.*;

public class ForgetPasswordCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(ForgetPasswordCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to ForgetPasswordCommand");
        String page = JSP_USER + REGISTRATED_INDEX_PAGE;
        final String newPassword = req.getParameter(NEW_PASSWORD);
        final String email = req.getParameter(EMAIL_UPDATE);
        if (!Validator.validatePassword(newPassword)) {
            req.setAttribute(ERR, EASY_PASSWORD_ERROR_MESSAGE);
            page = JSP_USER + FORGET_PASSWORD_PAGE;
        } else {
            final String confirmNewPassword = req.getParameter(CONFIRM_NEW_PASSWORD);
            if (!newPassword.equals(confirmNewPassword)) {
                req.setAttribute(ERROR, INCORRECT_PASSWORD_INPUT);
                page = JSP_USER + FORGET_PASSWORD_PAGE;
            } else {
                try {
                    userService.updatePassword(email, newPassword);
                    Pair pair = userService.getNameAndSurname(email);
                    HttpSession session = req.getSession(true);
                    session.setAttribute(NAME_ACCOUNT, pair.getFirst() + " " + pair.getSecond());
                    session.setAttribute(EMAIL_ACCOUNT, email);
                } catch (ServiceException e) {
                    throw new ControllerException(e);
                }
            }
        }
        return page;
    }
}
