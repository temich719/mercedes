package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.car.Minibus;
import service.CarService;
import service.ServiceFactory;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MinibusOrderCommand implements ICommand {

    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        final String imagePath = req.getParameter("img");
        Minibus minibus = null;
        try {
            for (Minibus i : carService.getMinibuses()) {
                if (i.getImagePath().equals(imagePath)) {
                    minibus = i;
                    break;
                }
            }
        }
        catch (ServiceException e){
            throw new ControllerException(e);
        }
        req.setAttribute("mark", minibus.getNameOfMark());
        req.setAttribute("money", minibus.getPrice());
        req.setAttribute("img",imagePath);
        return "formOfOrder";
    }
}