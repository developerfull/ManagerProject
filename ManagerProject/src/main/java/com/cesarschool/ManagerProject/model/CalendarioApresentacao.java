package com.cesarschool.ManagerProject.model;

import java.sql.Date;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

// Classe de apresentação para a tela cadastro de calendário. Será convertida para a entidade Calendario.
public class CalendarioApresentacao {

	private long id;
	
	private String descricao;
	
	private String feriadosFixos;
	
	private String feriadosCiclicos;

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

	public String getFeriadosFixos() {
		return feriadosFixos;
	}

	public void setFeriadosFixos(String feriadosFixos) {
		this.feriadosFixos = feriadosFixos;
	}

	public String getFeriadosCiclicos() {
		return feriadosCiclicos;
	}

	public void setFeriadosCiclicos(String feriadosCiclicos) {
		this.feriadosCiclicos = feriadosCiclicos;
	}
}
