package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.Order;
import service.OrderService;
import service.ServiceFactory;
import service.email.Mail;
import service.exception.ServiceException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class MakeServiceOrderCommand implements ICommand {

    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
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
        try {
            orderService.addOrder(new Order(userName, userSurname, email, "service", mark, "После осмотра", phone, date, "unread"));
            try {
                Mail.sendServiceOrder(email, mark, date, "После осмотра");
            } catch (IOException e) {
                System.out.println("IOException");
            } catch (MessagingException e) {
                System.out.println("MessagingException");
            }
            req.setAttribute("email", email);
            req.getSession().setAttribute("count", orderService.getCountOfUnreadOrders(email));
        }
        catch (ServiceException e){
            throw new ControllerException(e);
        }
        return "thanks";
    }
}
