package com.app.demo.controllers;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.demo.pojo.CompanyPojo;
import com.app.demo.service.CompanyService;
import com.app.demo.service.CompanyServiceDaoImp;

@Controller

public class CompanyController {
	/*@RequestMapping(value="/register_company.jsp" , method = RequestMethod.GET)
	public ModelAndView getRegisterCompany(){
		ModelAndView model = new ModelAndView("register_company");
		return model;
	}*/
	@Autowired
	@Qualifier("dao_based_service")
	private CompanyService service;
	
	
	// show registration form for new customer : 
	
			@RequestMapping(value = "/register", method = RequestMethod.GET)
			public String showRegForm( Model map) {
				System.out.println("show reg form " );
				// invoke service layer method to add company details
				map.addAttribute("compModel", new CompanyPojo());
				return "register";
			}

	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegisterForm(
			@Valid @ModelAttribute("compModel") CompanyPojo com,
			BindingResult res, HttpSession hs, 
			RedirectAttributes attr) {
		System.out.println("in process reg form " + com);
		if (res.hasErrors()) {
			System.out.println("register P.L errs ");
			return "register";
		}
		System.out.println("register : no p.l errs");
		try {
			// invoke service layer method for updation
		//	service.addCompany(com);
			hs.setAttribute("valid_comp", service.addCompany(com));
			// added flash attribute
			System.out.println("after set attribute");
			attr.addAttribute("status", "Registered Successfully");
			System.out.println("in try");
			// redirect after post pattern -- redirected to company details page.
			return "list";
		} catch (Exception e) {
			e.printStackTrace();
			res.rejectValue("cUserName", "cPassword", e.getMessage());
			return "register";
		}
		
	}
	
	@RequestMapping("/list")
	public String listCustomers(Model map) {
		List<CompanyPojo> l1 = service.getCompanies();
		System.out.println("in list controller " + l1);
		map.addAttribute("comp_list", l1);
		return "list";
	}
	
	
	// show form for updating selected customer : pre populated with customer
		// data
		@RequestMapping(value = "/update/{cUserName}", method = RequestMethod.GET)
		public String showUpdateForm(@PathVariable String cUserName, Model map) {
			System.out.println("update controller " + cUserName);
			// invoke service layer method to get cust details
			map.addAttribute("compModel", service.getCompanyByUserName(cUserName));
			return "update";
		}


		// process update form

		@RequestMapping(value = "/update/{cUserName}", method = RequestMethod.POST)
		public String processUpdateForm(
				@Valid @ModelAttribute("compModel") CompanyPojo comp,
				BindingResult res, HttpSession hs, Model map,
				RedirectAttributes attr) {
			System.out.println("in process update form " + comp);
			if (res.hasErrors()) {
				System.out.println("update P.L errs ");
				return "update";
			}
			System.out.println("update : no p.l errs");
			try {
				// invoke service layer method for updation
				hs.setAttribute("valid_comp", service.updateCompany(comp));
				// added flash attribute
				attr.addAttribute("status", "Updated Successfully");
				// redirect after post pattern
				return "redirect:/list";
			} catch (Exception e) {
				e.printStackTrace();
				//res.rejectValue("email", "code", e.getMessage());
				return "update";
			}

			/*
			 * System.out.println("process_update " + cust);
			 * service.updateCustomer(cust); // redirect after post pattern return
			 * "redirect:/cust/list";
			 */
		}


		// un subscribe customer & after redirection take user back to cusotmer list
		@RequestMapping(value = "/delete/{cUserName}", method = RequestMethod.GET)
		public String deleteCustomer(@PathVariable String cUserName,
				RedirectAttributes attr) {
			System.out.println("delete controller " + cUserName);
			// invoke service layer method to delete cust details
			try {
				String sts = service.deleteCompany(cUserName);
				attr.addAttribute("status", sts);
			} catch (Exception e) {
				System.out.println("err in delete controller " + e);
				attr.addAttribute("status",
						"Customer unsubscription failed : " + e.getMessage());
			}
			return "redirect:/list";
		}

}
