package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.Order;
import org.apache.log4j.Logger;
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

public class MakeOrderCommand implements ICommand {

    private final static Logger logger = Logger.getLogger(MakeOrderCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        logger.info("We got to MakeOrderCommand");
        final String name = req.getParameter("name");
        final String surname = req.getParameter("surname");
        final String email = req.getParameter("email");
        final String phone = req.getParameter("phone");
        final String imagePath = req.getParameter("img");
        try {
            String[] markAndPrice = carService.getMarkAndPriceByImage(imagePath);
            if (name.equals("") || surname.equals("") || email.equals("") || phone.equals("")) {
                req.setAttribute("error", "Заполните все обязательные поля!");
                req.setAttribute("img", imagePath);
                if (Objects.isNull(markAndPrice[1])) req.setAttribute("money", markAndPrice[2]);
                else req.setAttribute("price", markAndPrice[1]);
                req.setAttribute("mark", markAndPrice[0]);
                return "formOfOrder";
            }
            if (Objects.isNull(markAndPrice[1]))
                orderService.addOrder(
                    new Order(name, surname, email, "buying_a_car", markAndPrice[0], markAndPrice[2], phone, null, "unread")
            );
            else orderService.addOrder(
                    new Order(name, surname, email, "buying_a_car", markAndPrice[0], markAndPrice[1], phone, null, "unread")
            );
            try {
                Mail.sendOrder(email, markAndPrice[0], markAndPrice[1]);
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
