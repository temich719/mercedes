package controller.command.impl;

import controller.command.ICommand;
import controller.exception.ControllerException;
import service.CarService;
import service.ServiceFactory;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class FormOrderCommand implements ICommand {

    private final ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private final CarService carService = serviceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ControllerException {
        final String imagePath = req.getParameter("img");
        req.setAttribute("img", imagePath);
        try {
            String[] markAndPrice = carService.getMarkAndPriceByImage(imagePath);
            req.setAttribute("mark", markAndPrice[0]);
            if (Objects.isNull(markAndPrice[1])) req.setAttribute("money", markAndPrice[2]);
            else req.setAttribute("price", markAndPrice[1]);
            req.setAttribute("carName", markAndPrice[0]);

            if (Objects.isNull(req.getSession().getAttribute("nameAccount"))) {
                req.setAttribute("nameAccount", "");
                req.setAttribute("surnameAccount", "");
                req.setAttribute("emailAccount", "");
            } else {
                final String nameSurname = req.getSession().getAttribute("nameAccount").toString();
                String[] strings = nameSurname.split(" ");
                req.setAttribute("nameAccount", strings[0]);
                req.setAttribute("surnameAccount", strings[1]);
                req.setAttribute("emailAccount", req.getSession().getAttribute("emailAccount"));
            }
        }
        catch (ServiceException e){
            throw new ControllerException(e);
        }
        return "formOfOrder";
    }
}
