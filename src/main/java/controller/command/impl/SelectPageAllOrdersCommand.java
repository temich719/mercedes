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

import static controller.ControllerStringsStorage.*;

public class SelectPageAllOrdersCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(SelectPageAllOrdersCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to SelectPageCommand");
        final String numberOfPage = req.getParameter(NUMBER_OF_PAGE);
        try {
            validator.validateInputData(numberOfPage);
            Page<Order> page = orderService.getPageOfOrders(numberOfPage);
            req.setAttribute(ORDERS, page.getElements());
            req.setAttribute(NUMBERS, page.getCountOfPages());
            req.setAttribute(PAGE_NUMBER, numberOfPage);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_ADMIN + ADMIN_ALL_ORDERS_PAGE;
    }
}
