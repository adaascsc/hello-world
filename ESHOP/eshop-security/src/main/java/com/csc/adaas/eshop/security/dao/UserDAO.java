package com.csc.adaas.eshop.security.dao;

import com.csc.adaas.eshop.security.vo.AuthenticateUser;


public interface UserDAO {
	
	public AuthenticateUser loadUserByUsername (String userId);
	
	public boolean updateLastAccessedTime (String userId);
}
