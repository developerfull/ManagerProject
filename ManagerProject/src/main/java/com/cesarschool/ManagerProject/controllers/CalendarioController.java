package com.cesarschool.ManagerProject.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cesarschool.ManagerProject.model.Calendario;
import com.cesarschool.ManagerProject.model.Projeto;
import com.cesarschool.ManagerProject.repository.CalendarioRepository;

@Controller
public class CalendarioController {

	@Autowired
	private CalendarioRepository calendarioRepository;
	
	// Carregando a tela de cadastro de calendário...
	@RequestMapping(value="/cadastrarcalendario", method=RequestMethod.GET)
	public String form() {
		return "calendario/formCalendario";
	}
	
	// Ao salvar um calendário...
	@RequestMapping(value="/cadastrarcalendario", method=RequestMethod.POST)
	public String form(Calendario calendario, @PathVariable("feriadosText")String feriados) {
		
		String[] split = feriados.split(";");
		
		List<Date> dates = new ArrayList<Date>();
		
		for (String feriado : split)
			dates.add(Date.valueOf(feriado));
		
		calendario.setFeriadosFixos(dates);
		
		calendarioRepository.save(calendario);
		return "redirect:/calendarios";
	}
	
	@RequestMapping("/calendarios")
	public ModelAndView listaProjetos() {
		ModelAndView mv = new ModelAndView("calendario/calendarios");
		Iterable<Calendario> calendarios = calendarioRepository.findAll();
		mv.addObject("calendarios", calendarios);
		return mv; 
	}
	
	@RequestMapping("/deletarCalendario")
	public String deletarProjeto(long codigo) {
		Optional<Calendario> calendario = calendarioRepository.findById((Long)codigo);
		if (calendario.isPresent())
			calendarioRepository.delete(calendario.get());
		
		return "redirect:/calendarios";
	}
}
