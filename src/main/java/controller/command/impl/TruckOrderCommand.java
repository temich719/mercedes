package controller.command.impl;

import controller.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class TruckOrderCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("mark","Actros");
        req.setAttribute("img","img/truck.jpg");
        req.setAttribute("money", "73 000$");
        req.setAttribute("price","");
        if (Objects.isNull(req.getSession().getAttribute("nameAccount"))){//дубликация кода с formOrderCommand
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