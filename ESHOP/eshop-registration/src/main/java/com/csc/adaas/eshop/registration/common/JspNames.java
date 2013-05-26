package com.csc.adaas.eshop.registration.common;

public enum JspNames {
	
	REGISTRATION("registerUser"),
	MOBREGISTRATION("registerMobileUser"),
	HOME("home");
	
	/** view name */
	private String view = null;
	
	private JspNames(String view) {
		this.view = view;
	}
	
	public final String getView () {
		return view;
	}
}
