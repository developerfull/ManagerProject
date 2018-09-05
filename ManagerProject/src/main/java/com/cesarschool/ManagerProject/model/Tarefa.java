package com.cesarschool.ManagerProject.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Tarefa {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String titulo;
	
	private String descricao;
	
	@ManyToOne
	private Membro executor;

	private float duracaoEmDias;
	
	private Date diaMinimoInicio;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	private Projeto projeto;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Membro getExecutor() {
		return executor;
	}

	public void setExecutor(Membro executor) {
		this.executor = executor;
	}

	public float getDuracaoEmDias() {
		return duracaoEmDias;
	}

	public void setDuracaoEmDias(float duracaoEmDias) {
		this.duracaoEmDias = duracaoEmDias;
	}

	public Date getDiaMinimoInicio() {
		return diaMinimoInicio;
	}

	public void setDiaMinimoInicio(Date diaMinimoInicio) {
		this.diaMinimoInicio = diaMinimoInicio;
	}
}
