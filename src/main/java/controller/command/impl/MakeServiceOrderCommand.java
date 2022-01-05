package controller.command.impl;

import controller.command.Command;
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
import service.util.impl.ValidatorImpl;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static controller.ControllerStringsStorage.*;

public class MakeServiceOrderCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(MakeServiceOrderCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final UserService userService = serviceFactory.getUserService();
    private final CarService carService = serviceFactory.getCarService();
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to MakeServiceOrderCommand");
        String page = JSP_USER + SERVICE_ORDER_PAGE;
        boolean inputDataIsCorrect;
        final String userName = req.getParameter(NAME);
        final String userSurname = req.getParameter(SURNAME);
        final String email = req.getParameter(EMAIL);
        final String phone = req.getParameter(PHONE);
        final String date = req.getParameter(DATE);
        final String select = req.getParameter(MARK);
        inputDataIsCorrect = isInputDataIsCorrect(req, userName, userSurname, email, phone, date, select);
        if (inputDataIsCorrect) {
            final String mark;
            if (Objects.isNull(req.getParameter(SELECT_NAME))) {
                mark = req.getParameter(MARK);
            } else {
                mark = req.getParameter(SELECT_NAME);
            }
            try {
                Order order = new Order(userName, userSurname, email, SERVICE, mark, AFTER_INSPECTION, phone, date, UNREAD);
                orderService.addOrder(order);
                try {
                    Mail.sendServiceOrder(email, mark, date, AFTER_INSPECTION, req);
                } catch (IOException | MessagingException e) {
                    LOGGER.error(e.getMessage());
                }
                page = JSP_USER + THANKS_PAGE;
                req.setAttribute(EMAIL, email);
                req.getSession().setAttribute(COUNT, orderService.getCountOfUnreadOrders(email));
            } catch (ServiceException e) {
                throw new ControllerException(e);
            }
        }
        return page;
    }

    private boolean isInputDataIsCorrect(HttpServletRequest req, String userName, String userSurname, String email, String phone, String date, String select) throws ControllerException {
        boolean inputDataIsCorrect = true;
        if (userName.isEmpty() || userSurname.isEmpty() || email.isEmpty() || phone.isEmpty() || date.isEmpty()) {
            req.setAttribute(ERROR, NOT_ALL_REQUIRED_FIELDS_FILLED_MESSAGE);
            checkNameOfMarkOnEmptyCondition(select, req);
            inputDataIsCorrect = false;
        } else if (!validator.validateEmail(email)) {
            req.setAttribute(ERROR, INVALID_EMAIL);
            checkNameOfMarkOnEmptyCondition(select, req);
            inputDataIsCorrect = false;
        } else if (!validator.validatePhoneNumber(phone)) {
            req.setAttribute(ERROR, INVALID_PHONE_NUMBER);
            checkNameOfMarkOnEmptyCondition(select, req);
            inputDataIsCorrect = false;
        } else {
            try {
                if (!validator.validateDate(date)) {
                    req.setAttribute(ERROR, INVALID_DATE);
                    checkNameOfMarkOnEmptyCondition(select, req);
                    inputDataIsCorrect = false;
                }
                String checkName = userService.getUserNameByEmail(email);
                String checkSurname = userService.getUserSurnameByEmail(email);
                if (Objects.nonNull(req.getSession().getAttribute(NAME_ACCOUNT)) && inputDataIsCorrect) {
                    if (!req.getSession().getAttribute(ACCOUNT_NAME).equals(checkName) || !req.getSession().getAttribute(ACCOUNT_SURNAME).equals(checkSurname)) {
                        req.setAttribute(ERROR, NAME_OR_SURNAME_DOES_NOT_MATCH_USER_EMAIL_MESSAGE);
                        checkNameOfMarkOnEmptyCondition(select, req);
                        inputDataIsCorrect = false;
                    }
                } else if (inputDataIsCorrect) {
                    if (Objects.nonNull(checkName) && Objects.nonNull(checkSurname)) {
                        if (!userName.equals(checkName) || !userSurname.equals(checkSurname)) {
                            req.setAttribute(ERROR, NAME_OR_SURNAME_DOES_NOT_MATCH_USER_EMAIL_MESSAGE);
                            checkNameOfMarkOnEmptyCondition(select, req);
                            inputDataIsCorrect = false;
                        }
                    }
                }
            } catch (ServiceException e) {
                throw new ControllerException(e);
            }
        }
        return inputDataIsCorrect;
    }

    private void checkNameOfMarkOnEmptyCondition(String nameOfMark, HttpServletRequest req) throws ControllerException {
        if (!nameOfMark.isEmpty()) {
            req.setAttribute(SELECT, "true");
            req.setAttribute(NAME_OF_MARK, nameOfMark);
        } else {
            try {
                req.setAttribute(ALL_CARS, carService.getAllCars());
            } catch (ServiceException e) {
                throw new ControllerException(e);
            }
        }
    }
}
