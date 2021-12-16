package controller.command.impl;

import controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static controller.ControllerStringsStorage.*;

public class TruckOrderCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(TruckOrderCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("We got to TruckOrderCommand");
        req.setAttribute(MARK, TRUCK_NAME);
        req.setAttribute(PICTURE, IMG + TRUCK_IMAGE);
        req.setAttribute(PRICE, TRUCK_PRICE);
        if (Objects.isNull(req.getSession().getAttribute(NAME_ACCOUNT))) {
            req.setAttribute(NAME_ACCOUNT, "");
            req.setAttribute(SURNAME_ACCOUNT, "");
            req.setAttribute(EMAIL_ACCOUNT, "");
        } else {
            final String nameSurname = req.getSession().getAttribute(NAME_ACCOUNT).toString();
            String[] strings = nameSurname.split(" ");
            req.setAttribute(NAME_ACCOUNT, strings[0]);
            req.setAttribute(SURNAME_ACCOUNT, strings[1]);
            req.setAttribute(EMAIL_ACCOUNT, req.getSession().getAttribute(EMAIL_ACCOUNT));
        }
        return JSP_USER + FORM_OF_ORDER_PAGE;
    }
}