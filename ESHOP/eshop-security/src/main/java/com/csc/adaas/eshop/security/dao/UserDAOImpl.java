package com.csc.adaas.eshop.security.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.csc.adaas.eshop.security.vo.AuthenticateUser;
import com.csc.adaas.eshop.security.vo.UserAuthority;

/**
 * 
 * @author CSC
 * 
 */
@Repository
public class UserDAOImpl implements UserDAO {

	private Logger log = Logger.getLogger(UserDAOImpl.class.getName());
	private static final String NONE_USR = "NONE";
	
	/** JDBC Template instance */
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setSecurityDataSource (DataSource securityDataSource) {
		this.jdbcTemplate = new JdbcTemplate(securityDataSource);
	}

	public AuthenticateUser loadUserByUsername(String username) {
		username = (StringUtils.isEmpty(username) ? NONE_USR : username);
		Object[] arguments = new Object[] { username };
		StringBuilder sql = new StringBuilder();
				
		sql.append("SELECT S.USERID, S.USERNAME, S.PASSWORD,A.FIRSTNAME,A.STATUS,A.LASTACCESSED ");
		sql.append("FROM SIGNON S, ACCOUNT A ");
		sql.append("WHERE S.USERNAME = ? AND ");
		sql.append("S.USERID = A.USERID ");
		
		AuthenticateUser user = (AuthenticateUser) jdbcTemplate.queryForObject(
				sql.toString(), arguments, new UserRowMapper());
			
		sql = sql.delete(0, sql.length());

		sql.append("SELECT UR.ROLE_NAME, RA.AUTHORITY_NAME ");
		sql.append("FROM USER_ROLE UR, ROLE_AUTHORITIES RA ");
		sql.append("WHERE UR.USERID = ? AND ");
		sql.append("UR.ROLE_NAME = RA.ROLE_NAME");

		List<UserAuthority> authorities = (List<UserAuthority>) jdbcTemplate
				.query(sql.toString(), new Object[] { user.getUserid() },
						new BeanPropertyRowMapper(UserAuthority.class));

		user.setAuthorities(authorities);

		return user;

	}
	
	public boolean updateLastAccessedTime (String username) {
		StringBuilder sql = new StringBuilder();
		sql.append ("UPDATE ACCOUNT SET LASTACCESSED = ? ");
		sql.append ("WHERE EMAIL = ? ");
				
		Object[] args = new Object[] {new Timestamp(System.currentTimeMillis()), username};
	    int rowUpdated = jdbcTemplate.update(sql.toString(), args);
	    if (rowUpdated <= 0)
	    	return false;
	    else
	    	return true;
	}
}
