package controller.exception;

import org.apache.log4j.Logger;

public class ControllerException extends Exception {

    private final static Logger logger = Logger.getLogger(ControllerException.class);

    private static final long serialVersionUID = 1L;

    public ControllerException() {
        super();
        logger.error("ERROR_OCCUPIED");
    }

    public ControllerException(String message) {
        super(message);
        logger.error(message);
    }

    public ControllerException(Exception e) {
        super(e);
        logger.error(e.toString());
    }

    public ControllerException(String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }
}
