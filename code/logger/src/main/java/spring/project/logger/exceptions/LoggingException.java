package spring.project.logger.exceptions;

public class LoggingException extends RuntimeException {

    public LoggingException(Exception e) {
        super(e);
    }
}
