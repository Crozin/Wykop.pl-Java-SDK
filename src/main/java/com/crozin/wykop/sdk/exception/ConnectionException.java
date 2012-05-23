package com.crozin.wykop.sdk.exception;

@SuppressWarnings("serial")
public class ConnectionException extends SdkException {
	public ConnectionException(String message) {
		super(message);
	}
	
	public ConnectionException(String message, Integer code) {
		super(message, code);
	}
	
	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
}