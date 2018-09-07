	package com.cesarschool.ManagerProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	// Carregando a index...
	@RequestMapping("/")
	public String index() {
		return "redirect:/conta/login";
	}
}
