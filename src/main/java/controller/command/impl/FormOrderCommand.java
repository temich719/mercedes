package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;
import dao.entity.car.Car;
import dao.entity.car.Minibus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Objects;

public class FormOrderCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String imagePath = req.getParameter("img");
        /*Car car = null;
        for (Car i:DataBaseImpl.getCars()) {
            if (i.getImagePath().equals(imagePath)){
                car = i;
                break;
            }
        }*/

        //дубликация с MakeOrderCommand
        String mark = null;
        String price = null;
        String money = null;
        Car car = null;
        for (Car i : new DataBaseImpl().getCars()) {
            if (i.getImagePath().equals(imagePath)) {
                car = i;
                break;
            }
        }
        if (Objects.nonNull(car)){
            mark = car.getNameOfMark();
            price = car.getPrice();
        }
        Minibus minibus = null;
        if (Objects.isNull(car)){
            for (Minibus i:new DataBaseImpl().getMinibuses()) {
                if (i.getImagePath().equals(imagePath)){
                    minibus = i;
                    break;
                }
            }
        }
        if (Objects.nonNull(minibus)){
            mark = minibus.getNameOfMark();
            money = minibus.getPrice();
        }
        else if (Objects.isNull(car)){
            mark = "Actros";
            money = "73 000$";
        }




        req.setAttribute("img", imagePath);
        req.setAttribute("mark", mark);
        if (Objects.isNull(price))req.setAttribute("money", money);
        else req.setAttribute("price", price);
        req.setAttribute("carName", mark);
        //req.setAttribute("money", car.getPrice());
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
