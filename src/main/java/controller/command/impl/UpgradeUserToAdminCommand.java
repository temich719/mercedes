package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;
import service.util.Validator;
import service.util.impl.ValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class UpgradeUserToAdminCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(UpgradeUserToAdminCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to UpgradeUserToAdminCommand");
        final String id = req.getParameter(ID);
        try {
            validator.validateInputData(id);
            userService.upgradeUserToAdmin(Integer.parseInt(id));
            req.setAttribute(USERS, userService.getListOfUsers());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_ADMIN + ADMIN_ALL_USERS_PAGE;
    }
}
