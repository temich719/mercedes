package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class AdminGoToAddCarPageCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(AdminGoToAddCarPageCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to AdminGoToCarPageCommand");
        return JSP_ADMIN + ADMIN_ADD_PRODUCT_PAGE;
    }
}
