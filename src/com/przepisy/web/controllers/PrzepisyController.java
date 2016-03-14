package com.przepisy.web.controllers;

import java.security.Principal;
import java.sql.Blob;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.przepisy.web.dao.Przepis;
import com.przepisy.web.dao.User;
import com.przepisy.web.service.PrzepisyService;
import com.przepisy.web.service.UsersService;

@Controller
public class PrzepisyController {

	private static Logger logger = Logger.getLogger(HomeController.class);
	
	private PrzepisyService przepisyService;
	private UsersService usersService;

	@Autowired
	public void setPrzepisyService(PrzepisyService przepisyService) {
		this.przepisyService = przepisyService;
	}
	
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/przepisy")
	public String showPrzepisy(Model model) {

		List<Przepis> przepisy = przepisyService.getPrzepisy();

		model.addAttribute("przepisy", przepisy);

		return "przepisy";
	}
	
	@RequestMapping(value = "/przepis", method = RequestMethod.GET)
	public String showPrzepis(@RequestParam("id") int id, Model model) {
		Przepis przepis = przepisyService.getPrzepis(id);
		model.addAttribute("przepis", przepis);
		return "przepis";
		
	}

	@RequestMapping("/nowyprzepis")
	public String createPrzepis(Model model) {

		model.addAttribute("przepis", new Przepis());
		return "createprzepis";
	}

	/*
	boolean tryParseInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	*/

	boolean validate(Przepis przepis) {
		//String s = Integer.toString(przepis.getMember_id()) + "";
		if (przepis.getName().length() == 0 || przepis.getText().length() == 0
				//|| tryParseInt(s) == false || s.length() == 0 || s == null
				){
			return false;
		} else
		return true;
	}
	
	
	

	@RequestMapping(value = "/docreateprzepis", headers = "content-type=multipart/*", method = RequestMethod.POST)
	public String doCreatePrzepis(Model model, Przepis przepis, BindingResult result,  Principal principal, 
			@RequestParam(value = "image", required = false) Blob image) {
		
		przepis.setUser(usersService.findUser(principal.getName()));
		
		if (!(image == null)) {
			try {
			validateImage(image);
			 
			} catch (RuntimeException re) {
			result.reject(re.getMessage());
			return "createprzepis";
			}
		}
			
		if (validate(przepis) == false)
			return "createprzepiserror";
		else {
			przepisyService.createPrzepis(przepis);
			return "przepisdodany";
		}
	}
	
	private void validateImage(Blob image) {
		// TODO Auto-generated method stub
		
	}

	@RequestMapping("/mojeprzepisy")
	public String userPrzepisy(Model model, Principal principal){
		String username = principal.getName();
		List<Przepis> przepisy = przepisyService.getPrzepisy(username);
		model.addAttribute("przepisy", przepisy);
		return "accountprzepisy";
	}
}
