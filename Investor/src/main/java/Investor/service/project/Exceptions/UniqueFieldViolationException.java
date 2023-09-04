package Investor.service.project.Exceptions;

public class UniqueFieldViolationException extends RuntimeException{

	
	public UniqueFieldViolationException() {
        super();
    }

    public UniqueFieldViolationException(String message) {
        super(message);
    }

    public UniqueFieldViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
