package by.academy.news.service;

/**
 * @author Eugene Petrov
 */
public class ServiceException extends Exception{
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Exception exception){
        super(exception);
    }

    public ServiceException(String message, Exception exception){
        super(message, exception);
    }
}
