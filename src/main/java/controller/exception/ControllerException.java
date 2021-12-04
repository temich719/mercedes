package controller.exception;

import org.apache.log4j.Logger;

public class ControllerException extends Exception {

    private final static Logger LOGGER = Logger.getLogger(ControllerException.class);

    public ControllerException() {
        super();
        LOGGER.error("ERROR_OCCUPIED");
    }

    public ControllerException(String message) {
        super(message);
        LOGGER.error(message);
    }

    public ControllerException(Exception e) {
        super(e);
        LOGGER.error(e.toString());
    }

    public ControllerException(String message, Exception e) {
        super(message, e);
        LOGGER.error(message, e);
    }
}
