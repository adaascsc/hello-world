package com.csc.adaas.eshop.security.vo;

import java.io.Serializable;

public class UserAuthority implements Serializable {
	
	private static final long serialVersionUID = -4482517060496081067L;
	
	private String roleName;
	private String authorityName;

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	/**
	 * @return the roleName
	 */
	public final String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public final void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorityName == null) ? 0 : authorityName.hashCode());
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UserAuthority))
			return false;
		UserAuthority other = (UserAuthority) obj;
		if (authorityName == null) {
			if (other.authorityName != null)
				return false;
		} else if (!authorityName.equals(other.authorityName))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}
}
