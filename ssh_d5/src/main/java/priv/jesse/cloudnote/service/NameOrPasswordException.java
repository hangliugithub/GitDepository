package priv.jesse.cloudnote.service;

public class NameOrPasswordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4530952121901967193L;

	public NameOrPasswordException() {
	}

	public NameOrPasswordException(String message) {
		super(message);
	}

	public NameOrPasswordException(Throwable cause) {
		super(cause);
	}

	public NameOrPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public NameOrPasswordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
