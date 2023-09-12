package Withdrwal.momentum.proj.Exception;

public class AgeLessThan65Exception extends RuntimeException {

	public AgeLessThan65Exception() {
        super();
    }

    public AgeLessThan65Exception(String message) {
        super(message);
    }

    public AgeLessThan65Exception(String message, Throwable cause) {
        super(message, cause);
    }
}
