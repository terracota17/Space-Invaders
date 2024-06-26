package tp1.exceptions;

public class OffWorldException extends GameModelException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4978755843455451838L;

	public OffWorldException() {
	}

	public OffWorldException(String message) {
		super(message);
	}

	public OffWorldException(Throwable cause) {
		super(cause);
	}

	public OffWorldException(String message, Throwable cause) {
		super(message, cause);
	}

	public OffWorldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
