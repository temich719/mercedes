package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.car.Car;
import org.apache.log4j.Logger;
import service.CarService;
import service.ServiceFactory;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminChangeInfoCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(AdminChangeInfoCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        logger.info("We got to AdminChangeInfoCommand");
        try {
            final String oldImagePath = req.getParameter("img");
            final String imagePath = req.getParameter("image");
            final String mark = carService.getCarMarkByImage(oldImagePath);
            final String price = req.getParameter("price");
            final String power = req.getParameter("power");
            final String acceleration = req.getParameter("acceleration");
            final String consumption = req.getParameter("consumption");
            final String engine = req.getParameter("engine");
            final String tank = req.getParameter("tank");
            final String trunk = req.getParameter("trunk");
            final String maxSpeed = req.getParameter("maxSpeed");
            final String type = req.getParameter("type");
            if (!carService.isAllowedCarType(type)){
                req.setAttribute("mark", mark);
                req.setAttribute("price", price);
                req.setAttribute("power", power);
                req.setAttribute("acceleration", acceleration);
                req.setAttribute("consumption", consumption);
                req.setAttribute("engine", engine);
                req.setAttribute("tank", tank);
                req.setAttribute("trunk", trunk);
                req.setAttribute("maxSpeed", maxSpeed);
                req.setAttribute("type", type);
                req.setAttribute("image", imagePath);
                req.setAttribute("img", oldImagePath);
                req.setAttribute("error", "Неподдерживаемый тип машины!");
                return "adminCarDescription";
            }
            Car car = new Car(mark, makePricePrettier(price), power, acceleration, consumption, engine, tank, trunk, maxSpeed, imagePath, type);
            carService.updateCarInfo(car);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return "adminPage";
    }

    private String makePricePrettier(String price){
        price = price.replaceAll("\\s","");
        if (price.charAt(price.length() - 1) != '$')price = price.concat("$");
        int j = price.length() - 2;
        int k = 0;
        int d = 3;
        for (int i = j;i >= 0;i--){
            k++;
            if (k % d == 0 && i - 1 >= 0){
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
