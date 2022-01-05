package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import dao.entity.Page;
import dao.entity.UserDTO;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;
import service.util.Validator;
import service.util.impl.ValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class SelectPageAllUsersCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SelectPageAllUsersCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to SelectPageAllUsersCommand");
        final String numberOfPage = req.getParameter(NUMBER_OF_PAGE);
        try {
            validator.validateInputData(numberOfPage);
            Page<UserDTO> page = userService.getPageOfUsers(numberOfPage);
            req.setAttribute(USERS, page.getElements());
            req.setAttribute(NUMBERS, page.getCountOfPages());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_ADMIN + ADMIN_ALL_USERS_PAGE;
    }
}
