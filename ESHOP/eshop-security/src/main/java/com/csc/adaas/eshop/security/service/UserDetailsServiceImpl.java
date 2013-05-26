package com.csc.adaas.eshop.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csc.adaas.eshop.security.dao.UserDAO;
import com.csc.adaas.eshop.security.vo.AuthenticateUser;
import com.csc.adaas.eshop.security.vo.UserAuthority;
import com.csc.adaas.eshop.security.vo.UserProfile;



@Service("customUserDetailsService")
public class UserDetailsServiceImpl implements CustomUserDetailsService {

	@Autowired
	private UserDAO userDAO;

	public UserDetails loadUserByUsername(java.lang.String username)
			throws UsernameNotFoundException, DataAccessException {
		UserDetails userdetail = null;
		try {
			AuthenticateUser user = userDAO.loadUserByUsername(username);
			
			System.out.println("User name here+++"+user.getFirstname());
			// If user is null throw UserNameNotFoundException
			if (user == null)
				throw new UsernameNotFoundException(
						"Username is not a registered user of this website");

			// load Spring UserDetails object by calling constructor of
			// UserProfile
			// This method will return UserProfile object which then gets stored
			// in
			// SecurityContextHolder Spring class and one can access this object
			// by calling
			// SecurityContextHolder.getContext().getAuthentication().getPrincipal()
			// : UserProfile
			userdetail = new UserProfile(user, getAuthorities(user));
		} catch (Exception e) {
			throw new UsernameNotFoundException(
					"Username not found, please try again");
		}

		return userdetail;
	}

	private Collection<GrantedAuthority> getAuthorities(AuthenticateUser user) {
		// get the Authorities from AuthenticateUser object to created
		// spring GrantedAuthority Class and add to a list
		List<UserAuthority> authorities = user.getAuthorities();
		if (authorities == null || authorities.size() <= 0)
			throw new AuthenticationServiceException(
					"User Granted Authorities not defined, cannot access website. Please contact system administrator");

		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(
				authorities.size());

		// Add the authority names with corresponding role name
		// Even if the role name is duplicated only unique names gets into
		// ArrayList
		for (UserAuthority authority : authorities) {
			authList.add(new GrantedAuthorityImpl(authority.getRoleName()));
			authList.add(new GrantedAuthorityImpl(authority.getAuthorityName()));
		}
		return authList;
	}

	
	public boolean updateLastAccessedTime(String userId)
			throws AuthenticationException {
		return userDAO.updateLastAccessedTime(userId);
	}
}
