package tp1.exceptions;

public class InitializationException extends CommandParseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2417685824331510271L;

	public InitializationException() {
	}

	public InitializationException(String message) {
		super(message);
	}

	public InitializationException(Throwable cause) {
		super(cause);
	}

	public InitializationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InitializationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
