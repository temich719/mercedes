package controller;

import controller.command.CommandFactory;
import controller.command.Command;
import controller.exception.ControllerException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static controller.ControllerStringsStorage.*;

public class FrontController extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(FrontController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doExecute(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doExecute(request, response);
    }

    private String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        Command command = CommandFactory.createCommand(request);
        return command.execute(request, response);
    }

    private void doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("START servlet : " + request.getMethod() + " : command : " + request.getParameter(COMMAND));
        String forward = null;
        try {
            forward = handleRequest(request, response);
        } catch (ControllerException e) {
            LOGGER.error("Something went wrong");
            request.getRequestDispatcher(WEB_INF + ERRORS + ERROR + JSP).forward(request, response);
        }
        if (Objects.nonNull(forward)) {
            if (forward.equals(INDEX_PAGE)) {
                request.getRequestDispatcher(forward + JSP).forward(request, response);
            } else {
                request.getRequestDispatcher(WEB_INF + forward + JSP).forward(request, response);
            }
        } else {
            LOGGER.info("Redirect response");
        }
    }
}