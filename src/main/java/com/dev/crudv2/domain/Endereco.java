package com.dev.crudv2.domain;

import lombok.Data;

@Data
public class Endereco {
	private String cep;
	private String state;
	private String city;
	private String neighborhood;
	private String street;
	private String service;
	private Localizacao location;
}
