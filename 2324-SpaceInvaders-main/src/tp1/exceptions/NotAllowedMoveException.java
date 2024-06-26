package tp1.exceptions;

public class NotAllowedMoveException extends CommandParseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4688765452678902789L;
	
	public NotAllowedMoveException() {
	}

	public NotAllowedMoveException(String message) {
		super(message);
	}

	public NotAllowedMoveException(Throwable cause) {
		super(cause);
	}

	public NotAllowedMoveException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotAllowedMoveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
