package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UploadAvatarCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String avatarPath = req.getParameter("ava");
        final String email = req.getSession().getAttribute("emailAccount").toString();
        //make in service
        DataBaseImpl.addAvatar(avatarPath, email);
        req.setAttribute("avatarImage","img/" + avatarPath);
        return "account";
    }
}
