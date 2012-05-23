package com.crozin.wykop.sdk.exception;

@SuppressWarnings("serial")
public class ApiException extends SdkException {
	public ApiException(String message) {
		super(message);
	}
	
	public ApiException(String message, Integer code) {
		super(message, code);
	}
	
	public ApiException(String message, Throwable cause) {
		super(message, cause);
	}
}