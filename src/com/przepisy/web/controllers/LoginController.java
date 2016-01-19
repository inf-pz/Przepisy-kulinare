package com.przepisy.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping("/rejestracja")
	public String showNewAccount(Model model){
		
		model.addAttribute("user", new User());
		return "newaccount";
	}
	
	
	@RequestMapping(value="/nowekonto", method=RequestMethod.POST)
	public String createAccount(Model model, User user) {

		user.setActive(true);
		user.setAuthority("user");
		usersService.createUser(user);
		
		return "accountcreated";
	}
}
