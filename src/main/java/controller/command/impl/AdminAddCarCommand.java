package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import dao.entity.car.Car;
import org.apache.log4j.Logger;
import service.CarService;
import service.ServiceFactory;
import service.exception.ServiceException;
import service.util.Validator;
import service.util.impl.ValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class AdminAddCarCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(AdminAddCarCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to AdminAddCarCommand");
        String page = JSP_ADMIN + ADMIN_ADD_PRODUCT_PAGE;
        final String mark = req.getParameter(MARK);
        final String price = req.getParameter(PRICE);
        final String power = req.getParameter(POWER);
        final String acceleration = req.getParameter(ACCELERATION);
        final String consumption = req.getParameter(CONSUMPTION);
        final String engineVolume = req.getParameter(ENGINE_VOLUME);
        final String tankVolume = req.getParameter(TANK_VOLUME);
        final String trunkVolume = req.getParameter(TRUNK_VOLUME);
        final String maxSpeed = req.getParameter(MAX_SPEED);
        final String imagePath = req.getParameter(IMAGE_PATH);
        final String type = req.getParameter(TYPE);
        try {
            boolean dataIsValid = validateData(req, mark, price, power, acceleration, consumption, engineVolume, tankVolume, trunkVolume, maxSpeed, imagePath, type);
            if (dataIsValid) {
                Car car = new Car(mark, price, power, acceleration, consumption, engineVolume, tankVolume, trunkVolume, maxSpeed, imagePath, type);
                carService.addCar(car);
                page = JSP_ADMIN + ADMIN_PAGE;
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return page;
    }

    private boolean validateData(HttpServletRequest req, String mark, String price, String power, String acceleration, String consumption, String engineVolume, String tankVolume, String trunkVolume, String maxSpeed, String imagePath, String type) throws ServiceException {
        boolean isCorrectData = true;
        validator.validateInputData(mark, price, power, acceleration, consumption, engineVolume, tankVolume, trunkVolume, maxSpeed, imagePath, type);
        if (!validator.validatePrice(price.replaceAll("\\s", ""))) {
            req.setAttribute(ERROR, INVALID_PRICE);
            isCorrectData = false;
        } else if (!validator.validateImagePath(imagePath)) {
            req.setAttribute(ERROR, WRONG_IMAGE_PATH_FORMAT);
            isCorrectData = false;
        } else if (!validator.isNonNegativeDigit(power, acceleration, consumption, engineVolume, tankVolume, trunkVolume, maxSpeed)) {
            req.setAttribute(ERROR, NEGATIVE_NUMBER_OR_NOT_NUMBER);
            isCorrectData = false;
        } else if (!validator.isPermissibleCarType(type)) {
            req.setAttribute(ERROR, IS_NOT_ALLOWED_CAR_TYPE_TO_BE_ADDED);
            isCorrectData = false;
        }
        return isCorrectData;
    }
}
