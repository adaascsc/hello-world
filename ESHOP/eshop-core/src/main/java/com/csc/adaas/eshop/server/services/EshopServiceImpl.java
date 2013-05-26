/**
 * 
 */
package com.csc.adaas.eshop.server.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation; 

import com.csc.adaas.eshop.server.common.ApplicationException;
import com.csc.adaas.eshop.server.dao.ProductDao;
import com.csc.adaas.eshop.server.dto.ProductDTO;


/**
 * @author CSC
 * 
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { DataAccessException.class,
		ApplicationException.class }) 
public class EshopServiceImpl implements EshopService {
	
	@Autowired(required=false)
	private ProductDao productDao  ;
	
	@Autowired(required=false)
	public List<ProductDTO> getProductDetails() {
		// TODO Auto-generated method stub
		return productDao.getProductDetails();
	}
	
	public List<ProductDTO> getProductDetails(String name) {
		// TODO Auto-generated method stub
		return productDao.getProductDetails(name);
	}
	
	@Override
	public List<ProductDTO> getCategory(String name) {
		// TODO Auto-generated method stub
		return productDao.getCategory(name);
	}
	
}
