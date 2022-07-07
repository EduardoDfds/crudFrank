package com.dev.crudv2.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.data.domain.Page; 
import org.springframework.util.StringUtils;

import com.dev.crudv2.domain.Produto;
import com.dev.crudv2.domain.ProdutoPreco;
import com.dev.crudv2.exception.BadResourceException;
import com.dev.crudv2.exception.ResourceAlreadyExistsException;
import com.dev.crudv2.exception.ResourceNotFoundException;
import com.dev.crudv2.repository.ProdutoPrecoRepository;
import com.dev.crudv2.repository.ProdutoRepository;



@Service
public class ProdutoPrecoService {
	@Autowired
	private ProdutoPrecoRepository produtoPrecoRepository;

	public ProdutoPreco save(ProdutoPreco produtoPreco){
		
		return produtoPrecoRepository.save(produtoPreco);

	}

}
