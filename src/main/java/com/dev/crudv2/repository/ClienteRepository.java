package com.dev.crudv2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.crudv2.domain.Cliente;




public interface ClienteRepository extends JpaRepository<Cliente,Long> {
	
	@Query(value = "Select a from Cliente a where a.nome like %?1%") Page<Cliente> findByNome(String nome, Pageable pageable);
	Page<Cliente> findAll(Pageable pageable);
} 
