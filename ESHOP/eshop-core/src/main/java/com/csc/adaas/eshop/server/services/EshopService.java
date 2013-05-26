/**
 * 
 */
package com.csc.adaas.eshop.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.csc.adaas.eshop.server.dto.ProductDTO;



/**
 * @author CSC
 *
 */
public interface EshopService {
	
	public List<ProductDTO> getProductDetails (String productName);

	public List<ProductDTO> getProductDetails ();
	
	public List<ProductDTO> getCategory(String name);
	
}
