package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.car.Minibus;
import org.apache.log4j.Logger;
import service.CarService;
import service.ServiceFactory;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAddMinibusCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(AdminAddMinibusCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        logger.info("We got to AdminAddMinibusCommand");
        final String mark = req.getParameter("mark");
        final String price = req.getParameter("price");
        final String imagePath = req.getParameter("image");
        final String load = req.getParameter("load");
        final String weight = req.getParameter("weight");
        try {
            carService.addMinibus(new Minibus(mark, price, imagePath, load, weight));
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return "adminPage";
    }
}
