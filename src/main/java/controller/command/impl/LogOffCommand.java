package controller.command.impl;

import controller.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOffCommand implements ICommand {

    private final static Logger logger = Logger.getLogger(LogOffCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("We got to LogOffCommand");
        req.getSession().setAttribute("nameAccount",null);
        req.getSession().setAttribute("emailAccount", null);
        req.getSession().setAttribute("accountName", null);
        req.getSession().setAttribute("accountSurname", null);
        return "index";
    }
}

