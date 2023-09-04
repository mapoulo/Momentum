package Investor.service.project.Exceptions;

public class InvestorNotFoundException extends RuntimeException {


	public InvestorNotFoundException() {
        super();
    }

    public InvestorNotFoundException(String message) {
        super(message);
    }

    public InvestorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
