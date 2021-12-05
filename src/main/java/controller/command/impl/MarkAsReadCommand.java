package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.OrderService;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class MarkAsReadCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(MarkAsReadCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to MarkAsReadCommand");
        final String service = req.getParameter(SERVICE);
        final String name = req.getSession().getAttribute(ACCOUNT_NAME).toString();
        final String surname = req.getSession().getAttribute(ACCOUNT_SURNAME).toString();
        final String email = req.getSession().getAttribute(EMAIL_ACCOUNT).toString();
        final String mark = req.getParameter(MARK);
        final String date = req.getParameter(DATE);
        try {
            orderService.markAsRead(name, surname, email, service, mark, date);
            req.setAttribute(ORDER, orderService.getListOfOrders());
            req.setAttribute(AVATAR_IMAGE, IMG + userService.getAvatarPathByEmail(email));
            req.getSession().setAttribute(COUNT, orderService.getCountOfUnreadOrders(email));
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_USER + ACCOUNT_PAGE;
    }
}
