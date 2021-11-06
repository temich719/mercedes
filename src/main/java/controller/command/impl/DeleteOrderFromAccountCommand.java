package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.Order;
import service.OrderService;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteOrderFromAccountCommand implements ICommand {

    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        final String name = req.getSession().getAttribute("accountName").toString();
        final String surname = req.getSession().getAttribute("accountSurname").toString();
        final String email = req.getSession().getAttribute("emailAccount").toString();
        final String service = req.getParameter("service");
        final String mark = req.getParameter("mark");
        final String price = req.getParameter("price");
        final String date = req.getParameter("date");
        final String phone = req.getParameter("phone");
        try {
            orderService.deleteOrder(new Order(name, surname, email, service, mark, price, phone, date, "unread"));
            req.setAttribute("avatarImage", "img/" + userService.getAvatarPathByEmail(email));
            req.getSession().setAttribute("count", orderService.getCountOfUnreadOrders(email));
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return "account";
    }
}