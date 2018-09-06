package com.cesarschool.ManagerProject.repository;

import org.springframework.data.repository.CrudRepository;

import com.cesarschool.ManagerProject.model.Conta;
import com.cesarschool.ManagerProject.model.Membro;

public interface ContaRepository extends CrudRepository<Conta, String>{

	Conta findByNome(String nome);
}
