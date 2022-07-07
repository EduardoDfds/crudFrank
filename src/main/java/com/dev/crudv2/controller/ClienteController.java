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
import org.springframework.web.bind. annotation. RestController; 
import org.springframework.web.server.ResponseStatusException;

import com.dev.crudv2.domain.Cliente;
import com.dev.crudv2.dto.ClienteDTO;
import com.dev.crudv2.exception.BadResourceException;
import com.dev.crudv2.exception.ResourceNotFoundException;
import com.dev.crudv2.service.ClienteService;






@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<ClienteDTO>> findAll(@RequestBody(required=false) String nome, Pageable pageable){
		
		if(StringUtils.isEmpty(nome)) {
			return ResponseEntity.ok(new ClienteDTO().converterListaClienteDTO(clienteService.findAll(pageable)));
		}
		else {
			return ResponseEntity.ok(new ClienteDTO().converterListaClienteDTO(clienteService.findAll(pageable)));
		}
}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> findClienteById(@PathVariable long id){
	
		try {
			Cliente cliente = clienteService.findById(id);
			return ResponseEntity.ok(cliente);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
		
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(),ex);
		}
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente>updateCliente(@Valid @RequestBody Cliente cliente, @PathVariable long id) throws ResourceNotFoundException{
		try {
			cliente.setId(id);
			clienteService.update(cliente);
			return ResponseEntity.ok().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteClienteById(@PathVariable long id){
		try {
			clienteService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
}



















