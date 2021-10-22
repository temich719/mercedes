package controller.command.impl;

import controller.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class BackFromAllCarsCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (Objects.isNull(req.getSession().getAttribute("nameAccount")))return "index";
        else return "registratedIndex";
    }
}
