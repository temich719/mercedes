package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;
import dao.entity.car.Minibus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class MinibusOrderCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String imagePath = req.getParameter("img");
        Minibus minibus = null;
        for (Minibus i: new DataBaseImpl().getMinibuses()) {
            if (i.getImagePath().equals(imagePath)){
                minibus = i;
                break;
            }
        }
        req.setAttribute("mark", minibus.getNameOfMark());
        req.setAttribute("money", minibus.getPrice());
        req.setAttribute("img",imagePath);
        return "formOfOrder";
    }
}