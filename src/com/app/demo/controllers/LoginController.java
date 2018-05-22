package com.app.demo.controllers;


import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.demo.pojo.CompanyPojo;
import com.app.demo.service.CompanyService;




@Controller
public class LoginController {
	
	
	@Autowired
	@Qualifier("dao_based_service")
	private CompanyService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginForm(Model map) {
		// Model --- map of model attributes
		System.out.println("in show login form " + map);
		// create empty DTO(model) instance & add it modl atter map
		// Model API --- public void addAttribute(String attrName,Object val);
		map.addAttribute("compModel", new CompanyPojo());
		return "login";
	}


	
	// method for processing login form
		@RequestMapping(value = "/login", method = RequestMethod.POST)
		public String processLoginForm(
				@Valid @ModelAttribute("compModel") CompanyPojo c, BindingResult res,
				Model map, HttpSession hs) {
			System.out.println("in process login form " + c);
			// chk for p.l errs
			if (res.hasFieldErrors("email") || res.hasFieldErrors("pass")) {
				// p.l errs
				System.out.println("P.L errors");
				return "login";
			}
			System.out.println("no p .l errs");
			// use service layer bean for validation
			CompanyPojo details = service.validateCompany(c.getcUserName(), c.getcPassword());
			if (details == null)
				return "invalid";
			// successful B.L validations
			// add validated company details to model attr map
			map.addAttribute("valid_comp", details);
			map.addAttribute("status", "Logged in Successfully");
			System.out.println("login " + hs.getId());
			// add validated user dtls to session scope
			hs.setAttribute("valid_comp", details);
			return "list";
		}
} 