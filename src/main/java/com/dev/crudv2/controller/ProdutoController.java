package com.dev.crudv2.controller;
import javax.validation.Valid;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.data.domain.Pageable; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.data.domain.Page; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.MediaType; 
import org.springframework.http. ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind. annotation.GetMapping; 
import org.springframework.web.bind. annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind. annotation.RequestBody; 
import org.springframework.web.bind. annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind. annotation. RestController; 
import org.springframework.web.server.ResponseStatusException;

import com.dev.crudv2.domain.Produto;
import com.dev.crudv2.exception.ResourceNotFoundException;
import com.dev.crudv2.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation; 



@RestController
@RequestMapping("/api/produto") 
public class ProdutoController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Produto>> findAll(@RequestBody(required=false) String nome, Pageable pageable){
		
		if(StringUtils.isEmpty(nome)) {
			return ResponseEntity.ok(produtoService.findAll(pageable));
		}
		else {
			return ResponseEntity.ok(produtoService.findAllByNome(nome,pageable));
		}
}
	@Operation(summary = "Busca ID", description = "Buscar produto por ID", tags = {"produto"})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Produto> findProdutoById(@PathVariable long id){
	
		try {
			Produto produto = produtoService.findById(id);
			return ResponseEntity.ok(produto);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
		
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(),ex);
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Produto>updateProduto(@Valid @RequestBody Produto Produto, @PathVariable long id) {
			Produto.setId(id);
			produtoService.update(Produto);
			return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteProdutoById(@PathVariable long id){
			produtoService.deleteById(id);
			return ResponseEntity.ok().build();
	}
	
	
	@GetMapping(path = "/atualizarValorProdutoCategoria") 
	public ResponseEntity<Void> atualizarValorProdutoCategoria(@RequestParam Double percentual,@RequestParam Long id, @RequestParam String tipoOperacao){
		
		produtoService.atualizarValorProdutoCategoria(id, percentual, tipoOperacao);
		return ResponseEntity.ok().build();
	}
}


















