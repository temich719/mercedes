package controller.command;

import javax.servlet.http.HttpServletRequest;

import static controller.ControllerStringsStorage.*;

public class CommandFactory {

    private CommandFactory() {
    }

    public static ICommand createCommand(HttpServletRequest request) {
        String command = request.getParameter(COMMAND);
        ICommand iCommand;
        if (command != null && !command.equals("")) {
            iCommand = Commands.valueOf(command).getCommand();
        } else {
            iCommand = Commands.valueOf(ERROR_COMMAND).getCommand();
        }
        return iCommand;
    }
}