package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.OrderService;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;
import service.util.Validator;
import service.util.impl.ValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Objects;

import static controller.ControllerStringsStorage.*;

public class MarkAsReadCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(MarkAsReadCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final UserService userService = serviceFactory.getUserService();
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to MarkAsReadCommand");
        final String email = req.getSession().getAttribute(EMAIL_ACCOUNT).toString();
        final String id = req.getParameter(ID);
        try {
            validator.validateInputData(email, id);
            orderService.markAdRead(Integer.parseInt(id));
            req.setAttribute(ORDER, orderService.getListOfOrders());
            String avatarImage = userService.getAvatarPathByEmail(email);
            if (Objects.isNull(avatarImage)) {
                avatarImage = DEFAULT_AVATAR_IMAGE;
            } else {
                avatarImage = IMG + avatarImage;
            }
            req.setAttribute(AVATAR_IMAGE, avatarImage);
            req.getSession().setAttribute(COUNT, orderService.getCountOfUnreadOrders(email));
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_USER + ACCOUNT_PAGE;
    }
}
