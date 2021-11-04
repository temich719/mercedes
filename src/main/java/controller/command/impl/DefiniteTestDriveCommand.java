package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DefiniteTestDriveCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String img = req.getParameter("markToList");
        //make in service
        req.setAttribute("sel", new DataBaseImpl().getCarMarkByImage(img));
        req.setAttribute("select", img);
        return "testDriveOrder";
    }
}
