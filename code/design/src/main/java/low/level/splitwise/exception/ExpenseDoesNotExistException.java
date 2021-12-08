package low.level.splitwise.exception;

public class ExpenseDoesNotExistException extends Exception {
    public ExpenseDoesNotExistException(String msg) {
     super(msg);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }    
}
