package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import dao.entity.Order;
import dao.entity.Page;
import org.apache.log4j.Logger;
import service.OrderService;
import service.ServiceFactory;
import service.exception.ServiceException;
import service.util.Validator;
import service.util.impl.ValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Objects;

import static controller.ControllerStringsStorage.*;

public class AdminDeleteOrderCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(AdminDeleteOrderCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to AdminDeleteOrderCommand");
        final String id = req.getParameter(ID);
        final String numberOfPage = req.getParameter(PAGE_NUMBER);
        Page<Order> page;
        try {
            validator.validateInputData(id);
            orderService.deleteOrder(Integer.parseInt(id));
            if (Objects.isNull(numberOfPage) || numberOfPage.isEmpty()) {
                page = orderService.getPageOfOrders(DEFAULT_PAGE_NUMBER);
            } else {
                page = orderService.getPageOfOrders(numberOfPage);
            }
            req.setAttribute(ORDERS, page.getElements());
            req.setAttribute(NUMBERS, page.getCountOfPages());
            req.setAttribute(PAGE_NUMBER, numberOfPage);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_ADMIN + ADMIN_ALL_ORDERS_PAGE;
    }
}
