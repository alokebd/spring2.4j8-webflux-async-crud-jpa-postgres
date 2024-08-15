package com.tech.webflux.exception;

public class BadRequestException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public BadRequestException() {
	}

	public BadRequestException(String message) {
		super(message);
	}
}
