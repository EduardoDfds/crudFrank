package com.dev.crudv2.domain;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax. persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints. NotBlank; 
import lombok.Getter; 
import lombok.Setter; 
import org.hibernate.annotations.Cache; 
import org.hibernate.annotations.CacheConcurrencyStrategy;

import io.swagger.v3.oas.annotations.media.Schema; 


@Entity
@Table(name = "Categoria")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Categoria implements Serializable {

	private static final long serialVersionUID = 4048798961366546485L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String nome;

    private Date dataCadastro = new Date();
    
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;
}
