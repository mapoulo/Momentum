package Investor.service.project.Exceptions;

public class SavingsAccountNotFoundException extends RuntimeException{

	

	public SavingsAccountNotFoundException() {
        super();
    }

    public SavingsAccountNotFoundException(String message) {
        super(message);
    }

    public SavingsAccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
