package com.csc.adaas.eshop.security.core;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class CustomRoleVoter extends RoleVoter {

	private Logger log = Logger.getLogger(CustomRoleVoter.class.getName());

	public int vote(Authentication authentication, Object object,
			Collection<ConfigAttribute> attributes) {
		log.info("Entering vote in CustomRoleVoter class");
		int result = AccessDecisionVoter.ACCESS_ABSTAIN;
		Collection<GrantedAuthority> authorities = authentication
				.getAuthorities();
		if (attributes == null)
			log.info("Attributes are null check whats happening");
		for (ConfigAttribute attribute : attributes) {
			if (attribute.getAttribute() != null) {
				log.info("The value of individual attribute is: [" + attribute.getAttribute() + "]");
				result = AccessDecisionVoter.ACCESS_DENIED;

				// Attempt to find a matching granted authority
				for (GrantedAuthority authority : authorities) {
					if (attribute.getAttribute().equals(
							authority.getAuthority())) {
						log.info("Accsess granted for user: ["
								+ authentication.getName()
								+ "] and authority is: ["
								+ authority.getAuthority() + "]");
						return AccessDecisionVoter.ACCESS_GRANTED;
					}
				}
			}
		}

		return result;
	}

}
