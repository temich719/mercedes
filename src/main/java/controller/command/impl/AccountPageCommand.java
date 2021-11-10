package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class AccountPageCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(AccountPageCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        logger.info("We got to AccountPageCommand");
        try {
            final String avatarPath = userService.getAvatarPathByEmail(req.getSession().getAttribute("emailAccount").toString());
            if (Objects.isNull(avatarPath))req.setAttribute("avatarImage", "img/avatar.jpg");
            else req.setAttribute("avatarImage", "img/" + avatarPath);
            return "account";
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
}
