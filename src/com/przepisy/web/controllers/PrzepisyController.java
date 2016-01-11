package com.przepisy.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.przepisy.web.dao.Przepis;
import com.przepisy.web.service.PrzepisyService;

@Controller
public class PrzepisyController {
	
	private PrzepisyService przepisyService;
	
	@Autowired
	public void setPrzepisyService(PrzepisyService przepisyService) {
		this.przepisyService = przepisyService;
	}

	@RequestMapping("/przepisy")
	public String showPrzepisy(Model model) {
			
		List<Przepis> przepisy = przepisyService.getPrzepisy();
		
		model.addAttribute("przepisy", przepisy);
		
		return "przepisy";
	}
	
	@RequestMapping("/nowyprzepis")
	public String createPrzepis() {
				
		return "createprzepis";
	}

	@RequestMapping(value="/docreateprzepis", method=RequestMethod.POST)
	public String doCreatePrzepis(Model model, Przepis przepis) {

		przepisyService.createPrzepis(przepis);
		
		return "przepisdodany";
	}
}
