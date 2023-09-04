package Investor.service.project.Exceptions;

public class SavingsAccountDuplicationExcetion extends RuntimeException {

	
	public SavingsAccountDuplicationExcetion() {
        super();
    }

    public SavingsAccountDuplicationExcetion(String message) {
        super(message);
    }

    public SavingsAccountDuplicationExcetion(String message, Throwable cause) {
        super(message, cause);
    }
}
