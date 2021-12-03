package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import dao.entity.Pair;
import org.apache.log4j.Logger;
import service.OrderService;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static controller.ControllerStringsStorage.*;

public class EnterCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(EnterCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final UserService userService = serviceFactory.getUserService();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        LOGGER.info("We got to EnterCommand");
        Pair pair = new Pair(req.getParameter(EMAIL), req.getParameter(PASSWORD));
        try {
            ArrayList<Pair> arrayList = userService.getEmailAndPassword();
            for (Pair value : arrayList) {
                if (value.getFirst().equals(pair.getFirst()) && value.getSecond().equals(pair.getSecond())) {
                    Pair names = userService.getName(pair.getFirst());
                    HttpSession session = req.getSession(true);
                    session.setAttribute(NAME_ACCOUNT, names.getFirst() + " " + names.getSecond());
                    session.setAttribute(ACCOUNT_NAME, names.getFirst());
                    session.setAttribute(ACCOUNT_SURNAME, names.getSecond());
                    session.setAttribute(EMAIL_ACCOUNT, pair.getFirst());
                    session.setAttribute(COUNT, orderService.getCountOfUnreadOrders(pair.getFirst()));
                    return JSP_USER + REGISTRATED_INDEX_PAGE;
                }
            }
        }
        catch (ServiceException e){
            throw new ControllerException(e);
        }
        if (req.getSession().getAttribute(LOCALE).equals("ru"))req.setAttribute(ERROR, "Неверный адрес электронной почты или пароль.");
        else if (req.getSession().getAttribute(LOCALE).equals("ch"))req.setAttribute(ERROR, "無效的電子郵件或密碼。 ");
        else req.setAttribute(ERROR, "Invalid email or password.");
        return JSP_USER + ENTER_PAGE;
    }
}
