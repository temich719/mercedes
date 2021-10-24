package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;
import service.email.Mail;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class MakeServiceOrderCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String userName = req.getParameter("name");
        final String userSurname = req.getParameter("surname");
        final String email = req.getParameter("email");
        final String phone = req.getParameter("phone");
        final String date = req.getParameter("date");
        final String select = req.getParameter("select");
        if (userName.equals("") || userSurname.equals("") || email.equals("") || phone.equals("")
                || date.equals("")){
            req.setAttribute("error", "Заполните все обязательные поля!");
            req.setAttribute("select", select);
            return "serviceOrder";
        }
        final String mark;
        if (Objects.isNull(req.getParameter("selectName")))mark = req.getParameter("mark");
        else mark = req.getParameter("selectName");
        new DataBaseImpl().addOrder(userName, userSurname, email, "service", mark, "После осмотра", phone, date);
        try {
            Mail.sendServiceOrder(email, mark, date, "После осмотра");
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (MessagingException e) {
            System.out.println("MessagingException");
        }
        req.setAttribute("email", email);
        return "thanks";
    }
}
