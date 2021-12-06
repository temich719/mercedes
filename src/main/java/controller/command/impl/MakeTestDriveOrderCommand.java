package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.Order;
import org.apache.log4j.Logger;
import service.CarService;
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

public class MakeTestDriveOrderCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(MakeTestDriveOrderCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to MakeTestDriveOrderCommand");
        String page = JSP_USER + THANKS_PAGE;
        boolean inputDataIsRight = true;
        final String userName = req.getParameter(NAME);
        final String userSurname = req.getParameter(SURNAME);
        final String email = req.getParameter(EMAIL);
        final String phone = req.getParameter(PHONE);
        final String date = req.getParameter(DATE);
        final String sel = req.getParameter(DEFINITE_CAR);
        final String def = req.getParameter(DEFINITE_IMAGE);
        if (userName.equals("") || userSurname.equals("") || email.equals("") || phone.equals("") || date.equals("")) {
            req.setAttribute(ERROR, NOT_ALL_REQUIRED_FIELDS_FILLED_MESSAGE);
            if (Objects.nonNull(sel)) {
                req.setAttribute(SELECT, "true");
                req.setAttribute(DEFINITE_IMAGE, def);
                try {
                    req.setAttribute(DEFINITE_CAR, carService.getCarMarkByImage(def));
                } catch (ServiceException e) {
                    throw new ControllerException(e);
                }
            }
            inputDataIsRight = false;
            page = JSP_USER + TEST_DRIVE_ORDER_PAGE;
        }
        if (!Validator.validateEmail(email) && inputDataIsRight) {
            req.setAttribute(ERROR, INVALID_EMAIL);
            if (Objects.nonNull(sel)) {
                req.setAttribute(SELECT, "true");
                req.setAttribute(DEFINITE_IMAGE, def);
                try {
                    req.setAttribute(DEFINITE_CAR, carService.getCarMarkByImage(def));
                } catch (ServiceException e) {
                    throw new ControllerException(e);
                }
            }
            inputDataIsRight = false;
            page = JSP_USER + TEST_DRIVE_ORDER_PAGE;
        }
        try {
            if (Objects.nonNull(req.getSession().getAttribute(NAME_ACCOUNT)) && inputDataIsRight) {
                if (!userName.equals(userService.getNameAndSurname(email).getFirst()) || !userSurname.equals(userService.getNameAndSurname(email).getSecond())) {
                    req.setAttribute(ERROR, NAME_OR_SURNAME_DOES_NOT_MATCH_USER_EMAIL_MESSAGE);
                    if (Objects.nonNull(sel)) {
                        req.setAttribute(SELECT, "true");
                        req.setAttribute(DEFINITE_IMAGE, def);
                        try {
                            req.setAttribute(DEFINITE_CAR, carService.getCarMarkByImage(def));
                        } catch (ServiceException e) {
                            throw new ControllerException(e);
                        }
                    }
                    inputDataIsRight = false;
                    page = JSP_USER + TEST_DRIVE_ORDER_PAGE;
                }
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        if (inputDataIsRight) {
            String mark;
            String image;
            try {
                if (Objects.isNull(req.getParameter(SELECT_NAME))) {
                    image = req.getParameter(DEFINITE_IMAGE);
                    mark = carService.getCarMarkByImage(image);
                } else {
                    mark = req.getParameter(SELECT_NAME);
                }
                Order order = new Order(userName, userSurname, email, TEST_DRIVE, mark, TEST_DRIVE_PRICE, phone, date, UNREAD);
                orderService.addOrder(order);
                try {
                    Mail.sendTestDriveOrder(email, mark, date, TEST_DRIVE_PRICE, req);
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
