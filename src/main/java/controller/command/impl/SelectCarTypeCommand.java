package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;
import service.cssEditor.CssEditor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class SelectCarTypeCommand implements ICommand {//во фронте кнопка это надпись а не весь кусок-проблема надо фиксить

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String carType = req.getParameter("carButton");
        CssEditor.pressedButton(carType, req);
        req.setAttribute("cars", new DataBaseImpl().getCarListByType(carType));
        req.setAttribute("flag", "true");
        return "cars";
    }
}
