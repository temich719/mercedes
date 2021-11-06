package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import service.CarService;
import service.ServiceFactory;
import service.cssEditor.CssEditor;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class FilterAllCarsCommand implements ICommand {

    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        final String carType = req.getParameter("type");
        CssEditor.pressedButton(carType, req);
        try {
            if (carType.equals("car")) {
                ArrayList<Car> cars = carService.getCars();
                req.setAttribute("filtered", cars);
            } else if (carType.equals("minibus")) {
                ArrayList<Minibus> minibuses = carService.getMinibuses();
                req.setAttribute("filtered", minibuses);
            } else {
                ArrayList<Truck> trucks = carService.getTrucks();
                req.setAttribute("filtered", trucks);
            }
        }
        catch (ServiceException e){
            throw new ControllerException(e);
        }
        req.setAttribute("flag", "true");
        return "allCars";
    }
}
