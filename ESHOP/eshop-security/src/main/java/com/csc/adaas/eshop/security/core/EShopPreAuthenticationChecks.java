package com.csc.adaas.eshop.security.core;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

import com.csc.adaas.eshop.security.exception.UserNotActivatedException;
import com.csc.adaas.eshop.security.vo.UserProfile;

@Component(value = "eshopPreAuthenticationChecks")
public class EShopPreAuthenticationChecks implements UserDetailsChecker {

	private static String USER_ACTIVATED = "ACTIVE";
	
	private MessageSourceAccessor messages = SpringSecurityMessageSource
			.getAccessor();

	@Override
	public void check(UserDetails user) {
		UserProfile usr = null;
		if (user.getClass().isAssignableFrom(UserProfile.class)) {
			usr = (UserProfile) user;
		}
		//Check whether user - user id (email id) is proper with the correct domain of tenant
		
						
		
		// If user is not activated throw error
		if (!usr.getStatus().equals(USER_ACTIVATED))
			throw new UserNotActivatedException(
					messages.getMessage(
							"AbstractUserDetailsAuthenticationProvider.user.not.active",
							"Access Denied: User account is not activated. Status of user account: " + usr.getStatus()));

		System.out.println("usr into prechecks___________________"+usr.getStatus());
	}

}
