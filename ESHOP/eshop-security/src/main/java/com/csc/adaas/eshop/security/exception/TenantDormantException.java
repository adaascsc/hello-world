package com.csc.adaas.eshop.security.exception;

import org.springframework.security.core.AuthenticationException;

public class TenantDormantException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2503821545961573709L;
	
	public TenantDormantException(String msg) {
		super(msg);
	}
	
	public TenantDormantException(String msg, Throwable t) {
		super(msg, t);
	}
}
