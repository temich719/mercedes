package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import dao.entity.UserDTO;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;
import service.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class AdminDeleteUserCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(AdminDeleteUserCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to AdminDeleteUserCommand");
        final String name = req.getParameter(NAME);
        final String surname = req.getParameter(SURNAME);
        final String email = req.getParameter(EMAIL);
        final String accessType = req.getParameter(ACCESS_TYPE);
        Validator.validateInputData(name, surname, email, accessType);
        if (email.equals(req.getSession().getAttribute(EMAIL_ACCOUNT))){
            throw new ControllerException("Attempt to delete yourself");
        }
        final UserDTO userDTO = new UserDTO(name, surname, email, accessType);
        try {
            userService.deleteUser(userDTO);
            req.setAttribute(USERS, userService.getListOfUsers());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_ADMIN + ADMIN_ALL_USERS_PAGE;
    }
}
