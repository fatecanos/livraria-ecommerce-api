package com.fatec.livrariaecommerce.models.domain;

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

	public Estado() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
