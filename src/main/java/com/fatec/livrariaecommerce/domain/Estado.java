package com.fatec.livrariaecommerce.domain;

import javax.persistence.*;

@Entity
public class Estado extends EntidadeDominio {
	@Id
	@SequenceGenerator(
			name="estados_sequences",
			sequenceName="estados_sequences",
			allocationSize=1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "estados_sequences"
	)
	private int id;
	private String descricao;

}
