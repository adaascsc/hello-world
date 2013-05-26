package com.csc.adaas.eshop.security.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

import com.csc.adaas.eshop.security.service.CustomUserDetailsService;
import com.csc.adaas.eshop.security.vo.UserProfile;

@Component(value="eshopPostAuthenticationChecks")
public class EShopPostAuthenticationChecks implements UserDetailsChecker {
	
	private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	
	@Autowired 
	private CustomUserDetailsService userDetailsService;

	@Override
	public void check(UserDetails user) {
		UserProfile usr = null;
		if (user.getClass().isAssignableFrom(UserProfile.class)) {
			usr = (UserProfile) user;
		}
		if (!usr.isCredentialsNonExpired()) {
             throw new CredentialsExpiredException(messages.getMessage(
                     "AbstractUserDetailsAuthenticationProvider.credentialsExpired",
                     "User credentials have expired"), user);
        }
		
		//Update Last accessed data to current date time so when 
		//user logs in next time, he is aware of when he last accessed this website
		userDetailsService.updateLastAccessedTime( usr.getUsername());
		 System.out.println("use nameeeeeeeeeeeeeeeeeeee..."+usr.getUsername());
	}

}
