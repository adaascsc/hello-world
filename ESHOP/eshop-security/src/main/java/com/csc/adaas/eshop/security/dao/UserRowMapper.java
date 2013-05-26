package com.csc.adaas.eshop.security.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csc.adaas.eshop.security.vo.AuthenticateUser;

public class UserRowMapper implements RowMapper {
	
	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		AuthenticateUser user = new AuthenticateUser();
		user.setUserid(rs.getString(1));
		user.setUsername(rs.getString(2));
		user.setPassword(rs.getString(3));
		user.setFirstname(rs.getString(4));
		user.setStatus(rs.getString(5));
		user.setLastAccessed(rs.getTimestamp(6));
		return user;
	}
}
