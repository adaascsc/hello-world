package com.csc.adaas.eshop.registration.dao;

import static com.csc.adaas.eshop.registration.common.RegistrationConstants.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.csc.adaas.eshop.registration.dto.UserRegistrationDTO;
import com.csc.adaas.eshop.registration.common.ApplicationException;
import com.csc.adaas.eshop.registration.common.Util;


/**
 * 
 * @author rharini
 * 
 */
@Repository
public class UserRegistrationDAOImpl implements UserRegistrationDAO {

	private Logger log = Logger.getLogger(UserRegistrationDAOImpl.class.getName());
	
	/** JDBC Template instance */
	private JdbcTemplate jdbcTemplate;
	
	@Autowired (required= false)
	public void setSecurityDataSource (DataSource securityDataSource) {
		this.jdbcTemplate = new JdbcTemplate(securityDataSource);
	}
	
	 @Autowired  (required= false)
	 private PasswordEncoder passwordEncoder;
	 
	 @Autowired (required= false)
	  private SaltSource saltSource;
	  @Autowired (required= false)
	  private UserDetailsService userDetailsService;
	  
	/**
	 * sample method
	 */

	
	@Override
	public boolean registerNewUser(UserRegistrationDTO registerUser) {
				
		StringBuilder sql = new StringBuilder();
		String strSql;
		String strDomainName = null;
		SqlRowSet srs = null;
		String nextSequence = null;
		boolean bReturn = false;
		strSql = "SELECT nextid FROM sequence WHERE name = 'userid'";
		// check whether customer no already exists
		srs = jdbcTemplate.queryForRowSet(strSql);
		if(srs != null)
		{
			if(srs.next())
			{
				nextSequence = srs.getString(1);
				if(nextSequence==null) nextSequence="";
			}
		}
					
		sql.append("INSERT INTO ACCOUNT ");
		sql.append(" (USERID, EMAIL,FIRSTNAME,LASTNAME,STATUS,ADDR1,ADDR2,CITY,STATE,ZIP,COUNTRY,PHONE, LASTACCESSED) ");
		sql.append(" VALUES ");
		sql.append(" (?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?) ");
	
		Object[] args = new Object[] {nextSequence ,registerUser.getUsername(),registerUser.getFirstname(),registerUser.getLastname(), "ACTIVE",
							registerUser.getAddr1(), registerUser.getAddr2(),registerUser.getCity(),registerUser.getState(),registerUser.getZip(),registerUser.getCountry(),
							registerUser.getPhone(),Util.formatIntoDBTimestamp("Date")};
		
		int rowInserted = jdbcTemplate.update(sql.toString(), args);
		if(rowInserted >0)
		{
			assignRoles(nextSequence,"USER");
			strSql = "INSERT INTO SIGNON VALUES(?,?,?)";
			Object[] args1 = new Object[]{nextSequence,registerUser.getUsername(),registerUser.getPassword()};
			int rowIns = jdbcTemplate.update(strSql, args1);
			if(rowIns > 0)
				configureSaltPassword(registerUser.getUsername(),registerUser.getPassword());
				updateSequence(nextSequence);
		}
		if (rowInserted <= 0)
			throw new ApplicationException("registration.error.user.not.inserted", true);
		bReturn = true;
		
		return bReturn;
	}
	
	private void assignRoles(String userName, String roleName)
	{
		Object[] args = new Object[] {userName, roleName}; 
		int rowUpdated=  jdbcTemplate.update("insert into user_role values (?,?)", args);
		if(rowUpdated <=0)
			throw new ApplicationException("registration.error.user.not.inserted");
	}
	private void configureSaltPassword(String userName,String password)
	{
		UserDetails user =
	          userDetailsService.loadUserByUsername(userName);
		      String encodedPassword =
	          passwordEncoder.encodePassword(password,
	          saltSource.getSalt(user));
	     int rowUpdated=  jdbcTemplate.update("update signon set password = ? where username = ?", encodedPassword, userName);
	}
	
	private void updateSequence(String nextSeq)
	{
		StringBuilder sql = new StringBuilder();
		if(nextSeq == null)
			 nextSeq="";
		int length = nextSeq.length() - 3;
		String temp = nextSeq.substring(3, nextSeq.length());
		long l = Long.valueOf(temp);
		l = l +1;
		String zero = "";
		for (int i = 0; i < length; i++) {
			zero = zero + "0";
		}
		NumberFormat formatter = new DecimalFormat(zero);
		//return nextSeq.substring(0,3)+formatter.format(l);
		nextSeq = nextSeq.substring(0,3)+formatter.format(l);
		System.out.println("--->"+nextSeq.substring(0,3)+formatter.format(l));
		sql.append("UPDATE SEQUENCE SET NEXTID=? WHERE NAME= ? ");
		Object[] args = new Object[] {nextSeq,"USERID"};
		int rowIns = jdbcTemplate.update(sql.toString(), args);
	
	}

}
