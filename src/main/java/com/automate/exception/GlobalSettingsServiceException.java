package com.automate.exception;

import org.springframework.http.HttpStatus;

public class GlobalSettingsServiceException extends Exception{
	
	/**
	 * 
	 */
	
	
	
	private static final long serialVersionUID = 1L;
	private HttpStatus statusCode;

	public GlobalSettingsServiceException(String msg) {
		super(msg);
	}
	
	public GlobalSettingsServiceException(String msg,HttpStatus statusCode) {
		super(msg);
		this.statusCode=statusCode;
	}
	
	public GlobalSettingsServiceException(String msg,Throwable t) {
		super(msg,t);
	}
	
	public HttpStatus getStatusCode() {
		return statusCode;
	}

}
