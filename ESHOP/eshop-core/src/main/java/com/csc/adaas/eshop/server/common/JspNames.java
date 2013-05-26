package com.csc.adaas.eshop.server.common;

public enum JspNames {
	
	LOGIN("login"),
	HOME("home"),
	SEARCH("search"),
	SEARCHRESULT("searchResult"),
	ACCESS_DENIED("includes/accessDenied"),
	ERROR_PAGE("includes/error"),
	REGISTRATION("registerUser"),
	TWITTERCONNECT("connect/twitterConnect"),
	TWITTERCONNECTED("connect/twitterConnected"),
	TWITTER("connect/twitter");
	
	/** view name */
	private String view = null;
	
	private JspNames(String view) {
		this.view = view;
	}
	
	public final String getView () {
		return view;
	}
}
