package com.fatec.livrariaecommerce.models.domain;

import javax.persistence.*;

@Entity
public class TipoEndereco extends EntidadeDominio {

	@Id
	@SequenceGenerator(
			name="tipo_endereco_sequence",
			sequenceName="tipo_endereco_sequence",
			allocationSize=1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "tipo_endereco_sequence"
	)
	private int id;
	private String descricao;
}
