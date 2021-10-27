package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Objects;

public class AccountPageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String avatarPath = new DataBaseImpl().getAvatarPathByEmail(req.getSession().getAttribute("emailAccount").toString());
        if (Objects.isNull(avatarPath))req.setAttribute("avatarImage", "img/avatar.jpg");
        else req.setAttribute("avatarImage", "img/" + avatarPath);
        return "account";
    }
}
