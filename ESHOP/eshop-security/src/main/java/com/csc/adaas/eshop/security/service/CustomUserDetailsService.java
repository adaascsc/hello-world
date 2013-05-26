package com.csc.adaas.eshop.security.service;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface CustomUserDetailsService extends UserDetailsService {
	
	public boolean updateLastAccessedTime (String userId) throws AuthenticationException;

}
