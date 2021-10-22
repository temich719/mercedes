package controller.command.impl;

import controller.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TruckServiceOrderCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        final String mark = req.getParameter("truckMark");
        req.setAttribute("select", mark);
        return "serviceOrder";
    }
}