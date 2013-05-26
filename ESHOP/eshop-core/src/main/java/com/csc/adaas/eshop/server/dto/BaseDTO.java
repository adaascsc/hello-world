package com.csc.adaas.eshop.server.dto;

import java.io.Serializable;

public abstract class BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long version;
	private boolean checked;
	private String action;
	
	/**
	 * @return the version
	 */
	public final Long getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public final void setVersion(Long version) {
		this.version = version;
	}

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