package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;
import dao.entity.car.Car;
import dao.entity.car.Minibus;
import service.email.Mail;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

//дубликация
public class MakeOrderCommand implements ICommand {//проверки и перенаправление в аккаунт где уже будет новый заказ
    @Override    //тут и в formOrderCommand вынести как метод поиск по картинке
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String name = req.getParameter("name");
        final String surname = req.getParameter("surname");
        final String email = req.getParameter("email");
        final String phone = req.getParameter("phone");
        final String imagePath = req.getParameter("img");
        /*if (Objects.isNull(name) || Objects.isNull(surname) || Objects.isNull(email)
        || Objects.isNull(phone)){
            req.setAttribute("error", "Заполните все обязательные поля!");
            return "formOfOrder";
        }*/

        String mark = null;
        String price = null;
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
            price = minibus.getPrice();
        }
        else if (Objects.isNull(car)){
            mark = "Actros";
            price = "73 000$";
        }

        if (name.equals("") || surname.equals("") || email.equals("") || phone.equals("")){
            req.setAttribute("error", "Заполните все обязательные поля!");
            req.setAttribute("img", imagePath);
            req.setAttribute("price", price);
            req.setAttribute("mark", mark);
            return "formOfOrder";
        }

        new DataBaseImpl().addOrder(name, surname, email, "buying_a_car", mark, price, phone,null);
        try {
            Mail.sendOrder(email, mark, price);
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (MessagingException e) {
            System.out.println("MessageException");
        }
        req.setAttribute("email", email);
        return "thanks";
    }
}
