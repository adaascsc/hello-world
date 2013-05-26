package com.csc.adaas.eshop.web;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.csc.adaas.eshop.security.vo.AuthenticateUser;
import com.csc.adaas.eshop.security.vo.UserProfile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class EShopSecurtiyTestBean {

	@Autowired
	private DaoAuthenticationProvider daoAuthenticationProvider;

	@Autowired
	private ShaPasswordEncoder passwordEncoder;

	@Autowired
	private ReflectionSaltSource saltSource;

	private MockHttpServletRequest request = new MockHttpServletRequest();
	private MockHttpServletResponse response = new MockHttpServletResponse();
	private Authentication authentiction;

	private String[] emailIds = new String[] { "rharini@csc.com", "jayadeep@csc.com", "kmanoj@csc.com",
			"bmathew@csc.com", "venkat@csc.com", "haribabu@csc.com" };

	private String[] passwords = new String[] { "harini", "jayadeep",
			"manoj", "biju", "venkat", "haribabu" };

	// @Test
	public void authentication() {
		String prinicipal = "rharini@csc.com";
		String credentials = "harini";
		UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(
				prinicipal, credentials);
		authentiction = daoAuthenticationProvider.authenticate(user);
	}

	@Test
	public void testSHA() {
		for (int i = 0; i < emailIds.length; i++) {
			AuthenticateUser authenticateUsr = new AuthenticateUser();
			authenticateUsr.setUsername((emailIds[i].toString()));
			authenticateUsr.setPassword(passwords[i]);
			List list = new ArrayList();
			list.add(new GrantedAuthorityImpl("test"));
			UserProfile user = new UserProfile(authenticateUsr, list);
			Object value = saltSource.getSalt(user);
			String encodedPass = passwordEncoder.encodePassword(
					authenticateUsr.getPassword(), value);
			System.out.println("Encoded password: [" + encodedPass
					+ "] for username: [" + emailIds[i]
					+ "] and raw password: [" + passwords[i] + "]");
		}
	}
}
