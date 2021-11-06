package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.Pair;
import service.OrderService;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class EnterCommand implements ICommand {

    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        Pair pair = new Pair(req.getParameter("email"), req.getParameter("password"));
        try {
            ArrayList<Pair> arrayList = userService.getEmailAndPassword();
            for (Pair value : arrayList) {
                if (value.getFirst().equals(pair.getFirst()) && value.getSecond().equals(pair.getSecond())) {
                    Pair names = userService.getName(pair.getFirst());
                    HttpSession session = req.getSession(true);
                    session.setAttribute("nameAccount", names.getFirst() + " " + names.getSecond());
                    session.setAttribute("accountName", names.getFirst());
                    session.setAttribute("accountSurname", names.getSecond());
                    session.setAttribute("emailAccount", pair.getFirst());
                    session.setAttribute("count", orderService.getCountOfUnreadOrders(pair.getFirst()));
                    return "registratedIndex";
                }
            }
        }
        catch (ServiceException e){
            throw new ControllerException(e);
        }
        req.setAttribute("error", "Неверный адресс электронной почты или пароль.");
        return "enter";
    }
}
