package com.csc.adaas.eshop.server.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.csc.adaas.eshop.server.dto.ProductDTO;

/**
 * 
 * @author CSC
 * 
 */
@Repository
public class ProductDaoImpl implements ProductDao {

	private Logger log = Logger.getLogger(ProductDaoImpl.class.getName());
	private static final String NONE_USR = "NONE";
	
	/** JDBC Template instance */
	private JdbcTemplate jdbcTemplate;
	
	@Autowired(required=false)
	public void setSecurityDataSource (DataSource securityDataSource) {
		this.jdbcTemplate = new JdbcTemplate(securityDataSource);
	}
	
	@Override
	public List<ProductDTO> getProductDetails() {

		StringBuilder sql = new StringBuilder();
				
		sql.append("SELECT P.CATEGORY, P.NAME, P.DESCN,I.LISTPRICE,I.ATTR1,I.ATTR2 ");
		sql.append("FROM PRODUCT P, ITEM I ");
		sql.append("WHERE P.PRODUCTID = I.PRODUCTID ");
		
		List<ProductDTO> productList = jdbcTemplate.query(
				sql.toString(), new ProductRowMapper());
				
		return productList;
	}
	
	@Override
	public List<ProductDTO> getProductDetails(String name) {

		StringBuilder sql = new StringBuilder();
				
		sql.append("SELECT P.CATEGORY, P.NAME, P.DESCN,I.LISTPRICE,I.ATTR1,I.ATTR2 ");
		sql.append("FROM PRODUCT P, ITEM I ");
		sql.append("WHERE P.PRODUCTID = I.PRODUCTID AND P.NAME = '"+name + "'");
		
		log.info("sql string: "+sql.toString());
		List<ProductDTO> productList = jdbcTemplate.query(
				sql.toString(), new ProductRowMapper());
				
		return productList;
	}
	
	
	@Override
	public List<ProductDTO> getCategory(String name) {

		StringBuilder sql = new StringBuilder();
				
		sql.append("SELECT P.CATEGORY, P.NAME, P.DESCN,I.LISTPRICE,I.ATTR1,I.ATTR2 ");
		sql.append("FROM PRODUCT P, ITEM I ");
		sql.append("WHERE P.PRODUCTID = I.PRODUCTID AND P.CATEGORY = '"+name + "'");
		
		log.info("sql string: "+sql.toString());
		List<ProductDTO> productList = jdbcTemplate.query(
				sql.toString(), new ProductRowMapper());
				
		return productList;
	}
}
