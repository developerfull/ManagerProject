package com.cesarschool.ManagerProject.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cesarschool.ManagerProject.model.Membro;
import com.cesarschool.ManagerProject.model.Projeto;
import com.cesarschool.ManagerProject.repository.MembroRepository;

@Controller
public class MembroController {

	MembroRepository mr;
	
	// Carregando a tela de cadastro de membro...
	@RequestMapping("/pessoas")
	public String membro() {
		return "membro/pessoas";
	}
	
	//API
	
	/*@GetMapping(value="/api/membros/{id}")
	@ResponseBody	
	public Membro carregarMembroAPI(@PathVariable String cpf) {
		Optional<Membro> membro = mr.findByCpf(cpf);
;		return membro.isPresent() ? membro.get() : null;
	}
	
	@GetMapping(value="/api/membros")
	@ResponseBody
	public Iterable<Membro> carregarMebroAPI() {
		return mr.findAll();
	}
	
	@PutMapping(value="/api/deletarmembro/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deletarProjetoAPI(@PathVariable long id) {
		mr.deleteById(id);
	}
	*/
	
}
