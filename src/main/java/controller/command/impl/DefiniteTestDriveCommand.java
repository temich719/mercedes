package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;
import service.CarService;
import service.ServiceFactory;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefiniteTestDriveCommand implements ICommand {

    private final static Logger logger = Logger.getLogger(DefiniteTestDriveCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        logger.info("We got to DefiniteTestDriveCommand");
        final String img = req.getParameter("markToList");
        try {
            req.setAttribute("sel", carService.getCarMarkByImage(img));
            req.setAttribute("defImage", img);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        req.setAttribute("select", img);
        return "testDriveOrder";
    }
}
