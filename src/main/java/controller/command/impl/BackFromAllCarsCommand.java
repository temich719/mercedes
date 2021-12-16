package controller.command.impl;

import controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static controller.ControllerStringsStorage.*;

public class BackFromAllCarsCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(BackFromAllCarsCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("We got to BackFromAllCarsCommand");
        String page;
        if (Objects.isNull(req.getSession().getAttribute(NAME_ACCOUNT))) {
            page = INDEX_PAGE;
        } else {
            page = JSP_USER + REGISTRATED_INDEX_PAGE;
        }
        return page;
    }
}
