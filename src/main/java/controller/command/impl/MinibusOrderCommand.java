package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import dao.entity.car.Minibus;
import org.apache.log4j.Logger;
import service.CarService;
import service.ServiceFactory;
import service.exception.ServiceException;
import service.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Objects;

import static controller.ControllerStringsStorage.*;

public class MinibusOrderCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(MinibusOrderCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to MinibusOrderCommand");
        String returnPageName = JSP_USER + FORM_OF_ORDER_PAGE;
        final String imagePath = req.getParameter(PICTURE);
        final String id = req.getParameter(ID);
        Validator.validateInputData(imagePath, id);
        Minibus minibus;
        try {
            minibus = carService.getMinibusById(Integer.parseInt(id));
            if (Objects.isNull(minibus)) {
                returnPageName = JSP_ERRORS;
                LOGGER.error("Minibus is null");
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        req.setAttribute(MARK, minibus.getNameOfMark());
        req.setAttribute(PRICE, minibus.getPrice());
        req.setAttribute(PICTURE, imagePath);
        return returnPageName;
    }
}