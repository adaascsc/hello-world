package com.csc.adaas.eshop.server.dao;

import java.util.List;

import com.csc.adaas.eshop.server.dto.ProductDTO;


public interface ProductDao {
	
	public List<ProductDTO> getProductDetails();
	public List<ProductDTO> getProductDetails(String name);
	public List<ProductDTO> getCategory(String name);
	
}
