package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface ICommand {
    String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException;
}