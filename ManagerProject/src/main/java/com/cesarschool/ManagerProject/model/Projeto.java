package com.cesarschool.ManagerProject.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Projeto implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long codigo;
	
	@NotNull
	private String nome;
	
	private Date prazo;
	
	private Date dataInicio;
	
	@ManyToOne
	private Calendario calendario;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "projeto", targetEntity = Membro.class)
	private List<Membro> membros = new ArrayList<Membro>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "projeto", targetEntity = Tarefa.class)
	private List<Tarefa> tarefas = new ArrayList<Tarefa>();
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getPrazo() {
		return prazo;
	}
	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Calendario getCalendario() {
		return calendario;
	}
	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}
	public List<Membro> getMembros() {
		return membros;
	}
	public List<Tarefa> getTarefas() {
		return tarefas;
	}
	
}
