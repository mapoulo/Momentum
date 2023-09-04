package Investor.service.project.Exceptions;

public class RetirementAccountDuplicationExcetions extends RuntimeException {

	
	public RetirementAccountDuplicationExcetions() {
        super();
    }

    public RetirementAccountDuplicationExcetions(String message) {
        super(message);
    }

    public RetirementAccountDuplicationExcetions(String message, Throwable cause) {
        super(message, cause);
    }
}
