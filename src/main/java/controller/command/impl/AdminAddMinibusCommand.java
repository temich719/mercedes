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

import static controller.ControllerStringsStorage.*;

public class AdminAddMinibusCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(AdminAddMinibusCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to AdminAddMinibusCommand");
        final String mark = req.getParameter(MARK);
        final String price = req.getParameter(PRICE);
        final String imagePath = req.getParameter(IMAGE_PATH);
        final String load = req.getParameter(LOAD);
        final String weight = req.getParameter(WEIGHT);
        try {
            Validator.validateInputData(mark, price, imagePath, load, weight);
            Minibus minibus = new Minibus(mark, price, imagePath, load, weight);
            carService.addMinibus(minibus);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return JSP_ADMIN + ADMIN_PAGE;
    }
}
