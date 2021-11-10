package service.exception;

import org.apache.log4j.Logger;

public class ServiceException extends Exception{

    private final static Logger logger = Logger.getLogger(ServiceException.class);

    public ServiceException() {
        super();
        logger.error("ERROR_OCCUPIED");
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public ServiceException(String message) {
        super(message);
        logger.error(message);
    }

    public ServiceException(Exception e) {
        super(e);
        logger.error(e.toString());
    }
}
