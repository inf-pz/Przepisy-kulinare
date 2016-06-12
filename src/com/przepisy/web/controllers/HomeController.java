package com.przepisy.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String showHome() {
		return "home";
	}
	 @RequestMapping("favicon.ico")
     String favicon() {
         return "forward:/resources/images/favicon.ico";
     }
}
