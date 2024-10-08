package com.springweb.SpringBootSecurityMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home(Model model) {
	    model.addAttribute("webTech", "Spring Boot 3.x Security MVC");
	    model.addAttribute("projectName", "Simple shopping cart with security 6.x");
	    return "home";
	}
}
