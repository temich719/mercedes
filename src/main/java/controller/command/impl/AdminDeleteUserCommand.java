package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.User;
import org.apache.log4j.Logger;
import service.OrderService;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class AdminDeleteUserCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AdminDeleteUserCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to AdminDeleteUserCommand");
        final String name = req.getParameter(NAME);
        final String surname = req.getParameter(SURNAME);
        final String email = req.getParameter(EMAIL);
        final String accessType = req.getParameter(ACCESS_TYPE);
        final User user = new User(name, surname, accessType, email, "");
        try {
            userService.deleteUser(user);
            orderService.deleteOrdersOfDeletedUser(user);
            req.setAttribute(USERS, userService.getListOfUsers());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_ADMIN + ADMIN_ALL_USERS_PAGE;
    }
}
