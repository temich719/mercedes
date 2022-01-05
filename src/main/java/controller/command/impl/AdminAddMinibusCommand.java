package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import dao.entity.car.Minibus;
import org.apache.log4j.Logger;
import service.CarService;
import service.ServiceFactory;
import service.exception.ServiceException;
import service.util.Validator;
import service.util.impl.ValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class AdminAddMinibusCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(AdminAddMinibusCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to AdminAddMinibusCommand");
        String page = JSP_ADMIN + ADMIN_ADD_PRODUCT_PAGE;
        final String mark = req.getParameter(MARK);
        final String price = req.getParameter(PRICE);
        final String imagePath = req.getParameter(IMAGE_PATH);
        final String load = req.getParameter(LOAD);
        final String weight = req.getParameter(WEIGHT);
        try {
            boolean dataIsValid = validateData(req, mark, price, imagePath, load, weight);
            if (dataIsValid) {
                Minibus minibus = new Minibus(mark, price, imagePath, load, weight);
                carService.addMinibus(minibus);
                page = JSP_ADMIN + ADMIN_PAGE;
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return page;
    }

    private boolean validateData(HttpServletRequest req, String mark, String price, String imagePath, String load, String weight) throws ServiceException {
        boolean isCorrectData = true;
        validator.validateInputData(mark, price, imagePath, load, weight);
        if (!validator.validatePrice(price.replaceAll("\\s", ""))) {
            req.setAttribute(ERROR, INVALID_PRICE);
            isCorrectData = false;
        } else if (!validator.validateImagePath(imagePath)) {
            req.setAttribute(ERROR, WRONG_IMAGE_PATH_FORMAT);
            isCorrectData = false;
        } else if (!validator.isNonNegativeDigit(load, weight)) {
            req.setAttribute(ERROR, NEGATIVE_NUMBER_OR_NOT_NUMBER);
            isCorrectData = false;
        }
        return isCorrectData;
    }
}
