package com.dev.crudv2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crudv2.domain.Endereco;
import com.dev.crudv2.service.EnderecoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Operation(summary = "Busca endereco", description = "Buscar produto por cep", tags = {"produto"})
	@GetMapping(value = "/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Endereco> findEnderecoByCep(@PathVariable String cep){
	
			Endereco endereco = enderecoService.getEndereco(cep);
			return ResponseEntity.ok(endereco);
		}
}

