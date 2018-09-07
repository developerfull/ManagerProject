package com.cesarschool.ManagerProject.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Membro {

	@Id
	@NotEmpty
	private String cpf;
	@NotEmpty
	private String nome;
	@NotEmpty
	private String cargo;
	@NotEmpty
	private String cargaHoraria;
	@NotEmpty
	private String dias;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	private Projeto projeto;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCargo() {
		return cargo;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public String getDias() {
		return dias;
	}
	public void setDias(String dias) {
		this.dias = dias;
	} 
	
}



