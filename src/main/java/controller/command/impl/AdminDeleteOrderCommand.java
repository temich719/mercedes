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

import java.util.Objects;

import static controller.ControllerStringsStorage.*;

public class AdminDeleteOrderCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AdminDeleteOrderCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to AdminDeleteOrderCommand");
        final String id = req.getParameter(ID);
        final String numberOfPage = req.getParameter(PAGE_NUMBER);
        try {
            orderService.deleteOrder(Integer.parseInt(id));
            if (numberOfPage.equals("")) {
                req.setAttribute(ORDERS, orderService.getOrderInfoForOnePage(DEFAULT_PAGE_NUMBER));
            } else {
                req.setAttribute(ORDERS, orderService.getOrderInfoForOnePage(numberOfPage));
            }
            req.setAttribute(NUMBERS, orderService.getCountOfOrdersPages());
            req.setAttribute(PAGE_NUMBER, numberOfPage);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_ADMIN + ADMIN_ALL_ORDERS_PAGE;
    }
}
