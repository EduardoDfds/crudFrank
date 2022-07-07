package com.dev.crudv2.dto.request;

import org.springframework.beans.BeanUtils;


import com.dev.crudv2.domain.Aluno;

import lombok.Data;

@Data
public class AlunoDTORequest {

	private String nome;
	private String email;
	private String cpf;
	private String observacao;
	private String senha;

	public Aluno converter(AlunoDTORequest alunoDTO) {
		Aluno aluno = new Aluno();
		BeanUtils.copyProperties(alunoDTO, aluno);
		return aluno;
	}
	
	
}
