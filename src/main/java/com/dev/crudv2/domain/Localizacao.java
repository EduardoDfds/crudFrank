package com.dev.crudv2.domain;

import lombok.Data;

@Data
public class Localizacao {
	private String type;
	private Coordenadas coordinates;
}
