package com.crozin.wykop.sdk.exception;

@SuppressWarnings("serial")
public class InvalidParametersException extends SdkException {
	public InvalidParametersException(String message) {
		super(message);
	}
	
	public InvalidParametersException(String message, Integer code) {
		super(message, code);
	}
	
	public InvalidParametersException(String message, Throwable cause) {
		super(message, cause);
	}
}