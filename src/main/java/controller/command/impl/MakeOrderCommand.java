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

public class MakeOrderCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(MakeOrderCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to MakeOrderCommand");
        String page = JSP_USER + THANKS_PAGE;
        boolean inputDataIsRight = true;
        final String name = req.getParameter(NAME);
        final String surname = req.getParameter(SURNAME);
        final String email = req.getParameter(EMAIL);
        final String phone = req.getParameter(PHONE);
        final String imagePath = req.getParameter(PICTURE);
        try {
            String[] markAndPrice = carService.getMarkAndPriceByImage(imagePath);
            if (name.equals("") || surname.equals("") || email.equals("") || phone.equals("")) {
                req.setAttribute(ERROR, NOT_ALL_REQUIRED_FIELDS_FILLED_MESSAGE);
                req.setAttribute(PICTURE, imagePath);
                if (Objects.isNull(markAndPrice[1])) req.setAttribute(MONEY, markAndPrice[2]);
                else req.setAttribute(PRICE, markAndPrice[1]);
                req.setAttribute(MARK, markAndPrice[0]);
                inputDataIsRight = false;
                page = JSP_USER + FORM_OF_ORDER_PAGE;
            }
            if (!Validator.validateEmail(email) && inputDataIsRight) {
                req.setAttribute(ERROR, INVALID_EMAIL);
                req.setAttribute(PICTURE, imagePath);
                if (Objects.isNull(markAndPrice[1])) req.setAttribute(MONEY, markAndPrice[2]);
                else req.setAttribute(PRICE, markAndPrice[1]);
                req.setAttribute(MARK, markAndPrice[0]);
                inputDataIsRight = false;
                page = JSP_USER + FORM_OF_ORDER_PAGE;
            }
            if (Objects.nonNull(req.getSession().getAttribute(NAME_ACCOUNT)) && inputDataIsRight) {
                if (!name.equals(userService.getNameAndSurname(email).getFirst()) || !surname.equals(userService.getNameAndSurname(email).getSecond())) {
                    req.setAttribute(ERROR, NAME_OR_SURNAME_DOES_NOT_MATCH_USER_EMAIL_MESSAGE);
                    req.setAttribute(PICTURE, imagePath);
                    if (Objects.isNull(markAndPrice[1])) req.setAttribute(MONEY, markAndPrice[2]);
                    else req.setAttribute(PRICE, markAndPrice[1]);
                    req.setAttribute(MARK, markAndPrice[0]);
                    inputDataIsRight = false;
                    page = JSP_USER + FORM_OF_ORDER_PAGE;
                }
            }
            if (inputDataIsRight) {
                if (Objects.isNull(markAndPrice[1])) {
                    Order order = new Order(name, surname, email, CAR_BUYING, markAndPrice[0], markAndPrice[2], phone, null, UNREAD);
                    orderService.addOrder(order);
                    sendMessage(email, markAndPrice[0], markAndPrice[2], req);
                } else {
                    Order order = new Order(name, surname, email, CAR_BUYING, markAndPrice[0], markAndPrice[1], phone, null, UNREAD);
                    orderService.addOrder(order);
                    sendMessage(email, markAndPrice[0], markAndPrice[1], req);
                }
                req.setAttribute(EMAIL, email);
                req.getSession().setAttribute(COUNT, orderService.getCountOfUnreadOrders(email));
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return page;
    }

    private void sendMessage(String email, String mark, String price, HttpServletRequest req) {
        try {
            Mail.sendOrder(email, mark, price, req);
        } catch (IOException | MessagingException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
