package com.csc.adaas.eshop.registration.dto;

import java.io.Serializable;

public abstract class BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean checked;
	private String action;

	/**
	 * @return the checked
	 */
	public final boolean isChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public final void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * @return the action
	 */
	public final String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public final void setAction(String action) {
		this.action = action;
	}
}
