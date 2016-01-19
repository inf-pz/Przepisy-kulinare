package com.przepisy.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.przepisy.web.dao.User;
import com.przepisy.web.service.UsersService;

@Controller
public class LoginController {

	private UsersService usersService;
	
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	@RequestMapping("/login")
	public String showLogin(){
		return "login";
	}
	
	@RequestMapping("/logout")
	public String showLoggout(){
		return "loggedout";
	}
	
	@RequestMapping("/rejestracja")
	public String showNewAccount(Model model){
		
		model.addAttribute("user", new User());
		return "newaccount";
	}
	
	
	@RequestMapping(value="/nowekonto", method=RequestMethod.POST)
	public String createAccount(@Valid User user, BindingResult result) {

		if(result.hasErrors()){
			return "newaccount";
		}
		
		user.setActive(true);
		user.setAuthority("user");
		
		if(usersService.exist(user.getLogin())){
			result.rejectValue("login", "DuplicateKey.user.login", "Podany login jest już zajęty");
			return "newaccount";			
		}
		
		try {
		usersService.createUser(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("login", "DuplicateKey.user.login", "Podany login jest już zajęty");
			return "newaccount";
		}

		
		return "accountcreated";
	}
}
