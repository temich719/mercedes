package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.OrderService;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;
import service.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class MarkAsReadCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(MarkAsReadCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to MarkAsReadCommand");
        final String email = req.getSession().getAttribute(EMAIL_ACCOUNT).toString();
        final String id = req.getParameter(ID);
        Validator.validateInputData(email, id);
        try {
            orderService.markAdRead(Integer.parseInt(id));
            req.setAttribute(ORDER, orderService.getListOfOrders());
            req.setAttribute(AVATAR_IMAGE, IMG + userService.getAvatarPathByEmail(email));
            req.getSession().setAttribute(COUNT, orderService.getCountOfUnreadOrders(email));
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_USER + ACCOUNT_PAGE;
    }
}
