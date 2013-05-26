package com.csc.adaas.eshop.server.common;

import org.springframework.core.NestedRuntimeException;

public class ApplicationException extends NestedRuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4224113405514683918L;
	private final String errorCode;
	private final boolean isErrorCodeGiven;
	
	
	public ApplicationException (String msg) {
		super(msg);
		errorCode = null;
		isErrorCodeGiven = false;
	}
	
	public ApplicationException (String msg, Throwable cause) {
		super(msg, cause);
		errorCode = null;
		isErrorCodeGiven = false;
	}
	
	public ApplicationException (String errorCode, boolean isErrorCodeGiven) {
		super(errorCode);
		this.errorCode = errorCode;
		this.isErrorCodeGiven = true;
		
	}
	
	public ApplicationException (String errorCode, Throwable cause, boolean isErrorCodeGiven) {
		super(errorCode, cause);
		this.errorCode = errorCode;
		this.isErrorCodeGiven = true;
		
	}
	
	public String getErrorCode() {
		return this.errorCode;
	}
	
	public boolean isErrorCodeGiven() {
		return this.isErrorCodeGiven;
	}
}
