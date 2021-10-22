package controller.command;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {

    private CommandFactory(){}

    public static ICommand createCommand(HttpServletRequest request){
        String command = request.getParameter("command");
        ICommand iCommand;
        if (command != null){
            iCommand = Commands.valueOf(command).getCommand();
        }
        else {
            iCommand = Commands.valueOf("ERROR_COMMAND").getCommand();
        }
        return iCommand;
    }
}