package br.com.ssouza.exception;

public final class ApplicationException extends Exception {

	private static final long serialVersionUID = -4156305345744400074L;

	public ApplicationException() {
		super();
	}

	public ApplicationException(Exception exception) {
		super(exception);
	}

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(String message, Exception exception) {
		super(message, exception);
	}

}