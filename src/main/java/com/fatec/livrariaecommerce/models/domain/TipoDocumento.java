package com.fatec.livrariaecommerce.models.domain;


import javax.persistence.*;

@Entity
public class TipoDocumento extends EntidadeDominio {

//	@Id
//	@SequenceGenerator(
//			name="tipo_doc_sequence",
//			sequenceName="tipo_doc_sequence",
//			allocationSize=1
//	)
//	@GeneratedValue(
//			strategy = GenerationType.SEQUENCE,
//			generator = "tipo_doc_sequence"
//	)
//	private int id;
	private String descricao;
	private String nome;

	public TipoDocumento() {
		this.setAtivo(true);
	}

	public TipoDocumento(String descricao, String nome) {
		this.descricao = descricao;
		this.nome = nome;
		this.setAtivo(true);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
