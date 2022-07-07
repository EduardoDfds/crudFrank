package com.dev.crudv2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dev.crudv2.domain.Endereco;


@Service
public class EnderecoService {
	
		public Endereco getEndereco( String cep) {
			String url = "https://brasilapi.com.br/api/cep/v2/"+cep;
			RestTemplate restTemplate = new RestTemplate();
			Endereco ob = restTemplate.getForObject(url, Endereco.class);
			return ob;
		}
}
