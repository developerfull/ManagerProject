package com.cesarschool.IHRV.ManagerProject.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cesarschool.IHRV.ManagerProject.model.Projeto;
import com.cesarschool.IHRV.ManagerProject.repository.ProjetoRepository;


@Controller
public class ProjetoController {

	@Autowired
	private ProjetoRepository pr;
	
	// Inicio de Requisição e Redericionamento para página de Cadastro
	@RequestMapping(value="/cadastrarprojeto", method=RequestMethod.GET)
	public String form() {
		return "projeto/formProjeto";
	}
	
	// Envio da Requisição e Registro das informações no BD 
	@RequestMapping(value="/cadastrarprojeto", method=RequestMethod.POST)
	public String form(@Valid Projeto projeto, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarprojeto";
		}
		
		pr.save(projeto);
		attributes.addFlashAttribute("mensagem", "Projeto Cadastrado com Sucesso!");
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
	//Requisição de Lista e Detalhes do Projeto
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesProjeto(@PathVariable("codigo")long codigo) {
		Projeto projeto = pr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("projeto/detalhesProjeto");
		mv.addObject("projeto", projeto);
		
		/*
		Iterable<Convidado> convidados = cr.findByEvento(evento);
		mv.addObject("convidados", convidados);
		*/
		
		return mv;
	}
	
	
}
