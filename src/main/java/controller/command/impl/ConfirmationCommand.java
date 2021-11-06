package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.User;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmationCommand implements ICommand {

    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        final String code = req.getParameter("code");
        final String userInput = req.getParameter("confirmation");
        if (!userInput.equals(code)){
            req.setAttribute("script", "true");
            return "registration";
        }
        final String email = req.getParameter("email");
        final String password = req.getParameter("password");
        final String name = req.getParameter("name");
        final String surname = req.getParameter("surname");
        try {
            userService.registration(new User(name, surname, "registered", email, password));
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        req.getSession().setAttribute("nameAccount",name + " " + surname);
        req.getSession().setAttribute("emailAccount", email);
        return "registratedIndex";
    }
}
