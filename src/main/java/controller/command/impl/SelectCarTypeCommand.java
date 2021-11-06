package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import service.CarService;
import service.ServiceFactory;
import service.cssEditor.CssEditor;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectCarTypeCommand implements ICommand {

    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        final String carType = req.getParameter("carButton");
        CssEditor.pressedButton(carType, req);
        try {
            req.setAttribute("cars", carService.getCarListByType(carType));
        }
        catch (ServiceException e){
            throw new ControllerException(e);
        }
        req.setAttribute("flag", "true");
        return "cars";
    }
}
