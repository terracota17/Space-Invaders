package tp1.exceptions;

public class PostionCreationException  extends GameModelException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4718713730350933937L;

	public PostionCreationException() {
	}

	public PostionCreationException(String message) {
		super(message);
	}

	public PostionCreationException(Throwable cause) {
		super(cause);
	}

	public PostionCreationException(String message, Throwable cause) {
		super(message, cause);
	}

	public PostionCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
