package com.cesarschool.ManagerProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cesarschool.ManagerProject.model.Login;

@Controller
public class LoginController {

	@RequestMapping(value="conta/login", method=RequestMethod.GET)
	public String getLogin() {
		
		return "conta/login";
	}
	
	@RequestMapping(value="conta/login", method=RequestMethod.POST)
	public String login(@ModelAttribute(name="login") Login login, Model model) {
		
		String username = login.getUsername();
		String password = login.getPassword();
		
		if("admin".equals(username) && "admin".equals(password)) {
			return "redirect:/projetos";
		}
		
		model.addAttribute("invalidCredentials",true);

		return "conta/login";
		
	}
	
}
