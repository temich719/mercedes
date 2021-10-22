package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import dao.entity.car.Truck;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilterAllCarsCommand implements ICommand {

    //дубликация с selectCarTypeCommand
    private void pressedButton(String carType, HttpServletRequest req){
        req.setAttribute(carType+"Form", "background-color: grey;\n" +
                "  border-top: 4px solid blue;");
        req.setAttribute(carType+"Button", "background-color: grey;");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String carType = req.getParameter("type");
        pressedButton(carType, req);
        if (carType.equals("car")){
            ArrayList<Car> cars = new DataBaseImpl().getCars();
            req.setAttribute("filtered", cars);
        }
        else if(carType.equals("minibus")){
            ArrayList<Minibus> minibuses = new DataBaseImpl().getMinibuses();
            req.setAttribute("filtered", minibuses);
        }
        else {
            ArrayList<Truck> trucks = new DataBaseImpl().getTrucks();
            req.setAttribute("filtered", trucks);
        }
        req.setAttribute("flag", "true");
        return "allCars";
    }
}
