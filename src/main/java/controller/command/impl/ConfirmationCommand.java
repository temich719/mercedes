package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ConfirmationCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
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
        //make in service
        new DataBaseImpl().insertUser(email, password, name, surname);
        req.getSession().setAttribute("nameAccount",name + " " + surname);
        req.getSession().setAttribute("emailAccount", email);
        return "registratedIndex";
    }
}
