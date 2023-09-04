package Investor.service.project.Exceptions;

public class ExcessiveWithdrawalException extends RuntimeException{
	
	public ExcessiveWithdrawalException() {
        super();
    }

    public ExcessiveWithdrawalException(String message) {
        super(message);
    }

    }
