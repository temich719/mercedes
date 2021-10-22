package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;
import dao.entity.car.Car;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DefiniteTestDriveCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String img = req.getParameter("markToList");
        String mark = null;
        for (Car i: DataBaseImpl.getCars()) {
            if (i.getImagePath().equals(img)){
                mark = i.getNameOfMark();
                break;
            }
        }
        req.setAttribute("sel", mark);
        req.setAttribute("select", img);
        return "testDriveOrder";
    }
}
