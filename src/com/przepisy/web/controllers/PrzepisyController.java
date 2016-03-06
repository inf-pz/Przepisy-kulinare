package com.przepisy.web.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	
	private void validateImage(MultipartFile image) {
		if (!image.getContentType().equals("image/jpeg")) {
		throw new RuntimeException("Format jpg jest wymagany");
		}
		}
	
	

	@RequestMapping(value = "/docreateprzepis", headers = "content-type=multipart/*", method = RequestMethod.POST)
	public String doCreatePrzepis(Model model, Przepis przepis, BindingResult result,  Principal principal, 
			@RequestParam(value = "image", required = false) MultipartFile image) {
		String username = principal.getName();
		przepis.setUsername(username);
		
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
			przepis.setPhoto(image);
			przepisyService.createPrzepis(przepis);
			return "przepisdodany";
		}
	}
	
	@RequestMapping("/mojeprzepisy")
	public String userPrzepisy(Model model, Principal principal){
		String username = principal.getName();
		List<Przepis> przepisy = przepisyService.getPrzepisy(username);
		model.addAttribute("przepisy", przepisy);
		return "accountprzepisy";
	}
}
