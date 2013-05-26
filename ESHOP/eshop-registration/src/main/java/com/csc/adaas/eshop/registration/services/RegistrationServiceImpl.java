/**
 * 
 */
package com.csc.adaas.eshop.registration.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csc.adaas.eshop.registration.dao.UserRegistrationDAO;
import com.csc.adaas.eshop.registration.dto.UserRegistrationDTO;
import com.csc.adaas.eshop.registration.common.ApplicationException;


/**
 * @author Harini
 *
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { DataAccessException.class,
		ApplicationException.class })

public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired (required= false)
	private UserRegistrationDAO registrationDAO  ;

	

	@Override
	public boolean registerNewUser(UserRegistrationDTO registerUser) {
		
		return registrationDAO.registerNewUser(registerUser);
	}

	

}
