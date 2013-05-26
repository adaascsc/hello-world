package com.csc.adaas.eshop.web.controllers;



import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csc.adaas.eshop.security.vo.UserProfile;
import com.csc.adaas.eshop.server.common.EShopConstants;
import com.csc.adaas.eshop.server.common.JspNames;
import com.csc.adaas.eshop.server.dto.ProductDTO;
import com.csc.adaas.eshop.server.services.EshopService;
@Controller
public class HomeController {
	private Logger log = Logger.getLogger(HomeController.class.getName());


	@Autowired(required=false)
	private EshopService eshopService;
	
	
	//added by sem start
	
		
	//aded by sem ends
	
	/*
	@RequestMapping(value = "/twitterConnect.htm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView getTwitter(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView(JspNames.TWITTERCONNECTED.getView());
		
		return view;
	}
*/
	@RequestMapping(value = "/search.htm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response, ModelMap map, @ModelAttribute("productObj") ProductDTO productDTO,
			BindingResult result) throws Exception {
		log.info("Entering HomeController.search method");
		ModelAndView view = new ModelAndView(JspNames.SEARCH.getView());
		
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

		log.info("Exiting HomeController.search method");
		return view;
	}
	
	@RequestMapping(value = "/searchResult.htm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView searchResult(ModelMap map, @ModelAttribute("productObj") ProductDTO productDTO,
			BindingResult result) throws Exception {
		System.out.println("In searchResult" );
		log.info("Entering HomeController.searchResult method");
		log.info("productDTO: "+productDTO.getName());
		ModelAndView view = new ModelAndView(JspNames.SEARCHRESULT.getView());
		
		List<ProductDTO> productSearchResults = eshopService.getProductDetails(productDTO.getName());
		map.addAttribute("productSearchObj", productSearchResults);
		
		for(ProductDTO product: productSearchResults) {
			System.out.println("Category in productSearchResults: "+product.getCategory());
			System.out.println("Description: "+product.getDescription());
			System.out.println("Name: "+product.getName());
			System.out.println("Price: "+product.getPrice());
			
		}
		
		String productstring;
		StringBuilder productname = new StringBuilder();
		
		List<ProductDTO> productResults = eshopService.getProductDetails();
		Set<String> productNames = new TreeSet<String>();
		map.addAttribute("productObj", productResults);
		
		for(ProductDTO product: productResults) {
			productNames.add(product.getName());
		}
		
		for(String product: productNames) {
			productname = productname.append(new StringBuilder("\"" + product + "\"" +","));			
		}
		productstring = productname.substring(0,(productname.lastIndexOf(",")));
		
		System.out.println("productname: "+productstring);
		map.addAttribute("productName", productstring);

		log.info("Exiting HomeController.searchResult method");
		return view;
	}
	
	@RequestMapping(value = "/books.htm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView getBooks(HttpServletRequest request, HttpServletResponse response, ModelMap map, @ModelAttribute("productObj") ProductDTO productDTO,
			BindingResult result) throws Exception {
		log.info("Entering HomeController.getBooks method");
		ModelAndView view = new ModelAndView(JspNames.SEARCHRESULT.getView());
		
		List<ProductDTO> productSearchResults = eshopService.getCategory(EShopConstants.BOOKS);
		map.addAttribute("productSearchObj", productSearchResults);
		
		for(ProductDTO product: productSearchResults) {
			System.out.println("Category in productSearchResults: "+product.getCategory());
			System.out.println("Description: "+product.getDescription());
			System.out.println("Name: "+product.getName());
			System.out.println("Price: "+product.getPrice());
			
		}
			
		return view;
	}
	@RequestMapping(value = "/music.htm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView getMusic(HttpServletRequest request, HttpServletResponse response, ModelMap map, @ModelAttribute("productObj") ProductDTO productDTO,
			BindingResult result) throws Exception {
		log.info("Entering HomeController.getMobiles method");
		ModelAndView view = new ModelAndView(JspNames.SEARCHRESULT.getView());
		
		List<ProductDTO> productSearchResults = eshopService.getCategory(EShopConstants.MUSIC);
		map.addAttribute("productSearchObj", productSearchResults);
							
		return view;
	}
	
	@RequestMapping(value = "/mobile.htm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView getMobiles(HttpServletRequest request, HttpServletResponse response, ModelMap map, @ModelAttribute("productObj") ProductDTO productDTO,
			BindingResult result) throws Exception {
		log.info("Entering HomeController.getMobiles method");
		ModelAndView view = new ModelAndView(JspNames.SEARCHRESULT.getView());
		
		List<ProductDTO> productSearchResults = eshopService.getCategory(EShopConstants.MOBILES);
		map.addAttribute("productSearchObj", productSearchResults);
							
		return view;
	}
	
	@RequestMapping(value = "/sunglass.htm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView getGlasses(HttpServletRequest request, HttpServletResponse response, ModelMap map, @ModelAttribute("productObj") ProductDTO productDTO,
			BindingResult result) throws Exception {
		log.info("Entering HomeController.getGlasses method");
		ModelAndView view = new ModelAndView(JspNames.SEARCHRESULT.getView());
		
		List<ProductDTO> productSearchResults = eshopService.getCategory(EShopConstants.SUNGLASSES);
		map.addAttribute("productSearchObj", productSearchResults);
							
		return view;
	}
	@RequestMapping(value = "/watch.htm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView getWatches(HttpServletRequest request, HttpServletResponse response, ModelMap map, @ModelAttribute("productObj") ProductDTO productDTO,
			BindingResult result) throws Exception {
		log.info("Entering HomeController.getWatches method");
		ModelAndView view = new ModelAndView(JspNames.SEARCHRESULT.getView());
		
		List<ProductDTO> productSearchResults = eshopService.getCategory(EShopConstants.WATCHES);
		map.addAttribute("productSearchObj", productSearchResults);
							
		return view;
	}
	
	@RequestMapping(value = "/camera.htm", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView getCamera(HttpServletRequest request, HttpServletResponse response, ModelMap map, @ModelAttribute("productObj") ProductDTO productDTO,
			BindingResult result) throws Exception {
		log.info("Entering HomeController.gecamera method");
		ModelAndView view = new ModelAndView(JspNames.SEARCHRESULT.getView());
		
		List<ProductDTO> productSearchResults = eshopService.getCategory(EShopConstants.CAMERAS);
		map.addAttribute("productSearchObj", productSearchResults);
							
		return view;
	}
	
	
	@RequestMapping(value = "/twitterConnect.htm", method = { RequestMethod.GET,	RequestMethod.POST })
	public ModelAndView home(Principal currentUser, Model model) {
				//model.addAttribute(accountRepository.findAccountByUsername(currentUser.getName()));
		//ModelAndView view = new ModelAndView(JspNames.TWITTERCONNECTED.getView());
		ModelAndView view = new ModelAndView(JspNames.TWITTERCONNECT.getView());
		
		return view;
	}
}
