package com.csc.adaas.eshop.server.common;


public enum YesNo {
	
	YES("Y", "Yes"),
	NO("N", "No");
	
	/** key */
	private String key = null;
	
	/** value */
	private String value = null;
	
	
	private YesNo(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public final String getKey () {
		return key;
	}
	
	public final String getValue () {
		return value;
	}
}
