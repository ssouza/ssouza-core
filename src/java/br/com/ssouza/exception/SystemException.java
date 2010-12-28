package br.com.ssouza.exception;

public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 3689148798449877945L;

	public SystemException() {
		super();
	}

	public SystemException(Exception exception) {
		super(exception);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(String message, Exception exception) {
		super(message, exception);
	}

}