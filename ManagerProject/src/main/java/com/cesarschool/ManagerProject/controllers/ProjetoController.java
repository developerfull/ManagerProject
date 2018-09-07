package com.cesarschool.ManagerProject.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.cesarschool.ManagerProject.repository.CalendarioRepository;
import com.cesarschool.ManagerProject.repository.MembroRepository;
import com.cesarschool.ManagerProject.repository.ProjetoRepository;
import com.cesarschool.ManagerProject.repository.TarefaRepository;




@Controller
public class ProjetoController {

	@Autowired
	private ProjetoRepository pr;
	
	@Autowired
	private MembroRepository mr;
	
	@Autowired
	private CalendarioRepository calendarioRepository;
	
	// Inicio de Requisição e Redericionamento para página de Cadastro
	@RequestMapping(value="/cadastrarprojeto", method=RequestMethod.GET)
	public String form() {
		return "projeto/formProjeto";
	}
	
	// Envio da Requisição e Registro das informações no BD 
	@RequestMapping(value="/cadastrarprojeto", method=RequestMethod.POST)
	public String form(Projeto projeto) {
		
		pr.save(projeto);
		return "redirect:/cadastrarprojeto";
	}
	
	//Requisição de Lista de Projetos Cadastrados 
	@RequestMapping("/projetos")
	public ModelAndView listaProjetos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Projeto> projetos = pr.findAll();
		mv.addObject("projetos", projetos);
		return mv; 
	}
	
	//Requisição de Lista de Detalhes de Cada Projeto
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesProjeto(@PathVariable("codigo") long codigo) {
		Projeto projeto = pr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("projeto/detalhesProjeto");
		mv.addObject("projeto", projeto);
		
		Iterable<Membro> membros = mr.findByProjeto(projeto);
		mv.addObject("membros", membros);
		
		
		return mv;
	}
	
	//Requisição que tem por ação deletar o projeto
	@RequestMapping("/deletarProjeto")
	public String deletarProjeto(long codigo) {
		Projeto projeto = pr.findByCodigo(codigo);
		pr.delete(projeto);
		return "redirect:/projetos";
	}
	
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesProjetoPost(@PathVariable("codigo")long codigo, Membro membro) {
		
		Projeto projeto = pr.findByCodigo(codigo);
		membro.setProjeto(projeto);
		mr.save(membro);
		
		return "redirect:/{codigo}";
	}
	
	@RequestMapping("/deletarMembro")
	public String deletarMembro(String cpf) {
		Membro membro = mr.findByCpf(cpf);
		mr.delete(membro);
		
		Projeto projeto = membro.getProjeto();
		long codigoLong = projeto.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
	}
	
	
	// API...
	
	@PutMapping(value="/api/cadastrarprojeto", produces = "application/json")
	@ResponseBody
	public Projeto salvarProjetoAPI(@RequestBody Projeto projeto) {
		
		// Admite que será passado o ID do calendário, caso deseje já vincular a um calendário
		if (projeto.getCalendario() != null && projeto.getCalendario().getId() != 0)
			projeto.setCalendario(calendarioRepository.findById(projeto.getCalendario().getId()).get());
		
		return pr.save(projeto);
	}
	
	@GetMapping(value="/api/projetos/{id}")
	@ResponseBody	
	public Projeto carregarProjetoAPI(@PathVariable long id) {
		Optional<Projeto> projeto = pr.findById(id);
		return projeto.isPresent() ? projeto.get() : null;
	}
	
	@GetMapping(value="/api/projetos")
	@ResponseBody
	public Iterable<Projeto> carregarProjetosAPI() {
		return pr.findAll();
	}
	
	@PutMapping(value="/api/deletarprojeto/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deletarProjetoAPI(@PathVariable long id) {
		pr.deleteById(id);
	}
}
