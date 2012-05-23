package com.crozin.wykop.sdk.exception;

@SuppressWarnings("serial")
public class RequestException extends SdkException {
	public RequestException(String message) {
		super(message);
	}
	
	public RequestException(String message, Integer code) {
		super(message, code);
	}
	
	public RequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
