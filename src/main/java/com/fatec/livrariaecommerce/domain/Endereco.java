package com.fatec.livrariaecommerce.domain;

import javax.persistence.*;

@Entity
public class Endereco extends EntidadeDominio {
	@Id
	@SequenceGenerator(
			name="enderecos_sequences",
			sequenceName="encerecos_sequences",
			allocationSize=1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "enderecos_sequences"
	)
	private int id;
	private String nome;
	private String logradouro;
	private String numero;
	private String cep;
	private String complemento;
	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_CIDADE"))
	private Cidade cidade;
	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_TIPO_ENDERECO"))
	private TipoEndereco tipoEndereco;
}
