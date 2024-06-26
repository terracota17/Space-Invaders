package tp1.exceptions;

public class CommandParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8457538812861416749L;

	public CommandParseException() {
	}

	public CommandParseException(String message) {
		super(message);
	}

	public CommandParseException(Throwable cause) {
		super(cause);
	}

	public CommandParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
