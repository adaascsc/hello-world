package com.csc.adaas.eshop.registration.common;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8965013777358139484L;
	
	/** String */
	private String key = null;

	/** Object[] for holding arguments */
	private Object[] msgValues = null;

	/**
	 * Default Constructor
	 */
	public Message() {
	}
	
	/**
	 * Overloaded Constructor
	 * @param key
	 */
	public Message(String key) {
		this.key = key;
	}
		
	/**
	 * Overloaded Constructor
	 * 
	 * @param key
	 * @param values
	 */
	public Message(String key, Object[] values) {
		this.key = key;
		this.msgValues = values;
	}

	/**
	 * @return Returns the key.
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            The key to set.
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return Returns the msgValues.
	 */
	public Object[] getMsgValues() {
		return msgValues;
	}

	/**
	 * @param msgValues
	 *            The msgValues to set.
	 */
	public void setMsgValues(Object[] msgValues) {
		this.msgValues = msgValues;
	}
}
