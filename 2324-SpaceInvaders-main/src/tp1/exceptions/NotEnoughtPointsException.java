package tp1.exceptions;

public class NotEnoughtPointsException extends GameModelException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7421273883977222965L;

	public NotEnoughtPointsException() {
	}

	public NotEnoughtPointsException(String message) {
		super(message);
	}

	public NotEnoughtPointsException(Throwable cause) {
		super(cause);
	}

	public NotEnoughtPointsException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotEnoughtPointsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
