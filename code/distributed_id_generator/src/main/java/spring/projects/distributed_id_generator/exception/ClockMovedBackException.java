package spring.projects.distributed_id_generator.exception;

public class ClockMovedBackException extends Exception {
    public ClockMovedBackException(String message) {
        super(message);
    }
}
