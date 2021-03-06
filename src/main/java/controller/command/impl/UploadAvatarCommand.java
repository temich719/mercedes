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

import static controller.ControllerStringsStorage.*;

public class UploadAvatarCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(UploadAvatarCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to UploadAvatarCommand");
        final String avatarPath = req.getParameter(AVA);
        final String email = req.getSession().getAttribute(EMAIL_ACCOUNT).toString();
        try {
            validator.validateInputData(avatarPath, email);
            userService.addAvatar(avatarPath, email);
            req.setAttribute(ORDER, orderService.getListOfOrders());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        req.setAttribute(AVATAR_IMAGE, IMG + avatarPath);
        return JSP_USER + ACCOUNT_PAGE;
    }
}
