package br.com.ssouza.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -659906563168825714L;

	public BusinessException() {
		super();
	}

	public BusinessException(Exception exception) {
		super(exception);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Exception exception) {
		super(message, exception);
	}

}