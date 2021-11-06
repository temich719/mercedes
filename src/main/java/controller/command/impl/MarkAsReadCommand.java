package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import service.OrderService;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MarkAsReadCommand implements ICommand {

    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        final String service = req.getParameter("service");
        final String name = req.getSession().getAttribute("accountName").toString();
        final String surname = req.getSession().getAttribute("accountSurname").toString();
        final String email = req.getSession().getAttribute("emailAccount").toString();
        final String mark = req.getParameter("mark");
        try {
            orderService.markAsRead(name, surname, email, service, mark);
            req.setAttribute("avatarImage", "img/" + userService.getAvatarPathByEmail(email));
            req.setAttribute("count", orderService.getCountOfUnreadOrders(email));
        }
        catch (ServiceException e){
            throw new ControllerException(e);
        }
        return "account";
    }
}