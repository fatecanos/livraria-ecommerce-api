package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor

@Getter
@Setter
@Entity
@Table(name = "cidade")
public class Cidade extends EntidadeDominio {

	private String nome;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "estado")
	private Estado estado;

}
