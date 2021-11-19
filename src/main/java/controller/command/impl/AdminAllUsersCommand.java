package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAllUsersCommand implements ICommand {

    private final static Logger logger = Logger.getLogger(AdminAllUsersCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        logger.info("We got to AdminAllUsersCommand");
        try {
            req.setAttribute("users", userService.getListOfUsers());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return "adminAllUsers";
    }
}
