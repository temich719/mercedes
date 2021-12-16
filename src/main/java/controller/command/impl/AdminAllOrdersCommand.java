package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import dao.entity.Order;
import dao.entity.Page;
import org.apache.log4j.Logger;
import service.OrderService;
import service.ServiceFactory;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class AdminAllOrdersCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(AdminAllOrdersCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to AdminAllUsersCommand");
        try {
            Page<Order> page = orderService.getPageOfOrders(DEFAULT_PAGE_NUMBER);
            req.setAttribute(ORDERS, page.getElements());
            req.setAttribute(NUMBERS, page.getCountOfPages());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_ADMIN + ADMIN_ALL_ORDERS_PAGE;
    }
}
