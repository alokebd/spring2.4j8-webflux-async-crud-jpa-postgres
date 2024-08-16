package com.tech.webflux.exception;

public class WebFluxCommonException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public WebFluxCommonException() {
	}

	public WebFluxCommonException(String message) {
		super(message);
	}
}
