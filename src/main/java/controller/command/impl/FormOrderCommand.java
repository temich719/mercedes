package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.AbstractCar;
import org.apache.log4j.Logger;
import service.CarService;
import service.ServiceFactory;
import service.exception.ServiceException;
import service.util.Validator;

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
        Validator.validateInputData(imagePath);
        try {
            AbstractCar abstractCar = carService.getAnyCarByImage(imagePath);
            req.setAttribute(MARK, abstractCar.getNameOfMark());
            req.setAttribute(PRICE, abstractCar.getPrice());
            req.setAttribute(PICTURE, imagePath);
            if (Objects.nonNull(req.getSession().getAttribute(NAME_ACCOUNT))){
                req.setAttribute(NAME_ACCOUNT, req.getSession().getAttribute(ACCOUNT_NAME));
                req.setAttribute(SURNAME_ACCOUNT, req.getSession().getAttribute(ACCOUNT_SURNAME));
                req.setAttribute(EMAIL_ACCOUNT, req.getSession().getAttribute(EMAIL_ACCOUNT));
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_USER + FORM_OF_ORDER_PAGE;
    }
}
