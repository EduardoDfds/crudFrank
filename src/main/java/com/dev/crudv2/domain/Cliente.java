package com.dev.crudv2.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax. persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints. NotBlank; 
import lombok.Getter; 
import lombok.Setter; 
import org.hibernate.annotations.Cache; 
import org.hibernate.annotations.CacheConcurrencyStrategy; 


@Entity
@Table(name = "cliente")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String nome;
	
	private String cpf;
	
	private String email;
	
	@Column(length = 4000)
	private String observacao;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro = new Date();
}
