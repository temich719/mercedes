package controller.command.impl;

import controller.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class FirstStepCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String name = req.getParameter("name");
        final String surname = req.getParameter("surname");
        req.getSession().setAttribute("name", name);
        req.getSession().setAttribute("surname", surname);
        return "registration";
    }
}
