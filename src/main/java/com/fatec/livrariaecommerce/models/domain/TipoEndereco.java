package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class TipoEndereco extends EntidadeDominio {

	@Id
	@SequenceGenerator(name="tipo_endereco_sequence", sequenceName="tipo_endereco_sequence", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_endereco_sequence")
	private int id;
	private String descricao;
}
