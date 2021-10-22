package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;
import dao.entity.Pair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnterCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        Pair pair = new Pair(req.getParameter("email"), req.getParameter("password"));
        ArrayList<Pair> arrayList = new DataBaseImpl().selectDataForEnter();
        for (Pair value : arrayList) {
            if (value.getFirst().equals(pair.getFirst()) && value.getSecond().equals(pair.getSecond())){
                Pair names = new DataBaseImpl().getName(pair.getFirst());
                HttpSession session = req.getSession(true);
                session.setAttribute("nameAccount", names.getFirst() + " " +names.getSecond());
                session.setAttribute("accountName", names.getFirst());
                session.setAttribute("accountSurname", names.getSecond());
                session.setAttribute("emailAccount", pair.getFirst());
                return "registratedIndex";
            }
        }
        req.setAttribute("error", "Неверный адресс электронной почты или пароль.");
        return "enter";
    }
}
