package com.dev.crudv2.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.data.domain.Page; 
import org.springframework.util.StringUtils;

import com.dev.crudv2.domain.ProdutoPreco;
import com.dev.crudv2.domain.Produto;
import com.dev.crudv2.exception.BadResourceException;
import com.dev.crudv2.exception.ResourceAlreadyExistsException;
import com.dev.crudv2.exception.ResourceNotFoundException;
import com.dev.crudv2.repository.ProdutoPrecoRepository;
import com.dev.crudv2.repository.ProdutoRepository;



@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoPrecoRepository produtoPrecoRepository;
	
	

	private boolean existsById(long id) {
		return produtoRepository.existsById(id);
	}
	
	public Produto findById(Long id) throws ResourceNotFoundException {
		Produto produto = produtoRepository.findById(id).orElse(null);
			if (produto==null) {
				throw new ResourceNotFoundException("Produto não encontrado com o id:" + id);
			}
			else return produto;
	}
	
	public Page<Produto> findAll(Pageable pageable){
		return produtoRepository.findAll(pageable);
	}
	
	public Page<Produto> findAllByNome(String nome, Pageable pageable){
		Page<Produto> produtos = produtoRepository.findByNome(nome, pageable);
		return produtos;
	}
	
	public Produto update(Produto produto){
		Produto produtoNovo = produtoRepository.save(produto);
		
		ProdutoPreco produtoPreco = new ProdutoPreco();
		produtoPreco.setProduto(produtoNovo);
		produtoPreco.setPrecoCusto(produtoNovo.getPrecoCusto());
		produtoPreco.setPrecoVenda(produtoNovo.getPrecoVenda());
		produtoPrecoRepository.save(produtoPreco);
		
		return produtoNovo;
		}
	
	public void atualizarValorProdutoCategoria(Long idCategoria, Double percentual, String tipoOperacao) {
		List<Produto> produtos = produtoRepository.buscarProdutosCategoria(idCategoria);
		
		
			for(Produto produto:produtos) {

				if(tipoOperacao.equals("-")) {
					produto.setPrecoVenda(produto.getPrecoVenda()*(1-(percentual/100)));
					
				}else if(tipoOperacao.equals("+")) {
					produto.setPrecoVenda(produto.getPrecoVenda()*(1+(percentual/100)));
				}
				update(produto);
			}
	}
	
	
	public Produto save(Produto produto){
		Produto produtoNovo = produtoRepository.save(produto);
		
		ProdutoPreco produtoPreco = new ProdutoPreco();
		produtoPreco.setProduto(produtoNovo);
		produtoPreco.setPrecoCusto(produtoNovo.getPrecoCusto());
		produtoPreco.setPrecoVenda(produtoNovo.getPrecoVenda());
		produtoPrecoRepository.save(produtoPreco);
		
		return produtoRepository.save(produto);
		}
	
	public Long count() {
		return produtoRepository.count();
	}

	public void deleteById(long id) {
		// TODO Auto-generated method stub
		
	}
}
