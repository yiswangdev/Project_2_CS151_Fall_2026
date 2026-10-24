package exceptions;

public class InstanceLimitException extends RuntimeException {
    
    public InstanceLimitException(String message) {
        super(message);
    }
}
