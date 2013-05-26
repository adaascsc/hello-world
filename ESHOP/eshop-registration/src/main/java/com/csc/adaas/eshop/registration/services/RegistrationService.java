package com.csc.adaas.eshop.registration.services;

import java.util.List;


import com.csc.adaas.eshop.registration.dto.UserRegistrationDTO;

public interface RegistrationService {

	
	public boolean registerNewUser (UserRegistrationDTO registerUser);
	
}
