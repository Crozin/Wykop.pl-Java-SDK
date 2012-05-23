package com.crozin.wykop.sdk.exception;

@SuppressWarnings("serial")
public class InvalidResponseException extends SdkException {
	public InvalidResponseException(String message) {
		super(message);
	}
	
	public InvalidResponseException(String message, Integer code) {
		super(message, code);
	}
	
	public InvalidResponseException(String message, Throwable cause) {
		super(message, cause);
	}
}
