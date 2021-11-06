package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.car.Car;
import service.CarService;
import service.ServiceFactory;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewCarCommand implements ICommand {

    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        final String nameOfMark = req.getParameter("nameOfMark");
        Car car = null;
        try {
            for (Car i : carService.getCars()) {
                if (i.getNameOfMark().equals(nameOfMark)) {
                    car = i;
                    break;
                }
            }
        }
        catch (ServiceException e){
            throw new ControllerException(e);
        }
        req.setAttribute("nameOfMark", nameOfMark);
        req.setAttribute("img", car.getImagePath());
        req.setAttribute("price", car.getPrice());
        req.setAttribute("power", car.getPower());
        req.setAttribute("acceleration", car.getAccelerationTillHundred());
        req.setAttribute("consumption", car.getConsumption());
        req.setAttribute("engine", car.getEngineVolume());
        req.setAttribute("tank", car.getTankVolume());
        req.setAttribute("trunk", car.getTrunkVolume());
        req.setAttribute("speed", car.getMaxSpeed());
        req.setAttribute("type", car.getType());
        return "carInfo";
    }
}