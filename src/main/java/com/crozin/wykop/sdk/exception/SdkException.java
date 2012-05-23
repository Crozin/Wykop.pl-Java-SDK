package com.crozin.wykop.sdk.exception;

@SuppressWarnings("serial")
public class SdkException extends RuntimeException {
	private Integer code;

	public SdkException(String message) {
		super(message);
	}
	
	public SdkException(String message, Integer code) {
		super(message);
		this.code = code;
	}
	
	public SdkException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public Integer getCode() {
		return code;
	}
	
	@Override
	public String toString() {
		String code = getCode() != null ? " (code: " + getCode() + ")" : "";
		return getClass().getName() + ": " + getMessage() + code;
	}
}
