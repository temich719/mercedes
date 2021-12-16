package controller.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.Objects;

import static controller.ControllerStringsStorage.*;

public class CommandFactory {

    private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);

    private CommandFactory() {
    }

    public static Command createCommand(HttpServletRequest request) {
        String commandParameter = request.getParameter(COMMAND);
        Command command = CommandEnum.ERROR_COMMAND.getCommand();
        if (Objects.nonNull(command) && !commandParameter.isEmpty()) {
            try {
                CommandEnum currentCommand = CommandEnum.valueOf(commandParameter);
                command = currentCommand.getCommand();
            } catch (IllegalArgumentException e) {
                LOGGER.error("Illegal parameters");
            }
        }
        return command;
    }
}