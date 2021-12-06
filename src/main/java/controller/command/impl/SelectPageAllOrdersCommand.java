package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.OrderService;
import service.ServiceFactory;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class SelectPageAllOrdersCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(SelectPageAllOrdersCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to SelectPageCommand");
        final String numberOfPage = req.getParameter(NUMBER_OF_PAGE);
        try {
            req.setAttribute(ORDERS, orderService.getOrderInfoForOnePage(numberOfPage));
            req.setAttribute(NUMBERS, orderService.getCountOfOrders());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_ADMIN + ADMIN_ALL_ORDERS_PAGE;
    }
}
