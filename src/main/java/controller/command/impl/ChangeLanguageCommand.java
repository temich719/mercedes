package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguageCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(ChangeLanguageCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        logger.info("We got to ChangeLanguageCommand");
        String locale = req.getParameter("locale");
        req.getSession().setAttribute("locale", locale);
        try {
            resp.sendRedirect("index.jsp");
        } catch (IOException e) {
            throw new ControllerException(e);
        }
        return null;
    }
}
