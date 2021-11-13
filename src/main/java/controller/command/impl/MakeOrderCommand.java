package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.Order;
import org.apache.log4j.Logger;
import service.CarService;
import service.OrderService;
import service.ServiceFactory;
import service.UserService;
import service.email.Mail;
import service.exception.ServiceException;
import service.util.Validator;

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
    private final UserService userService = serviceFactory.getUserService();

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
                if (req.getSession().getAttribute("locale").equals("ru")) req.setAttribute("error", "Заполните все обязательные поля!");
                else if (req.getSession().getAttribute("locale").equals("ch"))req.setAttribute("error", "請填寫所有必填字段！");
                else req.setAttribute("error", "Please fill in all required fields! ");
                req.setAttribute("img", imagePath);
                if (Objects.isNull(markAndPrice[1])) req.setAttribute("money", markAndPrice[2]);
                else req.setAttribute("price", markAndPrice[1]);
                req.setAttribute("mark", markAndPrice[0]);
                return "formOfOrder";
            }
            if (!Validator.validateEmail(email)){
                if (req.getSession().getAttribute("locale").equals("ru")) req.setAttribute("error", "Неверный email!");
                else if (req.getSession().getAttribute("locale").equals("ch"))req.setAttribute("error", "不合規電郵！ ");
                else req.setAttribute("error", "Invalid email! ");
                req.setAttribute("img", imagePath);
                if (Objects.isNull(markAndPrice[1])) req.setAttribute("money", markAndPrice[2]);
                else req.setAttribute("price", markAndPrice[1]);
                req.setAttribute("mark", markAndPrice[0]);
                return "formOfOrder";
            }
            if (!name.equals(userService.getName(email).getFirst()) || !surname.equals(userService.getName(email).getSecond())){
                if (req.getSession().getAttribute("locale").equals("ru")) req.setAttribute("error", "Имя или фамилия не совпадают с данными пользователя с данной почтой");
                else if (req.getSession().getAttribute("locale").equals("ch"))req.setAttribute("error", "名字或姓氏與此郵件的用戶數據不匹配 ");
                else req.setAttribute("error", "The first or last name does not match the user's data with this mail ");
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
