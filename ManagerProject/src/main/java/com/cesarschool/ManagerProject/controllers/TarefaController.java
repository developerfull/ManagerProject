package com.cesarschool.ManagerProject.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cesarschool.ManagerProject.model.Membro;
import com.cesarschool.ManagerProject.model.Projeto;
import com.cesarschool.ManagerProject.model.Tarefa;
import com.cesarschool.ManagerProject.repository.MembroRepository;
import com.cesarschool.ManagerProject.repository.ProjetoRepository;
import com.cesarschool.ManagerProject.repository.TarefaRepository;




@Controller
public class TarefaController {

	@Autowired
	private ProjetoRepository projetoRepository;
	
	@Autowired
	private MembroRepository membroRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@RequestMapping("/tarefa")
	public String tarefa() {
		return "tarefa/formTarefa";
	}
	
	// Iniciar cadastro de tarefa...
	@RequestMapping(value="/cadastrartarefa", method=RequestMethod.GET)
	public ModelAndView form() {
		
		Iterable<Projeto> projetos = projetoRepository.findAll();
		ModelAndView mv = new ModelAndView("tarefa/formTarefa");
		
		Map<Long,String> projetosMap = new LinkedHashMap<Long,String>();
		for (Projeto proj : projetos)
			projetosMap.put(proj.getCodigo(), proj.getNome());
		
		mv.addObject("projetos", projetosMap);
		
		Iterable<Membro> membros = membroRepository.findAll();
		mv.addObject("membros", membros);
		
		return mv;
	}
	
	// Cadastro efetuado de tarefa
	@RequestMapping(value="/cadastrartarefa", method=RequestMethod.POST)
	public String cadastrarTarefa(Tarefa tarefa) {
		
		tarefaRepository.save(tarefa);
		return "redirect:/cadastrartarefa";
	}
	
	@PutMapping(value="/api/cadastrartarefa", produces = "application/json")
	@ResponseBody
	public Tarefa salvarTarefaAPI(@RequestBody Tarefa tarefa) {
		
		// Admite que será passado o CPF do executor
		tarefa.setExecutor(membroRepository.findByCpf(tarefa.getExecutor().getCpf()));
		
		// Admite que será passado o codigo do projeto
		tarefa.setProjeto(projetoRepository.findByCodigo(tarefa.getProjeto().getCodigo()));
		
		return tarefaRepository.save(tarefa);
	}
	
	@GetMapping(value="/api/tarefas/{id}")
	@ResponseBody	
	public Tarefa carregarTarefaAPI(@PathVariable long id) {
		Optional<Tarefa> tarefa = tarefaRepository.findById(id);
		return tarefa.isPresent() ? tarefa.get() : null;
	}
	
	@GetMapping(value="/api/tarefas")
	@ResponseBody
	public Iterable<Tarefa> carregarTarefasAPI() {
		return tarefaRepository.findAll();
	}
	
	@PutMapping(value="/api/deletartarefa/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deletarCalendarioAPI(@PathVariable long id) {
		tarefaRepository.deleteById(id);
	}
}
