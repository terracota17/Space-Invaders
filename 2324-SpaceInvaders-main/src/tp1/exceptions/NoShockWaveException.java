package tp1.exceptions;

public class NoShockWaveException extends GameModelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2988924153179938680L;

	public NoShockWaveException() {
	}

	public NoShockWaveException(String message) {
		super(message);
	}

	public NoShockWaveException(Throwable cause) {
		super(cause);
	}

	public NoShockWaveException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoShockWaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
