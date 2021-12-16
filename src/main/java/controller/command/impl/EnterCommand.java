package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import dao.entity.UserDTO;
import org.apache.log4j.Logger;
import service.OrderService;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;
import service.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

import static controller.ControllerStringsStorage.*;

public class EnterCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(EnterCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to EnterCommand");
        final String email = req.getParameter(EMAIL);
        final String password = req.getParameter(PASSWORD);
        Validator.validateInputData(email, password);
        String returnPageName = JSP_USER + ENTER_PAGE;
        UserDTO userDTO;
        try {
            userDTO = userService.enter(email, password);
            if (Objects.nonNull(userDTO)) {
                HttpSession session = req.getSession(true);
                session.setAttribute(NAME_ACCOUNT, userDTO.getName() + " " + userDTO.getSurname());
                session.setAttribute(ACCOUNT_NAME, userDTO.getName());
                session.setAttribute(ACCOUNT_SURNAME, userDTO.getSurname());
                session.setAttribute(EMAIL_ACCOUNT, userDTO.getEmail());
                session.setAttribute(COUNT, orderService.getCountOfUnreadOrders(userDTO.getEmail()));
                if (userDTO.getAccessType().equals(ADMIN)) {
                    session.setAttribute(IS_ADMIN, "true");
                }
                returnPageName = JSP_USER + REGISTRATED_INDEX_PAGE;
            } else {
                req.setAttribute(ERROR, INVALID_EMAIL_OR_PASSWORD_MESSAGE);
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return returnPageName;
    }
}
