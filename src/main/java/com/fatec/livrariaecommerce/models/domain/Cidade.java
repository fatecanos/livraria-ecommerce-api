package com.fatec.livrariaecommerce.models.domain;

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

	@OneToOne(cascade = CascadeType.ALL)
	private Estado estado;

	public Cidade() {
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
