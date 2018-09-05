package com.cesarschool.ManagerProject.repository;

import org.springframework.data.repository.CrudRepository;
import com.cesarschool.ManagerProject.model.Projeto;

public interface ProjetoRepository extends CrudRepository<Projeto, String>{

	Projeto findByCodigo(long codigo);
	
}
