package tp1.exceptions;

public class GameModelException extends CommandExecuteException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6689340857589263909L;
	
	public GameModelException() {
	}

	public GameModelException(String message) {
		super(message);
	}

	public GameModelException(Throwable cause) {
		super(cause);
	}

	public GameModelException(String message, Throwable cause) {
		super(message, cause);
	}

	public GameModelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
