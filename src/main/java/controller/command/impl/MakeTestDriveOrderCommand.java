package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.Order;
import service.CarService;
import service.OrderService;
import service.ServiceFactory;
import service.email.Mail;
import service.exception.ServiceException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class MakeTestDriveOrderCommand implements ICommand {

    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        final String userName = req.getParameter("name");
        final String userSurname = req.getParameter("surname");
        final String email = req.getParameter("email");
        final String phone = req.getParameter("phone");
        final String date = req.getParameter("date");
        final String sel = req.getParameter("sel");
        final String def = req.getParameter("defImage");
        System.out.println(def);
        if (userName.equals("") || userSurname.equals("") || email.equals("") || phone.equals("")
                || date.equals("")){
            req.setAttribute("error", "Заполните все обязательные поля!");
            if (Objects.nonNull(sel)){
                req.setAttribute("select", "true");
                req.setAttribute("defImage", def);
                try {
                    req.setAttribute("sel", carService.getCarMarkByImage(def));
                } catch (ServiceException e) {
                    throw new ControllerException(e);
                }
            }
            return "testDriveOrder";
        }
        String mark;
        String image;
        try {
            if (Objects.isNull(req.getParameter("selectName"))) {
                image = req.getParameter("defImage");
                System.out.println(image);
                mark = carService.getCarMarkByImage(image);
                System.out.println(mark);
            } else mark = req.getParameter("selectName");
            orderService.addOrder(new Order(userName, userSurname, email, "test-drive", mark, "20$", phone, date, "unread"));
            try {
                Mail.sendTestDriveOrder(email, mark, date, "20$");
            } catch (IOException e) {
                System.out.println("IOException");
            } catch (MessagingException e) {
                System.out.println("MessageException");
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
