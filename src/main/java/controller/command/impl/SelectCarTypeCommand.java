package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;
import dao.entity.car.Car;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectCarTypeCommand implements ICommand {//во фронте кнопка это надпись а не весь кусок-проблема надо фиксить

    private void pressedButton(String carType, HttpServletRequest req){
        req.setAttribute(carType+"Form", "background-color: grey;\n" +
                "  border-top: 4px solid blue;");
        req.setAttribute(carType+"Button", "background-color: grey;");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String carType = req.getParameter("carButton");
        pressedButton(carType, req);
        ArrayList<Car> cars = new ArrayList<>();
        for (Car car: new DataBaseImpl().getCars()) {
            if (car.getType().equals(carType))cars.add(car);
        }
        req.setAttribute("cars", cars);
        req.setAttribute("flag", "true");
        return "cars";
    }
}
