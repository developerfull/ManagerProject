package com.cesarschool.IHRV.ManagerProject.repository;

import org.springframework.data.repository.CrudRepository;

import com.cesarschool.IHRV.ManagerProject.model.Projeto;

public interface ProjetoRepository extends CrudRepository<Projeto, String>{

	Projeto findByCodigo(long codigo);
	
}
