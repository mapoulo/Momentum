package Retirement.momentum.service.Exceptions;

public class RetirementAccountNotFoundException extends RuntimeException {
	
	
	public RetirementAccountNotFoundException() {
        super();
    }

    public RetirementAccountNotFoundException(String message) {
        super(message);
    }

    public RetirementAccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
