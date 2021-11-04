package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Objects;

public class FormOrderCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String imagePath = req.getParameter("img");
        req.setAttribute("img", imagePath);
        //вытащить в переменные как в MakeOrderCommand
        //make in service
        String[] markAndPrice = new DataBaseImpl().getMarkAndPriceByImage(imagePath);
        req.setAttribute("mark", markAndPrice[0]);
        if (Objects.isNull(markAndPrice[1]))req.setAttribute("money", markAndPrice[2]);
        else req.setAttribute("price", markAndPrice[1]);
        req.setAttribute("carName", markAndPrice[0]);

        if (Objects.isNull(req.getSession().getAttribute("nameAccount"))){
            req.setAttribute("nameAccount","");
            req.setAttribute("surnameAccount","");
            req.setAttribute("emailAccount","");
        }
        else {
            final String nameSurname = req.getSession().getAttribute("nameAccount").toString();
            String[] strings = nameSurname.split(" ");
            req.setAttribute("nameAccount", strings[0]);
            req.setAttribute("surnameAccount", strings[1]);
            req.setAttribute("emailAccount", req.getSession().getAttribute("emailAccount"));
        }
        return "formOfOrder";
    }
}
