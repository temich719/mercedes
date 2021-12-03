package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class UploadAvatarCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(UploadAvatarCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to UploadAvatarCommand");
        final String avatarPath = req.getParameter(AVA);
        final String email = req.getSession().getAttribute(EMAIL_ACCOUNT).toString();
        try {
            userService.addAvatar(avatarPath, email);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        req.setAttribute(AVATAR_IMAGE, IMG + avatarPath);
        return JSP_USER + ACCOUNT_PAGE;
    }
}
