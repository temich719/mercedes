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

public class MakeTestDriveOrderCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(MakeTestDriveOrderCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final UserService userService = serviceFactory.getUserService();
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to MakeTestDriveOrderCommand");
        String page = JSP_USER + TEST_DRIVE_ORDER_PAGE;
        boolean inputDataIsCorrect;
        final String userName = req.getParameter(NAME);
        final String userSurname = req.getParameter(SURNAME);
        final String email = req.getParameter(EMAIL);
        final String phone = req.getParameter(PHONE);
        final String date = req.getParameter(DATE);
        final String nameOfMark = req.getParameter(NAME_OF_MARK);
        inputDataIsCorrect = isInputDataIsCorrect(req, userName, userSurname, email, phone, date, nameOfMark);
        if (inputDataIsCorrect) {
            String mark;
            try {
                if (nameOfMark.isEmpty()) {
                    mark = req.getParameter(SELECT_NAME);
                } else {
                    mark = nameOfMark;
                }
                Order order = new Order(userName, userSurname, email, TEST_DRIVE, mark, TEST_DRIVE_PRICE, phone, date, UNREAD);
                orderService.addOrder(order);
                try {
                    Mail.sendTestDriveOrder(email, mark, date, TEST_DRIVE_PRICE, req);
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

    private boolean isInputDataIsCorrect(HttpServletRequest req, String userName, String userSurname, String email, String phone, String date, String nameOfMark) throws ControllerException {
        boolean inputDataIsCorrect = true;
        if (userName.isEmpty() || userSurname.isEmpty() || email.isEmpty() || phone.isEmpty() || date.isEmpty()) {
            req.setAttribute(ERROR, NOT_ALL_REQUIRED_FIELDS_FILLED_MESSAGE);
            checkNameOfMarkOnEmptyCondition(nameOfMark, req);
            inputDataIsCorrect = false;
        } else if (!validator.validateEmail(email)) {
            req.setAttribute(ERROR, INVALID_EMAIL);
            checkNameOfMarkOnEmptyCondition(nameOfMark, req);
            inputDataIsCorrect = false;
        } else if (!validator.validatePhoneNumber(phone)) {
            req.setAttribute(ERROR, INVALID_PHONE_NUMBER);
            checkNameOfMarkOnEmptyCondition(nameOfMark, req);
            inputDataIsCorrect = false;
        } else {
            try {
                if (!validator.validateDate(date)) {
                    req.setAttribute(ERROR, INVALID_DATE);
                    checkNameOfMarkOnEmptyCondition(nameOfMark, req);
                    inputDataIsCorrect = false;
                }
                String checkName = userService.getUserNameByEmail(email);
                String checkSurname = userService.getUserSurnameByEmail(email);
                if (Objects.nonNull(req.getSession().getAttribute(NAME_ACCOUNT)) && inputDataIsCorrect) {
                    if (!req.getSession().getAttribute(ACCOUNT_NAME).equals(checkName) || !req.getSession().getAttribute(ACCOUNT_SURNAME).equals(checkSurname)) {
                        req.setAttribute(ERROR, NAME_OR_SURNAME_DOES_NOT_MATCH_USER_EMAIL_MESSAGE);
                        checkNameOfMarkOnEmptyCondition(nameOfMark, req);
                        inputDataIsCorrect = false;
                    }
                } else if (inputDataIsCorrect) {
                    if (Objects.nonNull(checkName) && Objects.nonNull(checkSurname)) {
                        if (!userName.equals(checkName) || !userSurname.equals(checkSurname)) {
                            req.setAttribute(ERROR, NAME_OR_SURNAME_DOES_NOT_MATCH_USER_EMAIL_MESSAGE);
                            checkNameOfMarkOnEmptyCondition(nameOfMark, req);
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
