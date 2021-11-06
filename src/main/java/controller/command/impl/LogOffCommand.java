package controller.command.impl;

import controller.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOffCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("nameAccount",null);
        req.getSession().setAttribute("emailAccount", null);
        req.getSession().setAttribute("accountName", null);
        req.getSession().setAttribute("accountSurname", null);
        return "index";
    }
}

