package com.cesarschool.ManagerProject.controllers;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.procedure.ParameterMisuseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesarschool.ManagerProject.model.Calendario;
import com.cesarschool.ManagerProject.model.CalendarioApresentacao;
import com.cesarschool.ManagerProject.model.Projeto;
import com.cesarschool.ManagerProject.repository.CalendarioRepository;

import javassist.NotFoundException;

@Controller
public class CalendarioController {

	@Autowired
	private CalendarioRepository calendarioRepository;
	
	// Carregando a tela de cadastro de calendario...
	@RequestMapping(value="/cadastrarcalendario", method=RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView("calendario/formCalendario");
		mv.addObject("calendario", new CalendarioApresentacao()); // Calendario ainda será inserido
		
		return mv;
	}
	
	// Ao salvar um calendario, apresentação vinda da web...
	@RequestMapping(value="/cadastrarcalendario", method=RequestMethod.POST)
	public String form(CalendarioApresentacao calendarioApres) {
		
		// Converte a classe de apresentação (web/html) para a entidade calendario, salva em banco
		Calendario calendario = converterParaCalendario(calendarioApres);
		
		calendarioRepository.save(calendario);
		return "redirect:/calendarios";
	}
	
	private Calendario converterParaCalendario(CalendarioApresentacao calendarioApres) {
		Calendario calendario = new Calendario();
		calendario.setId(calendarioApres.getId());
		calendario.setDescricao(calendarioApres.getDescricao());
		
		// Converte o texto de feriados fixos em datas
		String feriadosFixos = calendarioApres.getFeriadosFixos();
		if (feriadosFixos != null) {
			String[] split = feriadosFixos.split(";");
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
			
			List<Date> dates = new ArrayList<Date>();
			
			for (String feriado : split) {
				LocalDate date = LocalDate.parse(feriado, formatter);
				dates.add(Date.valueOf(date));
			}
			
			calendario.setFeriadosFixos(dates);
		}
		
		// Converte os testos de feriados cíclicos em dia-mês
		String feriadosCiclicos = calendarioApres.getFeriadosCiclicos();
		if (feriadosCiclicos != null) {
			String[] split = feriadosCiclicos.split(";");
			
			List<MonthDay> dates = new ArrayList<MonthDay>();
			
			for (String feriado : split) {
				String[] partes = feriado.split("/");
				if (partes.length == 2) {
					int dia = Integer.parseInt(partes[0]);
					int mes = Integer.parseInt(partes[1]);
					dates.add(MonthDay.of(mes, dia));
				}
				else
					throw new ParameterMisuseException("Dia-mês em formato errado. Deveria ser no formato dd/mm.");
			}
			
			calendario.setFeriadosCiclicos(dates);
		}
		
		return calendario;
	}
	
	private CalendarioApresentacao converterParaApresentacao(Calendario calendario) {
		CalendarioApresentacao apres = new CalendarioApresentacao();
		
		apres.setId(calendario.getId());
		apres.setDescricao(calendario.getDescricao());
		
		List<MonthDay> feriadosCiclicos = calendario.getFeriadosCiclicos();
		if (feriadosCiclicos != null) {
		
			List<String> diasmeses = new ArrayList<String>(feriadosCiclicos.size());
			for (MonthDay diames : feriadosCiclicos)
				diasmeses.add(diames.getDayOfMonth() + "/" + diames.getMonthValue());
			
			apres.setFeriadosCiclicos(String.join(";", diasmeses));
		}
		
		List<Date> feriadosFixos = calendario.getFeriadosFixos();
		if (feriadosFixos != null) {
			
			List<String> datas = new ArrayList<String>(feriadosCiclicos.size());
			DateFormat dateFormat = new SimpleDateFormat("d/MM/yyyy");
			for (Date data : feriadosFixos)
				datas.add(dateFormat.format(data));
			
			apres.setFeriadosFixos(String.join(";", datas));
		}
		
		return apres;
	}
	
	@PutMapping(value="/api/cadastrarcalendario", produces = "application/json")
	@ResponseBody
	public Calendario salvarCalendario(@RequestBody Calendario calendario) {
		return calendarioRepository.save(calendario);
	}
	
	@PutMapping(value="/api/deletarcalendario", produces = "application/json")
	public void deletarCalendarioAPI(@RequestBody Long id) {
		calendarioRepository.deleteById(id);
	}
	
	@GetMapping(value="/api/calendarios/id={id}", produces = "application/json")
	public Calendario listarCalendarioAPI(@PathVariable Long id) {
		Optional<Calendario> calendario = calendarioRepository.findById(id);
		return calendario.isPresent() ? calendario.get() : null;	
	}
	
	@GetMapping(value="/api/calendarios", produces = "application/json")
	public Iterable<Calendario> listarCalendariosAPI() {
		return calendarioRepository.findAll();
	}
	
	@RequestMapping("/calendarios")
	public ModelAndView listaProjetos() {
		ModelAndView mv = new ModelAndView("calendario/calendarios");
		Iterable<Calendario> calendarios = calendarioRepository.findAll();
		mv.addObject("calendarios", calendarios);
		return mv; 
	}
	
	@RequestMapping(value="/calendarios/{id}", method=RequestMethod.GET)
	public ModelAndView listaCalendario(@PathVariable Long id) throws NotFoundException {
		ModelAndView mv = new ModelAndView("calendario/formCalendario");
		Optional<Calendario> calendario = calendarioRepository.findById(id);
		
		if (calendario.isPresent())
			mv.addObject("calendario", converterParaApresentacao(calendario.get()));
		else
			throw new NotFoundException("O calendário requisitado não existe.");
		
		return mv; 
	}
	
	// Ao salvar um calendario, apresentação vinda da web...
	@RequestMapping(value="/calendarios/{id}", method=RequestMethod.POST)
	public String atualizarCalendario(CalendarioApresentacao calendarioApres) {
		
		// Converte a classe de apresentação (web/html) para a entidade calendario, salva em banco
		Calendario calendario = converterParaCalendario(calendarioApres);
		
		calendarioRepository.save(calendario);
		return "redirect:/calendarios";
	}
	
	@RequestMapping("/deletarCalendario")
	public String deletarCalendario(long codigo) {
		calendarioRepository.deleteById(codigo);
		
		return "redirect:/calendarios";
	}
}
