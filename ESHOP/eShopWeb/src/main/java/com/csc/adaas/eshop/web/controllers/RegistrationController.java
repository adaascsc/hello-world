package com.csc.adaas.eshop.web.controllers;


import static com.csc.adaas.eshop.registration.common.RegistrationConstants.SPACE;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.csc.adaas.eshop.registration.common.ApplicationException;

import com.csc.adaas.eshop.registration.common.JspNames;
import com.csc.adaas.eshop.registration.common.Message;
import com.csc.adaas.eshop.registration.common.RegistrationConstants;
import com.csc.adaas.eshop.registration.common.States;
import com.csc.adaas.eshop.registration.dto.UserRegistrationDTO;
import com.csc.adaas.eshop.registration.services.RegistrationService;
//import com.csc.ace.sqlfire.server.common.Gender;




@Controller
@SessionAttributes( {"userObj"})
public class RegistrationController {

	@Autowired (required= false)
	private RegistrationService registrationService; 
	
	//private SitePreference preference;
	
	private Logger log = Logger.getLogger(RegistrationController.class.getName());
	
	private Map<String, String> stateMap;
	
	
	
	@ModelAttribute("stateMap")
	public Map<String, String> getStatesMap() {
		if (stateMap == null) {
			stateMap = new LinkedHashMap<String, String>();
			States[] states =States.values();
			for (int i = 0; i < states.length; i++) {
				States stateList = states[i];
				stateMap.put(stateList.getKey(), stateList.getValue());
			}
		}
		return stateMap;
	}
	
	@RequestMapping(value = "/registerUser.htm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView showUserRegistration(ModelMap map) throws Exception {
				
		ModelAndView view = new ModelAndView(JspNames.REGISTRATION.getView());
		UserRegistrationDTO registrationDTO = new UserRegistrationDTO();
		map.addAttribute("registerUserObj", registrationDTO);
		return view;
	}	
	  	
	
	
				
	@RequestMapping(value = "/registerNewUser.htm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView registerNewUser(ModelMap map, @ModelAttribute("registerUserObj") UserRegistrationDTO userDTO,
			BindingResult result) throws Exception {
		try {
			userDTO.setAction(RegistrationConstants.CREATE);
			System.out.println("user dto details---"+userDTO.getCountry());	
			userDTO.setUserStatus("ACTIVE");
			registrationService.registerNewUser(userDTO);
			Message msg = new Message("registration.info.user.created");
			map.addAttribute("infoMsg", msg);
    	} catch (Exception e) {
			if (e instanceof ApplicationException) {
				if (((ApplicationException) e).isErrorCodeGiven())
					result.reject(((ApplicationException) e).getErrorCode(), null, SPACE);
			}
		}
    	 //model.addAttribute("notification", "Successfully did it!");
        // return new ModelAndView("redirect:/login.htm");
    	map.addAttribute("registerUserObj", userDTO);
		
		return new ModelAndView(JspNames.REGISTRATION.getView());
	}
	
	}
