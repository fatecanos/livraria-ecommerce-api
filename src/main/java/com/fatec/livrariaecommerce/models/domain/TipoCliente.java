package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter

@Entity
public class TipoCliente extends EntidadeDominio {
//	@Id
//	@SequenceGenerator(
//			name="tipoclientes_sequence",
//			sequenceName="tipoclientes_sequence",
//			allocationSize=1
//	)
//	@GeneratedValue(
//			strategy = GenerationType.SEQUENCE,
//			generator = "tipoclientes_sequence"
//	)
//	private int id;
	private String nome;
	private String descricao;

	public TipoCliente() {
		super();
	}

	public TipoCliente(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public TipoCliente(LocalDate timeStamp, boolean isAtivo, int id, String nome, String descricao) {
		super();
//		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	public TipoCliente(int id, String nome, String descricao) {
//		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}
}
