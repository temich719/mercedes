package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;
import dao.entity.car.Car;
import service.email.Mail;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class MakeTestDriveOrderCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String userName = req.getParameter("name");
        final String userSurname = req.getParameter("surname");
        final String email = req.getParameter("email");
        final String phone = req.getParameter("phone");
        final String date = req.getParameter("date");
        if (userName.equals("") || userSurname.equals("") || email.equals("") || phone.equals("")
                || date.equals("")){
            req.setAttribute("error", "Заполните все обязательные поля!");
            return "testDriveOrder";
        }
        String mark = null;
        String image;
        if (Objects.isNull(req.getParameter("selectName"))){
            image = req.getParameter("mark");
            for (Car car: DataBaseImpl.getCars()) {
                if (car.getImagePath().equals(image)){
                    mark = car.getNameOfMark();
                    break;
                }
            }
        }
        else mark = req.getParameter("selectName");
        new DataBaseImpl().addOrder(userName, userSurname, email, "test-drive", mark, "20$", phone, date);
        try {
            Mail.sendTestDriveOrder(email, mark, date, "20$");
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (MessagingException e) {
            System.out.println("MessageException");
        }
        req.setAttribute("email", email);
        return "thanks";
    }
}
