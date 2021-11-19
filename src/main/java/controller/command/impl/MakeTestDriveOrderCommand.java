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

public class MakeTestDriveOrderCommand implements ICommand {

    private final static Logger logger = Logger.getLogger(MakeTestDriveOrderCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        logger.info("We got to MakeTestDriveOrderCommand");
        final String userName = req.getParameter("name");
        final String userSurname = req.getParameter("surname");
        final String email = req.getParameter("email");
        final String phone = req.getParameter("phone");
        final String date = req.getParameter("date");
        final String sel = req.getParameter("sel");
        final String def = req.getParameter("defImage");
        if (userName.equals("") || userSurname.equals("") || email.equals("") || phone.equals("")
                || date.equals("")){
            if (req.getSession().getAttribute("locale").equals("ru")) req.setAttribute("error", "Заполните все обязательные поля!");
            else if (req.getSession().getAttribute("locale").equals("ch"))req.setAttribute("error", "請填寫所有必填字段！");
            else req.setAttribute("error", "Please fill in all required fields! ");
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
        if (!Validator.validateEmail(email)){
            if (req.getSession().getAttribute("locale").equals("ru")) req.setAttribute("error", "Неверный email!");
            else if (req.getSession().getAttribute("locale").equals("ch"))req.setAttribute("error", "不合規電郵！ ");
            else req.setAttribute("error", "Invalid email! ");
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
        try {
            if (Objects.nonNull(req.getSession().getAttribute("nameAccount"))) {
                if (!userName.equals(userService.getName(email).getFirst()) || !userSurname.equals(userService.getName(email).getSecond())) {
                    if (req.getSession().getAttribute("locale").equals("ru"))
                        req.setAttribute("error", "Имя или фамилия не совпадают с данными пользователя с данной почтой");
                    else if (req.getSession().getAttribute("locale").equals("ch"))
                        req.setAttribute("error", "名字或姓氏與此郵件的用戶數據不匹配 ");
                    else
                        req.setAttribute("error", "The first or last name does not match the user's data with this mail ");
                    if (Objects.nonNull(sel)) {
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
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        String mark;
        String image;
        try {
            if (Objects.isNull(req.getParameter("selectName"))) {
                image = req.getParameter("defImage");
                System.out.println(image);
                mark = carService.getCarMarkByImage(image);
            } else mark = req.getParameter("selectName");
            orderService.addOrder(new Order(userName, userSurname, email, "test-drive", mark, "20$", phone, date, "unread"));
            try {
                Mail.sendTestDriveOrder(email, mark, date, "20$", req);
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
