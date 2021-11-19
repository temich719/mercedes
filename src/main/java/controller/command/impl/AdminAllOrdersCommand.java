package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.OrderService;
import service.ServiceFactory;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAllOrdersCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(AdminAllOrdersCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        logger.info("We got to AdminAllUsersCommand");
        try {
            req.setAttribute("orders", orderService.getListOfOrders());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return "adminAllOrders";
    }
}
