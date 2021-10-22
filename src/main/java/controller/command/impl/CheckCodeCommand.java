package controller.command.impl;

import controller.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckCodeCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        final String code = req.getParameter("codeOfConfirm");
        final String input = req.getParameter("confirmation");
        if (!code.equals(input)){
            req.setAttribute("script", "true");
            return "enter";
        }
        return "forgetPassword";
    }
}