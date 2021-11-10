package controller.command.impl;

import controller.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class BackFromAllCarsCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(BackFromAllCarsCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("We got to BackFromAllCarsCommand");
        if (Objects.isNull(req.getSession().getAttribute("nameAccount")))return "index";
        else return "registratedIndex";
    }
}
