package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.Order;
import org.apache.log4j.Logger;
import service.OrderService;
import service.ServiceFactory;
import service.UserService;
import service.email.Mail;
import service.exception.ServiceException;
import service.util.Validator;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static controller.ControllerStringsStorage.*;

public class MakeServiceOrderCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(MakeServiceOrderCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to MakeServiceOrderCommand");
        String page = JSP_USER + THANKS_PAGE;
        boolean inputDataIsRight = true;
        final String userName = req.getParameter(NAME);
        final String userSurname = req.getParameter(SURNAME);
        final String email = req.getParameter(EMAIL);
        final String phone = req.getParameter(PHONE);
        final String date = req.getParameter(DATE);
        final String select = req.getParameter(SELECT);
        if (userName.equals("") || userSurname.equals("") || email.equals("") || phone.equals("") || date.equals("")) {
            req.setAttribute(ERROR, NOT_ALL_REQUIRED_FIELDS_FILLED_MESSAGE);
            req.setAttribute(SELECT, select);
            inputDataIsRight = false;
            page = JSP_USER + SERVICE_ORDER_PAGE;
        }
        if (!Validator.validateEmail(email) && inputDataIsRight) {
            req.setAttribute(ERROR, INVALID_EMAIL);
            req.setAttribute(SELECT, select);
            inputDataIsRight = false;
            page = JSP_USER + SERVICE_ORDER_PAGE;
        }
        try {
            if (Objects.nonNull(req.getSession().getAttribute(NAME_ACCOUNT)) && inputDataIsRight) {
                if (!userName.equals(userService.getUserNameByEmail(email)) || !userSurname.equals(userService.getUserSurnameByEmail(email))) {
                    req.setAttribute(ERROR, NAME_OR_SURNAME_DOES_NOT_MATCH_USER_EMAIL_MESSAGE);
                    req.setAttribute(SELECT, select);
                    inputDataIsRight = false;
                    page = JSP_USER + SERVICE_ORDER_PAGE;
                }
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        if (inputDataIsRight) {
            final String mark;
            if (Objects.isNull(req.getParameter(SELECT_NAME))) mark = req.getParameter(MARK);
            else mark = req.getParameter(SELECT_NAME);
            try {
                Order order = new Order(userName, userSurname, email, SERVICE, mark, AFTER_INSPECTION, phone, date, UNREAD);
                orderService.addOrder(order);
                try {
                    Mail.sendServiceOrder(email, mark, date, AFTER_INSPECTION, req);
                } catch (IOException | MessagingException e) {
                    LOGGER.error(e.getMessage());
                }
                req.setAttribute(EMAIL, email);
                req.getSession().setAttribute(COUNT, orderService.getCountOfUnreadOrders(email));
            } catch (ServiceException e) {
                throw new ControllerException(e);
            }
        }
        return page;
    }
}
