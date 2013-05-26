package com.csc.adaas.eshop.registration.common;

public enum States {
	
	ANDHRA("AP", "ANDHRA PRADESH"),
	MADHYA("MP", "MADHYA PRADESH"),
	KARNATAKA("KA", "KARNATAKA"),
	KERALA("KR", "KERELA"),
	GUJARAT("GU", "GUJARAT"),
	TAMILNADU("TN", "TAMILNADU"),
	MAHARASHTRA("MH", "MAHARASHTRA"),
	NEWDELHI("DEL", "NEW DELHI"),
	PONDY("PY", "PONDICHERRY");
	
	/** key */
	private String key = null;
	
	/** value */
	private String value = null;
	
	
	private States(String key, String value) {
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
