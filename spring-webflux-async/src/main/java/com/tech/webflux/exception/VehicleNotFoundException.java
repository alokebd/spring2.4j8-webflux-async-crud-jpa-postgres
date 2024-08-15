package com.tech.webflux.exception;

public class VehicleNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public VehicleNotFoundException() {
	}

	public VehicleNotFoundException(String message) {
		super(message);
	}
}
