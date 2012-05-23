package com.crozin.wykop.sdk.exception;

@SuppressWarnings("serial")
public class CredentialsException extends SdkException {
	public CredentialsException(String message) {
		super(message);
	}
	
	public CredentialsException(String message, Integer code) {
		super(message, code);
	}
	
	public CredentialsException(String message, Throwable cause) {
		super(message, cause);
	}
}
