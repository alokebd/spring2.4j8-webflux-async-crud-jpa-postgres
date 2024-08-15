package com.tech.webflux.exception;

public class WebFluxCutomeException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public WebFluxCutomeException() {
	}

	public WebFluxCutomeException(String message) {
		super(message);
	}
}
