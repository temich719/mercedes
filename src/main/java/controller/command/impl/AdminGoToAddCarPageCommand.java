package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminGoToAddCarPageCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(AdminGoToAddCarPageCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        logger.info("We got to AdminGoToCarPageCommand");
        return "adminAddProduct";
    }
}
