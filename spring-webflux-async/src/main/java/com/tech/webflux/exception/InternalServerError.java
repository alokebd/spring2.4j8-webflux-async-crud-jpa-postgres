package com.tech.webflux.exception;

public class InternalServerError extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InternalServerError() {
	}

	public InternalServerError(String message) {
		super(message);
	}
}
