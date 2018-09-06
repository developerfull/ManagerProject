package com.cesarschool.ManagerProject.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cesarschool.ManagerProject.model.Conta;
import com.cesarschool.ManagerProject.model.Login;
import com.cesarschool.ManagerProject.repository.ContaRepository;

@Controller
public class LoginController {

	@Autowired
	ContaRepository contaRepository;
	
	//Mapeamento de Requisição do Login
	@RequestMapping(value="conta/login", method=RequestMethod.GET)
	public String getLogin() {
		
		return "conta/login";
	}
	
	//Transação da tela de Login
	@RequestMapping(value="conta/login", method=RequestMethod.POST)
	public String login(Login login, Model model) {
		
		String username = login.getUsername();
		String password = login.getPassword();
		
		Optional<Conta> id = contaRepository.findById(username);
		
		 
		if(id.isPresent() && id.get().getSenha().equals( login.getPassword())) {

			return "redirect:/projetos";
		}
		
		model.addAttribute("invalidCredentials",true);

		return "conta/login";
		
	}
	
}
