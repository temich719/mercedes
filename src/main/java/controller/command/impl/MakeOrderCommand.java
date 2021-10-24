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

public class MakeOrderCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String name = req.getParameter("name");
        final String surname = req.getParameter("surname");
        final String email = req.getParameter("email");
        final String phone = req.getParameter("phone");
        final String imagePath = req.getParameter("img");
        //нужно вытащить в переменные для понятности mark price и money
        String[] markAndPrice = new DataBaseImpl().getMarkAndPriceByImage(imagePath);
        if (name.equals("") || surname.equals("") || email.equals("") || phone.equals("")){
            req.setAttribute("error", "Заполните все обязательные поля!");
            req.setAttribute("img", imagePath);
            if (Objects.isNull(markAndPrice[1]))req.setAttribute("money", markAndPrice[2]);
            else req.setAttribute("price", markAndPrice[1]);
            req.setAttribute("mark", markAndPrice[0]);
            return "formOfOrder";
        }
        new DataBaseImpl().addOrder(name, surname, email, "buying_a_car", markAndPrice[0], markAndPrice[1], phone,null);
        try {
            Mail.sendOrder(email, markAndPrice[0], markAndPrice[1]);
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (MessagingException e) {
            System.out.println("MessageException");
        }
        req.setAttribute("email", email);
        return "thanks";
    }
}
