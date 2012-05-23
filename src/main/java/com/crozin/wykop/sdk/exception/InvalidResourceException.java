package com.crozin.wykop.sdk.exception;

@SuppressWarnings("serial")
public class InvalidResourceException extends SdkException {
	public InvalidResourceException(String message) {
		super(message);
	}
	
	public InvalidResourceException(String message, Integer code) {
		super(message, code);
	}
	
	public InvalidResourceException(String message, Throwable cause) {
		super(message, cause);
	}
}
