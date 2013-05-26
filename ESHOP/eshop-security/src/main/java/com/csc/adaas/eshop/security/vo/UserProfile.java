package com.csc.adaas.eshop.security.vo;

import java.sql.Timestamp;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserProfile extends User {
	
	private static final long serialVersionUID = 1371849486650503692L;
	
	private String userid;
	private String username;
	private String password;
	private String firstname;
	private String status;
	private Timestamp lastAccessed; 
	
	/**
	 * To get username, use getUserName method which will return the
	 * unique email id of the user
	 * @param user
	 * @param authorities
	 */
	public UserProfile (AuthenticateUser user, Collection<GrantedAuthority> authorities) {
		super(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
		this.setFirstname(user.getFirstname());
		this.setLastAccessed(user.getLastAccessed());
		this.setPassword(user.getPassword());
		this.setStatus(user.getStatus());
		this.setUserid(user.getUserid());
		this.setUsername(user.getUsername());
		
	}
	
	

	public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public Timestamp getLastAccessed() {
		return lastAccessed;
	}



	public void setLastAccessed(Timestamp lastAccessed) {
		this.lastAccessed = lastAccessed;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result	+ ((userid == null) ? 0 : userid.hashCode());
		return result;
	}

	/**
	 * super class will only check username, in order
	 * to have multi-tenancy support we will will check even the tenant id
	 * all other fields are not used for equals check
	 * only username - which will hold email id
	 * tenantId - which will hold the tenant key is used
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof UserProfile))
			return false;
		UserProfile other = (UserProfile) obj;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}
}
