package com.fatec.livrariaecommerce.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class TipoCliente extends EntidadeDominio {
	@Id
	@SequenceGenerator(
			name="tipoclientes_sequence",
			sequenceName="tipoclientes_sequence",
			allocationSize=1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "tipoclientes_sequence"
	)
	private int id;
	private String nome;
	private String descricao;

	public TipoCliente() {
		super();
	}

	public TipoCliente(LocalDate timeStamp, boolean isAtivo, int id, String nome, String descricao) {
		super(timeStamp, isAtivo);
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	public TipoCliente(int id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
