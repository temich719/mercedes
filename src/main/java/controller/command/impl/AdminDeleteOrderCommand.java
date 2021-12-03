package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.Order;
import org.apache.log4j.Logger;
import service.OrderService;
import service.ServiceFactory;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class AdminDeleteOrderCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AdminDeleteOrderCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to AdminDeleteOrderCommand");
        final String name = req.getParameter(NAME);
        final String surname = req.getParameter(SURNAME);
        final String service = req.getParameter(SERVICE);
        final String mark = req.getParameter(MARK);
        final String price = req.getParameter(PRICE);
        final String date = req.getParameter(DATE);
        final String phone = req.getParameter(PHONE);
        final String email = req.getParameter(EMAIL);
        final Order order = new Order(name, surname, email, service, mark, price, phone, date, "");
        try {
            orderService.deleteOrder(order);
            req.setAttribute(ORDERS, orderService.getListOfOrders());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_ADMIN + ADMIN_ALL_ORDERS_PAGE;
    }
}
