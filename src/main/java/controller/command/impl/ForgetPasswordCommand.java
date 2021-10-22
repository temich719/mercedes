package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;
import dao.entity.Pair;
import service.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class ForgetPasswordCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String newPassword = req.getParameter("newPassword");
        final String email = req.getParameter("emailUpdate");
        if (!Validator.validatePassword(newPassword)){
            req.setAttribute("err","Пароль должен содержать латинскую букву верхнего и нижнего регистра, цифру, " +
                    "спецсимвол и иметь длину от 8 до 16 символов");
            return "forgetPassword";
        }
        final String confirmNewPassword = req.getParameter("confirmNewPassword");
        if (!newPassword.equals(confirmNewPassword)){
            req.setAttribute("error","Пароль введен неверно!");
            return "forgetPassword";
        }
        DataBaseImpl.updatePassword(email, newPassword);
        Pair pair = DataBaseImpl.getName(email);
        HttpSession session = req.getSession(true);
        session.setAttribute("nameAccount", pair.getFirst() + " " + pair.getSecond());
        session.setAttribute("emailAccount", email);
        return "registratedIndex";
    }
}
