package com.peter.backend_technical.handler;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = -1801235664403547093L;
	
	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}
}
