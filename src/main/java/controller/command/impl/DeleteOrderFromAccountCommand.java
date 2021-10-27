package controller.command.impl;

import controller.command.ICommand;
import dao.database.impl.DataBaseImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DeleteOrderFromAccountCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        final String name = req.getSession().getAttribute("accountName").toString();
        final String surname = req.getSession().getAttribute("accountSurname").toString();
        final String email = req.getSession().getAttribute("emailAccount").toString();
        final String service = req.getParameter("service");
        final String mark = req.getParameter("mark");
        final String price = req.getParameter("price");
        final String date = req.getParameter("date");
        final String phone = req.getParameter("phone");
        new DataBaseImpl().deleteOrder(name, surname, email, service, mark, price, date, phone);
        req.setAttribute("avatarImage", "img/" + new DataBaseImpl().getAvatarPathByEmail(email));
        req.getSession().setAttribute("count", DataBaseImpl.getCountOfUnreadOrders(email));
        return "account";
    }
}
