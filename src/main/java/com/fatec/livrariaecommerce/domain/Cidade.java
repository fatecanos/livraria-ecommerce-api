package com.fatec.livrariaecommerce.domain;

import javax.persistence.*;

@Entity
public class Cidade extends EntidadeDominio {
	@Id
	@SequenceGenerator(
			name="cidades_sequences",
			sequenceName="cidades_sequences",
			allocationSize=1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "cidades_sequences"
	)
	private int id;
	private String descricao;
	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_ESTADO"))
	private Estado estado;
}
