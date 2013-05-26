package com.csc.adaas.eshop.web.controllers;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.csc.adaas.eshop.security.vo.UserProfile;
import com.csc.adaas.eshop.server.common.JspNames;
import com.csc.adaas.eshop.server.dto.ProductDTO;
import com.csc.adaas.eshop.server.services.EshopService;

@Controller
@SessionAttributes({  "firstname", "lastAccessed" })
public class LoginController {
	
	@Autowired(required=false)
	private EshopService eshopService;

	private Logger log = Logger.getLogger(LoginController.class.getName());

	@RequestMapping(value = "/home.htm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView afterLogin(HttpServletRequest request, HttpServletResponse response, ModelMap map, @ModelAttribute("productObj") ProductDTO productDTO,
			BindingResult result) throws Exception {
		log.info("Entering LoginController.onLogin method");
		ModelAndView view = new ModelAndView(JspNames.HOME.getView());
		UserProfile user = null;
		if (SecurityContextHolder.getContext() != null) {
			user = (UserProfile) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			//request.getSession(true);
			log.info("New user session for: " + user.getUsername()
					+ " and session id: " + request.getSession().getId());
		}
		view.addObject("firstname", user.getFirstname());
		view.addObject("lastAccessed", user.getLastAccessed());
		
		List<ProductDTO> productResults = eshopService.getProductDetails();
		Set<String> productNames = new TreeSet<String>();
		map.addAttribute("productObj", productResults);
		
		String productstring;
		StringBuilder productname = new StringBuilder();

		for(ProductDTO product: productResults) {
			productNames.add(product.getName());
		}
		for(String product: productNames) {
			productname = productname.append(new StringBuilder("\"" + product + "\"" +","));			
		}
		productstring = productname.substring(0,(productname.lastIndexOf(",")));
		
		System.out.println("productname: "+productstring);
		map.addAttribute("productName", productstring);
		
		log.info("Exiting LoginController.onLogin method");
		return view;
	}

	@RequestMapping(value = "/login.htm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView getLoginPage(HttpServletRequest request) {
		log.info("Entering LoginController.onLogin method");
		ModelAndView view = new ModelAndView(JspNames.LOGIN.getView());
		log.info("Exiting LoginController.onLogin method");
		return view;
	}

	@RequestMapping(value = "/loginFailure.htm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView loginFailure(HttpServletRequest request,
			@RequestParam("msg") String msg) {
		log.info("Entering LoginController.loginFailure method");
		ModelAndView view = new ModelAndView(JspNames.LOGIN.getView());
		view.addObject("loginFailed", "true");
		view.addObject("error", msg);
		log.info("Exiting LoginController.loginFailure method");
		return view;
	}

	@RequestMapping(value = "/logout.htm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("Entering LoginController.logout method");
		ModelAndView view = new ModelAndView(JspNames.LOGIN.getView());
		HttpSession session = request.getSession();
		if (session != null) {
			log.info("Invalidating Session for session id: ["
					+ session.getId()
					+ "]. Check why Spring Logout Filter did not invalidate session");
			session.invalidate();
		}
		view.addObject("logout", "true");
		log.info("Exiting LoginController.logout method");
		return view;
	}

	@RequestMapping(value = "/accessDenied.htm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView accessDenied(HttpServletRequest request) {
		log.info("Entering LoginController.accessDenied method");
		ModelAndView view = new ModelAndView(JspNames.ACCESS_DENIED.getView());
		view.addObject("accessDenied", "true");
		log.info("Exiting LoginController.accessDenied method");
		return view;
	}
}
