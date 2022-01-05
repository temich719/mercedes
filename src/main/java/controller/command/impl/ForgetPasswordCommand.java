package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.OrderService;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;
import service.util.Validator;
import service.util.impl.ValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static controller.ControllerStringsStorage.*;

public class ForgetPasswordCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(ForgetPasswordCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to ForgetPasswordCommand");
        String page = JSP_USER + REGISTRATED_INDEX_PAGE;
        final String newPassword = req.getParameter(NEW_PASSWORD);
        final String email = req.getParameter(EMAIL_UPDATE);
        try {
            validator.validateInputData(newPassword, email);
            if (!validator.validatePassword(newPassword)) {
                req.setAttribute(ERR, EASY_PASSWORD_ERROR_MESSAGE);
                page = JSP_USER + FORGET_PASSWORD_PAGE;
            } else {
                final String confirmNewPassword = req.getParameter(CONFIRM_NEW_PASSWORD);
                if (!newPassword.equals(confirmNewPassword)) {
                    req.setAttribute(ERROR, INCORRECT_PASSWORD_INPUT);
                    page = JSP_USER + FORGET_PASSWORD_PAGE;
                } else {
                    userService.updatePassword(email, newPassword);
                    String name = userService.getUserNameByEmail(email);
                    String surname = userService.getUserSurnameByEmail(email);
                    HttpSession session = req.getSession(true);
                    session.setAttribute(NAME_ACCOUNT, name + " " + surname);
                    session.setAttribute(ACCOUNT_NAME, name);
                    session.setAttribute(ACCOUNT_SURNAME, surname);
                    session.setAttribute(EMAIL_ACCOUNT, email);
                    session.setAttribute(COUNT, orderService.getCountOfUnreadOrders(email));
                    if (userService.getUserAccessTypeByEmail(email).equals(ADMIN)) {
                        session.setAttribute(IS_ADMIN, "true");
                    }
                }
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return page;
    }
}
