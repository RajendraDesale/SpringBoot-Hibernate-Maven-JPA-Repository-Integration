package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.info.InfoPropertiesInfoContributor.Mode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.JpaRepository.LoginJpaRepository;
import com.example.JpaRepository.PersonJpaRepository;
import com.example.model.PersonModel;

@Controller
public class LoginController {

	@Autowired
	LoginJpaRepository jparepository;
	
	/*
	 * @Autowired(required = true)
	 * 
	 * @Qualifier(value = "loginService") public void setLoginService(LoginService
	 * loginService) { this.loginService = loginService; }
	 */
	
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public String RedirectPage(ModelMap model) {
		model.addAttribute("person", new PersonModel());
		return "person";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, ModelMap model) {
		String errorMessge = null;
		if (error != null) {
			errorMessge = "Username or Password is incorrect !!";
		}
		if (logout != null) {
			errorMessge = "You have been successfully logged out !!";
		}
		model.addAttribute("errorMessge", errorMessge);
		return "login";
	}
	
	@RequestMapping(value = "/loginvalidation", method = RequestMethod.POST)
	public String loginValidation(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password, ModelMap model) {
		System.out.println(username + " " + password);
		
		String errorMessge = null;
		if (username == "" || password == "") {
			errorMessge = "Please enter required fields !!";
			model.addAttribute("errorMessge", errorMessge);
		} else {
			if(jparepository.validateuser(username, password)) {
				model.addAttribute("person", new PersonModel());
				return "person";
			} else {
				errorMessge = "Username or Password is incorrect !!";
				model.addAttribute("errorMessge", errorMessge);
			}
		}
		
		//model.addAttribute("person", new PersonModel());
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String Logout(ModelMap model) {
		
		return "login";
	}
}
