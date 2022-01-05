package controller.command.impl;

import controller.command.Command;
import controller.exception.ControllerException;
import dao.entity.AbstractCar;
import dao.entity.Page;
import org.apache.log4j.Logger;
import service.CarService;
import service.ServiceFactory;
import service.cssEditor.CssEditor;
import service.exception.ServiceException;
import service.util.Validator;
import service.util.impl.ValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.ControllerStringsStorage.*;

public class SelectCarTypeCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(SelectCarTypeCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();
    private final Validator validator = ValidatorImpl.getINSTANCE();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to SelectCarTypeCommand");
        final String carType = req.getParameter(CAR_BUTTON);
        try {
            validator.validateInputData(carType);
            CssEditor.pressedButton(carType, req);
            Page<AbstractCar> page = carService.getPageOfCarsAccordingToType(DEFAULT_PAGE_NUMBER, carType);
            req.setAttribute(CARS, page.getElements());
            req.setAttribute(NUMBERS, page.getCountOfPages());
            req.setAttribute(INDICATOR, "true");
            req.setAttribute(CHOICE, carType);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        req.setAttribute(FLAG, "true");
        return JSP_USER + CARS_PAGE;
    }
}
