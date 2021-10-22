package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;
import dao.entity.car.Car;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ViewCarCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String nameOfMark = req.getParameter("nameOfMark");
        Car car = null;
        for (Car i: new DataBaseImpl().getCars()) {
            if (i.getNameOfMark().equals(nameOfMark)){
                car = i;
                break;
            }
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