package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.CarService;
import service.ServiceFactory;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static controller.ControllerStringsStorage.*;

public class FormOrderCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(FormOrderCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to FormOrderCommand");
        final String imagePath = req.getParameter(PICTURE);
        req.setAttribute(PICTURE, imagePath);
        try {
            String[] markAndPrice = carService.getMarkAndPriceByImage(imagePath);
            req.setAttribute(MARK, markAndPrice[0]);
            if (Objects.isNull(markAndPrice[1])) {
                req.setAttribute(MONEY, markAndPrice[2]);
            } else {
                req.setAttribute(PRICE, markAndPrice[1]);
            }
            req.setAttribute(CAR_NAME, markAndPrice[0]);

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
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_USER + FORM_OF_ORDER_PAGE;
    }
}
