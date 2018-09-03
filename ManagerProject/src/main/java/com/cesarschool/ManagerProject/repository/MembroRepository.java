package com.cesarschool.ManagerProject.repository;

import org.springframework.data.repository.CrudRepository;

import com.cesarschool.ManagerProject.model.Membro;
import com.cesarschool.ManagerProject.model.Projeto;

public interface MembroRepository extends CrudRepository<Membro, String>{

	Iterable<Membro> findByProjeto(Projeto projeto);
	Membro findByCpf(String cpf);
}
