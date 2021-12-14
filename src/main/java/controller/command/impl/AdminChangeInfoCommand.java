package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.car.Car;
import org.apache.log4j.Logger;
import service.CarService;
import service.ServiceFactory;
import service.exception.ServiceException;
import service.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class AdminChangeInfoCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AdminChangeInfoCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to AdminChangeInfoCommand");
        String returnPageName = JSP_ADMIN + ADMIN_PAGE;
        try {
            final String oldImagePath = req.getParameter(OLD_IMAGE_PATH);
            final String imagePath = req.getParameter(IMAGE_PATH);
            final String mark = req.getParameter(MARK);
            final String price = req.getParameter(PRICE);
            final String power = req.getParameter(POWER);
            final String acceleration = req.getParameter(ACCELERATION);
            final String consumption = req.getParameter(CONSUMPTION);
            final String engine = req.getParameter(ENGINE_VOLUME);
            final String tank = req.getParameter(TANK_VOLUME);
            final String trunk = req.getParameter(TRUNK_VOLUME);
            final String maxSpeed = req.getParameter(MAX_SPEED);
            final String type = req.getParameter(TYPE);
            Validator.validateInputData(oldImagePath, imagePath, mark, price, power, acceleration, consumption, engine, tank, trunk, maxSpeed, type);
            Car car = new Car(mark, makePricePrettier(price), power, acceleration, consumption, engine, tank, trunk, maxSpeed, imagePath, type);
            if (!carService.updateCarInfo(car)) {
                req.setAttribute(MARK, mark);
                req.setAttribute(PRICE, price);
                req.setAttribute(POWER, power);
                req.setAttribute(ACCELERATION, acceleration);
                req.setAttribute(CONSUMPTION, consumption);
                req.setAttribute(ENGINE_VOLUME, engine);
                req.setAttribute(TANK_VOLUME, tank);
                req.setAttribute(TRUNK_VOLUME, trunk);
                req.setAttribute(MAX_SPEED, maxSpeed);
                req.setAttribute(TYPE, type);
                req.setAttribute(IMAGE_PATH, imagePath);
                req.setAttribute(OLD_IMAGE_PATH, oldImagePath);
                req.setAttribute(ERROR, WRONG_CAR_TYPE);
                returnPageName = JSP_ADMIN + ADMIN_CAR_DESCRIPTION_PAGE;
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return returnPageName;
    }

    private String makePricePrettier(String price) {
        price = price.replaceAll("\\s", "");
        if (price.charAt(price.length() - 1) != '$') price = price.concat("$");
        int j = price.length() - 2;
        int k = 0;
        int d = 3;
        for (int i = j; i >= 0; i--) {
            k++;
            if (k % d == 0 && i - 1 >= 0) {
                price = price.substring(0, i) + " " + price.substring(i, j + 2);
                i++;
                j++;
                d++;
                k = 0;
            }
        }
        return price;
    }

}
