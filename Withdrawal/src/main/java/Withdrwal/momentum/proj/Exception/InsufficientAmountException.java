package Withdrwal.momentum.proj.Exception;

public class InsufficientAmountException extends RuntimeException {
	
	public InsufficientAmountException() {
        super();
    }

    public InsufficientAmountException(String message) {
        super(message);
    }

    public InsufficientAmountException(String message, Throwable cause) {
        super(message, cause);
    }

}
