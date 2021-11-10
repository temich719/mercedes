package controller.command.impl;

import controller.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckCodeCommand implements ICommand {

    private final static Logger logger = Logger.getLogger(CheckCodeCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("We got to CheckCodeCommand");
        final String code = req.getParameter("codeOfConfirm");
        final String input = req.getParameter("confirmation");
        if (!code.equals(input)){
            req.setAttribute("script", "true");
            return "enter";
        }
        return "forgetPassword";
    }
}