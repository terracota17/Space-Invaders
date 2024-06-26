package tp1.exceptions;

public class LaserInFlightException extends GameModelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2091047484768376551L;
	
	public LaserInFlightException() {
	}

	public LaserInFlightException(String message) {
		super(message);
	}

	public LaserInFlightException(Throwable cause) {
		super(cause);
	}

	public LaserInFlightException(String message, Throwable cause) {
		super(message, cause);
	}

	public LaserInFlightException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
