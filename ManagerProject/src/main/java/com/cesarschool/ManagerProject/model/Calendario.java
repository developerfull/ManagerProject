package com.cesarschool.ManagerProject.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Calendario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String descricao;
	
	@ElementCollection
	private List<Date> feriadosFixos = new ArrayList<Date>();
	
	@ElementCollection
	private List<MonthDay> feriadosCiclicos = new ArrayList<MonthDay>();

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Date> getFeriadosFixos() {
		return feriadosFixos;
	}

	public void setFeriadosFixos(List<Date> feriadosFixos) {
		this.feriadosFixos = feriadosFixos;
	}
	
	public List<MonthDay> getFeriadosCiclicos() {
		return feriadosCiclicos;
	}

	public void setFeriadosCiclicos(List<MonthDay> feriadosCiclicos) {
		this.feriadosCiclicos = feriadosCiclicos;
	}
}