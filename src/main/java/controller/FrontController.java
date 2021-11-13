package controller;

import controller.command.CommandFactory;
import controller.command.ICommand;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet{

    private final static Logger logger = Logger.getLogger(FrontController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String locale = req.getParameter("locale");
        req.getSession().setAttribute("locale", locale);
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String forward = null;
        try {
            forward = handleRequest(request, response);
        } catch (ControllerException throwable) {
            throwable.printStackTrace();
        }
        request.getRequestDispatcher( forward + ".jsp").forward(request, response);
    }

    private String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        ICommand iCommand = CommandFactory.createCommand(request);
        return iCommand.execute(request, response);
    }
}