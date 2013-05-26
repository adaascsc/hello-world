package com.csc.adaas.eshop.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.csc.adaas.eshop.server.dto.ProductDTO;

public class ProductRowMapper implements RowMapper<ProductDTO> {
	
	public ProductDTO mapRow(ResultSet rs, int rowNo) throws SQLException {
		ProductDTO product = new ProductDTO();
		product.setCategory(rs.getString(1));
		product.setName(rs.getString(2));
		product.setDescription(rs.getString(3));
		product.setPrice(rs.getDouble(4));
		String attr1 = (String) rs.getString(5);
		if(attr1 != null)
		{
			product.setAttr1(rs.getString(5));
		}
		else product.setAttr1("");
		
		String attr2 = (String) rs.getString(6);
		if(attr2 != null)
		{
			product.setAttr2(rs.getString(6));
		}
		else product.setAttr2("");
	
		return product;
	}
}

