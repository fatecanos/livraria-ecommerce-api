package com.fatec.livrariaecommerce.domain;

import javax.persistence.*;
import java.time.LocalDate;

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
	private String bairro;
	private String numero;
	private String cep;
	private String complemento;

	@OneToOne(cascade = CascadeType.ALL)
	private Cidade cidade;

	@OneToOne(cascade = CascadeType.ALL)
	private TipoEndereco tipoEndereco;

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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
}
