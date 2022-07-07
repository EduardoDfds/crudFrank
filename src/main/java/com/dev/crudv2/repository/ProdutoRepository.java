package com.dev.crudv2.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.crudv2.domain.Produto;




public interface ProdutoRepository extends JpaRepository<Produto,Long> {
	
	@Query(value = "Select a from Produto a where a.nome like %?1%") Page<Produto> findByNome(String nome, Pageable pageable);
	Page<Produto> findAll(Pageable pageable);
	
	//Buscar todos os produtos de acordo com a categoria
	@Query("Select p from Produto p where p.categoria.id=?1")
	public List<Produto> buscarProdutosCategoria(Long idCategoria);
} 


