package tp1.exceptions;

public class CommandExecuteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7049668120630270250L;

	public CommandExecuteException() {
	}

	public CommandExecuteException(String message) {
		super(message);
	}

	public CommandExecuteException(Throwable cause) {
		super(cause);
	}

	public CommandExecuteException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandExecuteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
