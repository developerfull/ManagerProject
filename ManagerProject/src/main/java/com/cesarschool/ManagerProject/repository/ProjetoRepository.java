package com.cesarschool.ManagerProject.repository;

import org.springframework.data.repository.CrudRepository;
import com.cesarschool.ManagerProject.model.Projeto;

public interface ProjetoRepository extends CrudRepository<Projeto, Long>{

	Projeto findByCodigo(long codigo);
	
}