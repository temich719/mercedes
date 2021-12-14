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
import java.util.Objects;

import static controller.ControllerStringsStorage.*;

public class AccountPageCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AccountPageCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to AccountPageCommand");
        try {
            final String avatarPath = userService.getAvatarPathByEmail(req.getSession().getAttribute(EMAIL_ACCOUNT).toString());
            if (Objects.isNull(avatarPath) || avatarPath.equals("")) {
                req.setAttribute(AVATAR_IMAGE, DEFAULT_AVATAR_IMAGE);
            } else {
                req.setAttribute(AVATAR_IMAGE, IMG + avatarPath);
            }
            req.setAttribute(ORDER, orderService.getListOfOrders());
            return JSP_USER + ACCOUNT_PAGE;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
}
