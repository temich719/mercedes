package service.exception;

public class ServiceException extends Exception{

    public ServiceException() {
        super();
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Exception e) {
        super(e);
    }
}
