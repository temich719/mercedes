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

public class AdminDeleteOrderCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(AdminDeleteOrderCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        logger.info("We got to AdminDeleteOrderCommand");
        final String name = req.getParameter("name");
        final String surname = req.getParameter("surname");
        final String service = req.getParameter("service");
        final String mark = req.getParameter("mark");
        final String price = req.getParameter("price");
        final String date = req.getParameter("date");
        final String phone = req.getParameter("phone");
        final String email = req.getParameter("email");
        final Order order = new Order(name, surname, email, service, mark, price, phone, date, "");
        try {
            orderService.deleteOrder(order);
            req.setAttribute("orders", orderService.getListOfOrders());
        }
        catch (ServiceException e){
            throw new ControllerException(e);
        }
        return "adminAllOrders";
    }
}
