package dao.exception;

import org.apache.log4j.Logger;

public class DAOException extends Exception{

    private final static Logger logger = Logger.getLogger(DAOException.class);
    private static final long serialVersionUID = 1L;

    public DAOException() {
        super();
        logger.error("ERROR_OCCUPIED");
    }

    public DAOException(String message) {
        super(message);
        logger.error(message);
    }

    public DAOException(Exception e) {
        super(e);
        logger.error(e);
    }

    public DAOException(String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }
}
