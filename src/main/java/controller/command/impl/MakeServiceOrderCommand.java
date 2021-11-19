package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.Order;
import org.apache.log4j.Logger;
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

public class MakeServiceOrderCommand implements ICommand {

    private final static Logger logger = Logger.getLogger(MakeServiceOrderCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        logger.info("We got to MakeServiceOrderCommand");
        final String userName = req.getParameter("name");
        final String userSurname = req.getParameter("surname");
        final String email = req.getParameter("email");
        final String phone = req.getParameter("phone");
        final String date = req.getParameter("date");
        final String select = req.getParameter("select");
        if (userName.equals("") || userSurname.equals("") || email.equals("") || phone.equals("")
                || date.equals("")){
            if (req.getSession().getAttribute("locale").equals("ru")) req.setAttribute("error", "Заполните все обязательные поля!");
            else if (req.getSession().getAttribute("locale").equals("ch"))req.setAttribute("error", "請填寫所有必填字段！");
            else req.setAttribute("error", "Please fill in all required fields! ");
            req.setAttribute("select", select);
            return "serviceOrder";
        }
        if (!Validator.validateEmail(email)){
            if (req.getSession().getAttribute("locale").equals("ru")) req.setAttribute("error", "Неверный email!");
            else if (req.getSession().getAttribute("locale").equals("ch"))req.setAttribute("error", "不合規電郵！ ");
            else req.setAttribute("error", "Invalid email! ");
            req.setAttribute("select", select);
            return "serviceOrder";
        }
        try {
            if (Objects.nonNull(req.getSession().getAttribute("nameAccount"))) {
                if (!userName.equals(userService.getName(email).getFirst()) || !userSurname.equals(userService.getName(email).getSecond())) {
                    if (req.getSession().getAttribute("locale").equals("ru"))
                        req.setAttribute("error", "Имя или фамилия не совпадают с данными пользователя с данной почтой");
                    else if (req.getSession().getAttribute("locale").equals("ch"))
                        req.setAttribute("error", "名字或姓氏與此郵件的用戶數據不匹配 ");
                    else
                        req.setAttribute("error", "The first or last name does not match the user's data with this mail ");
                    req.setAttribute("select", select);
                    return "serviceOrder";
                }
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        final String mark;
        if (Objects.isNull(req.getParameter("selectName")))mark = req.getParameter("mark");
        else mark = req.getParameter("selectName");
        try {
            orderService.addOrder(new Order(userName, userSurname, email, "service", mark, "После осмотра", phone, date, "unread"));
            try {
                Mail.sendServiceOrder(email, mark, date, "После осмотра", req);
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
