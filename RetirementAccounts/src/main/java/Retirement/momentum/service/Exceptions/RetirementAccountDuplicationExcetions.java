package Retirement.momentum.service.Exceptions;

public class RetirementAccountDuplicationExcetions extends RuntimeException {


	private static final long serialVersionUID = 1L;

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
