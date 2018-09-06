package com.cesarschool.ManagerProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cesarschool.ManagerProject.model.Conta;
import com.cesarschool.ManagerProject.model.Membro;
import com.cesarschool.ManagerProject.model.Projeto;
import com.cesarschool.ManagerProject.repository.ContaRepository;

@Controller
public class ContaController {

	@Autowired
	private ContaRepository cr;
	
	@RequestMapping("conta/conta")
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
		
		
		//Requisição de Lista de Contas Cadastradas 
		@RequestMapping("/listaconta")
		public ModelAndView listaProjetos() {
			ModelAndView mv = new ModelAndView("conta/listaConta");
			Iterable<Conta> contas = cr.findAll();
			mv.addObject("contas", contas);
			return mv; 
		}
		
		@RequestMapping("/deletarConta")
		public String deletarConta(String nome) {
			Conta conta = cr.findByNome(nome);
			cr.delete(conta);
			
			return "redirect:/listaconta";
		}
		
	
	
}
