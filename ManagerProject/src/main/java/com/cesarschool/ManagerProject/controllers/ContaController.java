package com.cesarschool.ManagerProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cesarschool.ManagerProject.model.Conta;
import com.cesarschool.ManagerProject.repository.ContaRepository;

@Controller
public class ContaController {

	@Autowired
	private ContaRepository cr;
	
	@RequestMapping("/conta")
	public String conta() {
		return "conta/conta";
	}

	// Inicio de Requisição e Redericionamento para página de Cadastro
		@RequestMapping(value="/conta", method=RequestMethod.GET)
		public String form() {
			return "conta/conta";
		}
		
		// Envio da Requisição e Registro das informações no BD 
		@RequestMapping(value="/conta", method=RequestMethod.POST)
		public String form(Conta conta) {
			
			cr.save(conta);
			return "redirect:conta/login";
		}
		
	
	
	
}
