package com.fatec.livrariaecommerce.models.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cidade")
public class Cidade extends EntidadeDominio {
//	@Id
//	@SequenceGenerator(
//			name="cidades_sequences",
//			sequenceName="cidades_sequences",
//			allocationSize=1
//	)
//	@GeneratedValue(
//			strategy = GenerationType.SEQUENCE,
//			generator = "cidades_sequences"
//	)
//	private int id;
	private String nome;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "estado")
	private Estado estado;

	public Cidade() {
	}
}
