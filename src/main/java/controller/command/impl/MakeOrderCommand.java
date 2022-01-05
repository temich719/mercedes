package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import dao.entity.AbstractCar;
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

public class MakeOrderCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(MakeOrderCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final UserService userService = serviceFactory.getUserService();
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to MakeOrderCommand");
        String page = JSP_USER + FORM_OF_ORDER_PAGE;
        boolean inputDataIsCorrect;
        final String name = req.getParameter(NAME);
        final String surname = req.getParameter(SURNAME);
        final String email = req.getParameter(EMAIL);
        final String phone = req.getParameter(PHONE);
        final String imagePath = req.getParameter(PICTURE);
        try {
            AbstractCar abstractCar = carService.getAnyCarByImage(imagePath);
            inputDataIsCorrect = isInputDataIsCorrect(req, name, surname, email, phone, imagePath, abstractCar);
            if (inputDataIsCorrect) {
                Order order = new Order(name, surname, email, CAR_BUYING, abstractCar.getNameOfMark(), abstractCar.getPrice(), phone, null, UNREAD);
                orderService.addOrder(order);
                sendMessage(email, abstractCar.getNameOfMark(), abstractCar.getPrice(), req);
                req.setAttribute(EMAIL, email);
                req.getSession().setAttribute(COUNT, orderService.getCountOfUnreadOrders(email));
                page = JSP_USER + THANKS_PAGE;
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return page;
    }

    private boolean isInputDataIsCorrect(HttpServletRequest req, String name, String surname, String email, String phone, String imagePath, AbstractCar abstractCar) throws ServiceException {
        boolean inputDataIsCorrect = true;
        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            req.setAttribute(ERROR, NOT_ALL_REQUIRED_FIELDS_FILLED_MESSAGE);
            req.setAttribute(PICTURE, imagePath);
            req.setAttribute(PRICE, abstractCar.getPrice());
            req.setAttribute(MARK, abstractCar.getNameOfMark());
            inputDataIsCorrect = false;
        }
        if (!validator.validateEmail(email) && inputDataIsCorrect) {
            req.setAttribute(ERROR, INVALID_EMAIL);
            req.setAttribute(PICTURE, imagePath);
            req.setAttribute(PRICE, abstractCar.getPrice());
            req.setAttribute(MARK, abstractCar.getNameOfMark());
            inputDataIsCorrect = false;
        }
        if (!validator.validatePhoneNumber(phone) && inputDataIsCorrect){
            req.setAttribute(ERROR, INVALID_PHONE_NUMBER);
            req.setAttribute(PICTURE, imagePath);
            req.setAttribute(PRICE, abstractCar.getPrice());
            req.setAttribute(MARK, abstractCar.getNameOfMark());
            inputDataIsCorrect = false;
        }
        String checkName = userService.getUserNameByEmail(email);
        String checkSurname = userService.getUserSurnameByEmail(email);
        if (Objects.nonNull(req.getSession().getAttribute(NAME_ACCOUNT)) && inputDataIsCorrect) {
            if (!req.getSession().getAttribute(ACCOUNT_NAME).equals(checkName) || !req.getSession().getAttribute(ACCOUNT_SURNAME).equals(checkSurname)) {
                req.setAttribute(ERROR, NAME_OR_SURNAME_DOES_NOT_MATCH_USER_EMAIL_MESSAGE);
                req.setAttribute(PICTURE, imagePath);
                req.setAttribute(PRICE, abstractCar.getPrice());
                req.setAttribute(MARK, abstractCar.getNameOfMark());
                inputDataIsCorrect = false;
            }
        } else if (inputDataIsCorrect) {
            if (Objects.nonNull(checkName) && Objects.nonNull(checkSurname)) {
                if (!name.equals(checkName) || !surname.equals(checkSurname)) {
                    req.setAttribute(ERROR, NAME_OR_SURNAME_DOES_NOT_MATCH_USER_EMAIL_MESSAGE);
                    req.setAttribute(PICTURE, imagePath);
                    req.setAttribute(PRICE, abstractCar.getPrice());
                    req.setAttribute(MARK, abstractCar.getNameOfMark());
                    inputDataIsCorrect = false;
                }
            }
        }
        return inputDataIsCorrect;
    }

    private void sendMessage(String email, String mark, String price, HttpServletRequest req) {
        try {
            Mail.sendOrder(email, mark, price, req);
        } catch (IOException | MessagingException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
