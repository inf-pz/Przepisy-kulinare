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

	@RequestMapping(value = "/docreateprzepis", method = RequestMethod.POST)
	public String doCreatePrzepis(Model model, Przepis przepis) {
		if (validate(przepis) == false)
			return "createprzepiserror";
		else {
			przepisyService.createPrzepis(przepis);
			return "przepisdodany";
		}
	}
}
