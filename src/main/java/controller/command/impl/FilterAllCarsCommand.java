package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;
import service.cssEditor.CssEditor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilterAllCarsCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String carType = req.getParameter("type");
        CssEditor.pressedButton(carType, req);
        if (carType.equals("car")){
            ArrayList<Car> cars = DataBaseImpl.getCars();
            req.setAttribute("filtered", cars);
        }
        else if(carType.equals("minibus")){
            ArrayList<Minibus> minibuses = DataBaseImpl.getMinibuses();
            req.setAttribute("filtered", minibuses);
        }
        else {
            ArrayList<Truck> trucks = DataBaseImpl.getTrucks();
            req.setAttribute("filtered", trucks);
        }
        req.setAttribute("flag", "true");
        return "allCars";
    }
}
