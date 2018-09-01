package com.cesarschool.ManagerProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MembroController {

	@RequestMapping("/pessoas")
	public String membro() {
		return "membro/pessoas";
	}
	

	
	
}
