package com.csc.adaas.eshop.registration.dao;

import java.util.List;
import java.util.Map;

import com.csc.adaas.eshop.registration.dto.UserRegistrationDTO;




public interface UserRegistrationDAO {
	
	public boolean registerNewUser (UserRegistrationDTO registerUser);
	
	
}
