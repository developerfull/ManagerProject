package com.cesarschool.ManagerProject.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesarschool.ManagerProject.model.Calendario;
import com.cesarschool.ManagerProject.model.Conta;
import com.cesarschool.ManagerProject.repository.ContaRepository;

@Controller
public class ContaController {
	@Autowired
	private ContaRepository cr;

	@PutMapping(value="/api/cadastrarconta", produces = "application/json")
	@ResponseBody
	public Conta salvarConta(@RequestBody Conta conta) {
		return cr.save(conta);
	}
	
	@PutMapping(value="/api/deletarconta/{id}", produces = "application/json")
	public void deletarContaAPI(@RequestBody String id) {
		cr.deleteById(id);
	}
	
	@GetMapping(value="/api/conta/{id}", produces = "application/json")
	public Conta listarContaAPI(@PathVariable String id) {
		Optional<Conta> conta = cr.findById(id);
		return conta.isPresent() ? conta.get() : null;	
	}
	
	@GetMapping(value="/api/conta", produces = "application/json")
	public Iterable<Conta> listarContaAPI() {
		return cr.findAll();
	}
	
	
	
	@RequestMapping("/conta")
	public String conta() {
		return "conta/conta";
	}

	// Inicio de Requisição e Redericionamento para página de Cadastro
	@RequestMapping(value = "/conta", method = RequestMethod.GET)
	public String form() {
		return "conta/conta";
	}

	// Envio da Requisição e Registro das informações no BD
	@RequestMapping(value = "/conta", method = RequestMethod.POST)
	public String form(Conta conta) {

		cr.save(conta);
		return "redirect:conta/login";
	}
}